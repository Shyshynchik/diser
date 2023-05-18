package com.example.demo.entity.redis;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList")
@ToString
@Getter
@Setter
public class LastFiveArticles extends CashingArticlesList {

    @Builder
    public LastFiveArticles(List<String> articlesList) {
        super(CashedId.topFive, articlesList);
    }

}
