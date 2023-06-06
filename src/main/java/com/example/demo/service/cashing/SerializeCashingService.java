package com.example.demo.service.cashing;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashedId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service("templateService")
@RequiredArgsConstructor
public class SerializeCashingService implements CashingService {

    private final RedisTemplate<String, Article> redisTemplate;

    @Override
    public Optional<List<Article>> findById(CashedId cashedId) {
        var articles = redisTemplate.opsForList().range(cashedId.toString(), 0, cashedId.getCount());

        return articles != null && !articles.isEmpty()
                ? Optional.of(articles)
                : Optional.empty();
    }

    @Override
    public void save(CashedId cashedId, List<Article> list) {
        redisTemplate.delete(cashedId.toString());

        redisTemplate.opsForList().leftPushAll(cashedId.toString(), list);

        if (cashedId.getTtl() > 0) {
            redisTemplate.expire(cashedId.toString(), cashedId.getTtl(), TimeUnit.SECONDS);
        }
    }

    @Override
    public void updateCash(CashedId cashedId, Article article) {
        redisTemplate.opsForList().leftPop(cashedId.toString());

        redisTemplate.opsForList().rightPush(cashedId.toString(), article);
    }
}
