package com.example.demo.service.cashing;

import com.example.demo.entity.redis.CashedId;
import com.example.demo.entity.redis.CashingArticlesList;
import com.example.demo.repositorie.redis.RedisArticlesListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisCashingService implements CashingService<CashingArticlesList, CashedId> {

    private final RedisArticlesListRepository redisArticlesListRepository;

    @Override
    public Optional<CashingArticlesList> findById(CashedId cashedId) {
        return redisArticlesListRepository.findById(cashedId);
    }

    @Override
    public void save(CashingArticlesList cashingArticlesList) {
        redisArticlesListRepository.save(cashingArticlesList);
    }
}
