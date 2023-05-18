package com.example.demo.entity.cashing.redis;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashedId;
import com.example.demo.entity.cashing.CashingArticlesList;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash(value = "ArticlesList")
@Data
@Builder
public class RedisCashingArticlesList implements CashingArticlesList {

    @Id
    private CashedId id;

    private List<String> articlesList;

}
