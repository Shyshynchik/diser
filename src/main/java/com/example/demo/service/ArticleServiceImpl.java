package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.repositorie.mysql.ArticleRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepositoryJpa articleRepositoryJpa;

    @Override
    public List<Article> findActualArticles() {
        return articleRepositoryJpa.findActualNews();
    }

    @Override
    public Article save(Article article) {
        return articleRepositoryJpa.save(article);
    }

    @Override
    public List<Article> findLastFiveArticles() {
        return articleRepositoryJpa.findLastFiveArticles();
    }


}
