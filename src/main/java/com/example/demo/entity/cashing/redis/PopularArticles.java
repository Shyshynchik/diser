package com.example.demo.entity.cashing.redis;

import com.example.demo.entity.cashing.CashedId;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList", timeToLive = 30L)
@ToString
@Getter
@Setter
public class PopularArticles extends RedisCashingArticles {

    @Builder
    public PopularArticles(List<String> articlesList) {
        super(CashedId.popular, articlesList);
    }


}
