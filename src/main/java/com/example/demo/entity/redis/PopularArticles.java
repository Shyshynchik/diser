package com.example.demo.entity.redis;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList", timeToLive = 30L)
@ToString
@Getter
@Setter
public class PopularArticles extends RedisArticlesList {

    @Builder
    public PopularArticles(List<String> articlesList) {
        super(RedisIds.popular, articlesList);
    }


}
