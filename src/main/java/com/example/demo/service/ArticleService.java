package com.example.demo.service;

import com.example.demo.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> findActualArticles();

    Article save(Article article);

    List<Article> findLastFiveArticles();

    List<Article> findByIdInOrderByDateDesc(List<String> ids);

}
