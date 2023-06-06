package com.example.demo.service.cashing;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashingArticlesList;
import com.example.demo.entity.cashing.CashedId;
import com.example.demo.entity.cashing.redis.RedisCashingArticlesList;
import com.example.demo.repositorie.mysql.ArticleRepositoryJpa;
import com.example.demo.repositorie.redis.RedisArticlesListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("uuidService")
@RequiredArgsConstructor
public class RedisCashingService implements CashingService {

    private final RedisArticlesListRepository redisArticlesListRepository;
    private final ArticleRepositoryJpa articleRepositoryJpa;

    @Override
    public Optional<List<Article>> findById(CashedId cashedId) {
        var data = redisArticlesListRepository.findById(cashedId);

        return data.map(this::findArticlesByCash);
    }

    @Override
    public void save(CashedId cashedId, List<Article> list) {
        redisArticlesListRepository.save(RedisCashingArticlesList.builder().id(cashedId).articlesList(list.stream().map(Article::getId).toList()).build());
    }

    @Override
    public void updateCash(CashedId cashedId, Article article) {
        var cashOpt = redisArticlesListRepository.findById(cashedId);

        if (cashOpt.isEmpty()) {
            return;
        }

        var cash = cashOpt.get();

        var articleDeque = new ArrayDeque<>(cash.getArticlesList());

        articleDeque.removeLast();
        articleDeque.addFirst(article.getId());

        cash.setArticlesList(new ArrayList<>(articleDeque));

        redisArticlesListRepository.save(cash);
    }

    private List<Article> findArticlesByCash(RedisCashingArticlesList cash) {
        return articleRepositoryJpa.findByIdInOrderByDateDesc(cash.getArticlesList());
    }

}
