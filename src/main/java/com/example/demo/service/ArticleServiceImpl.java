package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.repositorie.mysql.ArticleRepositoryJpa;
import com.example.demo.repositorie.mysql.CommentRepositoryJpa;
import com.example.demo.repositorie.redis.ArticleRepositoryRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepositoryJpa articleRepositoryJpa;
    private final ArticleRepositoryRedis articleRepositoryRedis;

    private final CommentRepositoryJpa commentRepositoryJpa;

    @Override
    public Optional<Article> getArticleById(UUID id) {

        var test = commentRepositoryJpa.findById(UUID.fromString("931023a3-f39b-11ed-966a-0242ac120002"));

        var redis = articleRepositoryRedis.findById(id);
        if (redis.isPresent()) {
            return redis;
        }

        var article = articleRepositoryJpa.findById(id);

        cashData(article);

        return article;
    }

    private void cashData(Optional<Article> articleOpt) {
        articleOpt.map(articleRepositoryRedis::save);
    }
}
