package com.example.demo.entity.cashing.redis;

import com.example.demo.entity.cashing.CashedId;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList")
@ToString
@Getter
@Setter
public class LastFiveArticles extends RedisCashingArticles {

    @Builder
    public LastFiveArticles(List<String> articlesList) {
        super(CashedId.topFive, articlesList);
    }

}
