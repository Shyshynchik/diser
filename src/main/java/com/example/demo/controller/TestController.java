package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ArticleService articleService;

    @GetMapping("/test/{id}")
    public @ResponseBody ResponseEntity<Article> getArticleById(@PathVariable(name = "id") UUID id) {
        var article = articleService.getArticleById(id);

        var test = article.get().getComments();

        return article.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
