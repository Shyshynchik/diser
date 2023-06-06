package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.model.ArticlesDto;
import com.example.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ArticleService articleService;

    @GetMapping("/test/actual")
    public @ResponseBody ResponseEntity<ArticlesDto> getActual() {

        var articles = articleService.findActualArticles();

        var articlesDtoList = new ArrayList<ArticlesDto.ArticleDto>();

        for (var article: articles) {
            articlesDtoList.add(
                    ArticlesDto.ArticleDto.builder().title(article.getTitle()).text(article.getText()).build()
            );
        }

        return ResponseEntity.ok(ArticlesDto.builder().articleDtos(articlesDtoList).build());
    }

    @GetMapping("/test/last-five")
    public @ResponseBody ResponseEntity<ArticlesDto> getTopFive() {

        var articles = articleService.findLastFiveArticles();

        var articlesDtoList = new ArrayList<ArticlesDto.ArticleDto>();

        for (var article: articles) {
            articlesDtoList.add(
                    ArticlesDto.ArticleDto.builder().title(article.getTitle()).text(article.getText()).build()
            );
        }

        return ResponseEntity.ok(ArticlesDto.builder().articleDtos(articlesDtoList).build());
    }

    @GetMapping("/test/save")
    public @ResponseBody ResponseEntity<Article> save() {

        var article = new Article();

        article.setId(UUID.randomUUID().toString());
        article.setTitle("Some title" + LocalDateTime.now());
        article.setText("Some text" + LocalDateTime.now());
        article.setDate(LocalDateTime.now());
        article.setViews(1000);

        var articles = articleService.save(article);

        return ResponseEntity.ok(articles);
    }

}
