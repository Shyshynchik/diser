package com.example.demo.entity.redis;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList")
@ToString
@Getter
@Setter
@AllArgsConstructor
public class CashingArticlesList {

    @Id
    @Getter(AccessLevel.NONE)
    private CashedId id;

    private List<String> articlesList;

}
