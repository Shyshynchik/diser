package com.example.demo.entity.cashing.redis;

import com.example.demo.entity.cashing.CashedId;
import com.example.demo.entity.cashing.CashingArticles;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList")
@ToString
@Getter
@Setter
@AllArgsConstructor
public class RedisCashingArticles implements CashingArticles {

    @Id
    @Getter(AccessLevel.NONE)
    private CashedId id;

    private List<String> articlesList;

}
