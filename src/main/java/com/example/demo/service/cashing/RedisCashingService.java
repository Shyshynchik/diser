package com.example.demo.service.cashing;

import com.example.demo.entity.cashing.CashingArticles;
import com.example.demo.entity.cashing.CashedId;
import com.example.demo.entity.cashing.redis.RedisCashingArticles;
import com.example.demo.repositorie.redis.RedisArticlesListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisCashingService implements CashingService {

    private final RedisArticlesListRepository redisArticlesListRepository;

    @Override
    public Optional<? extends CashingArticles> findById(CashedId cashedId) {
        return redisArticlesListRepository.findById(cashedId);
    }

    @Override
    public <T extends CashingArticles> void save(T cashingArticlesList) {
        redisArticlesListRepository.save((RedisCashingArticles) cashingArticlesList);
    }

    @Override
    public CashingArticles buildById(CashedId cashedId, List<String> list) {
        return RedisCashingArticles.builder().id(cashedId).articlesList(list).build();
    }
}
