package com.example.demo.service;

import com.example.demo.entity.Article;

import java.util.Optional;
import java.util.UUID;

public interface ArticleService {

    Optional<Article> getArticleById(UUID id);

}
