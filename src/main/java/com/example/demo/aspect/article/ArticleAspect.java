package com.example.demo.aspect.article;

import com.example.demo.entity.Article;
import com.example.demo.entity.redis.LastFiveArticles;
import com.example.demo.entity.redis.PopularArticles;
import com.example.demo.entity.redis.RedisIds;
import com.example.demo.repositorie.mysql.ArticleRepositoryJpa;
import com.example.demo.repositorie.redis.RedisArticlesListRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Aspect
@RequiredArgsConstructor
public class ArticleAspect {

    private final RedisArticlesListRepository redisArticlesListRepository;
    private final ArticleRepositoryJpa articleRepositoryJpa;


    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Around(value = "execution(* com.example.demo.service.ArticleService.findActualArticles(..))")
    public List<Article> aroundCallFindActualArticles(ProceedingJoinPoint pjp) {
        var popularArticlesOpt = redisArticlesListRepository.findById(RedisIds.popular);

        if (popularArticlesOpt.isPresent()) {
            return articleRepositoryJpa.findByIdInOrderByDateDesc(popularArticlesOpt.get().getArticlesList());
        }

        var articles = (List<Article>) pjp.proceed();

        redisArticlesListRepository.save(
                PopularArticles.builder().articlesList(
                        articles.stream().map(Article::getId).toList()
                ).build()
        );

        return articles;
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.service.ArticleService.save(..))", returning = "retVal")
    public void afterReturnSave(JoinPoint jp, Object retVal) {
        var lastFiveArticlesOpt = redisArticlesListRepository.findById(RedisIds.topFive);

        if (lastFiveArticlesOpt.isEmpty()) {
            return;
        }

        var article = (Article) retVal;

        var lastFiveArticles = lastFiveArticlesOpt.get();

        var articleDeque = new ArrayDeque<>(lastFiveArticles.getArticlesList());

        articleDeque.removeLast();
        articleDeque.addFirst(article.getId());

        var test = new ArrayList<>(articleDeque);

        redisArticlesListRepository.save(LastFiveArticles.builder().articlesList(test).build());
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Around(value = "execution(* com.example.demo.service.ArticleService.findLastFiveArticles(..))")
    public List<Article> aroundCallFindLastFiveArticles(ProceedingJoinPoint pjp) {
        var lastFiveArticleRepository = redisArticlesListRepository.findById(RedisIds.topFive);

        if (lastFiveArticleRepository.isPresent()) {
            return articleRepositoryJpa.findByIdInOrderByDateDesc(lastFiveArticleRepository.get().getArticlesList());
        }

        var articles = (List<Article>) pjp.proceed();

        redisArticlesListRepository.save(
                LastFiveArticles.builder()
                        .articlesList(articles.stream().map(Article::getId).toList())
                        .build()
        );

        return articles;
    }

}
