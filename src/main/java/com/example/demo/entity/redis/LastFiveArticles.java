package com.example.demo.entity.redis;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList")
@ToString
@Getter
@Setter
public class LastFiveArticles extends RedisArticlesList {

    @Builder
    public LastFiveArticles(List<String> articlesList) {
        super(RedisIds.topFive, articlesList);
    }

}
