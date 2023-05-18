package com.example.demo.entity.redis;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList", timeToLive = 30L)
@ToString
@Getter
@Setter
public class PopularArticles extends CashingArticlesList {

    @Builder
    public PopularArticles(List<String> articlesList) {
        super(CashedId.popular, articlesList);
    }


}
