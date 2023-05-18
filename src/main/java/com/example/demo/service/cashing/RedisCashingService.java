package com.example.demo.service.cashing;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashingArticlesList;
import com.example.demo.entity.cashing.CashedId;
import com.example.demo.entity.cashing.redis.RedisCashingArticlesList;
import com.example.demo.repositorie.mysql.ArticleRepositoryJpa;
import com.example.demo.repositorie.redis.RedisArticlesListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisCashingService implements CashingService {

    private final RedisArticlesListRepository redisArticlesListRepository;
    private final ArticleRepositoryJpa articleRepositoryJpa;

    @Override
    public Optional<? extends CashingArticlesList> findById(CashedId cashedId) {
        return redisArticlesListRepository.findById(cashedId);
    }

    @Override
    public <T extends CashingArticlesList> void save(T cashingArticlesList) {
        redisArticlesListRepository.save((RedisCashingArticlesList) cashingArticlesList);
    }

    @Override
    public CashingArticlesList buildById(CashedId cashedId, List<String> list) {
        return RedisCashingArticlesList.builder().id(cashedId).articlesList(list).build();
    }

    @Override
    public List<Article> findArticlesByCash(CashingArticlesList cash) {
        return articleRepositoryJpa.findByIdInOrderByDateDesc(cash.getArticlesList());
    }

}
