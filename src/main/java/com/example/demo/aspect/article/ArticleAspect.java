package com.example.demo.aspect.article;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashedId;
import com.example.demo.service.ArticleService;
import com.example.demo.service.cashing.CashingService;
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

    private final ArticleService articleService;

    private final CashingService cashingService;


    /**
     * Unchecked cast will always return (List<Article>), because method ArticleService.findActualArticles returns List<Article>
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Around(value = "execution(* com.example.demo.service.ArticleService.findActualArticles(..))")
    public List<Article> aroundCallFindActualArticles(ProceedingJoinPoint pjp) {
        var popularArticlesOpt = cashingService.findById(CashedId.popular);

        if (popularArticlesOpt.isPresent()) {
            return articleService.findByIdInOrderByDateDesc(popularArticlesOpt.get().getArticlesList());
        }

        var articles = (List<Article>) pjp.proceed();

        cashingService.save(
                cashingService.buildById(CashedId.popular, articles.stream().map(Article::getId).toList())
        );

        return articles;
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.service.ArticleService.save(..))", returning = "retVal")
    public void afterReturnSave(JoinPoint jp, Object retVal) {
        var lastFiveArticlesOpt = cashingService.findById(CashedId.topFive);

        if (lastFiveArticlesOpt.isEmpty()) {
            return;
        }

        var article = (Article) retVal;

        var lastFiveArticles = lastFiveArticlesOpt.get();

        var articleDeque = new ArrayDeque<>(lastFiveArticles.getArticlesList());

        articleDeque.removeLast();
        articleDeque.addFirst(article.getId());

        cashingService.save(
                cashingService.buildById(CashedId.topFive, new ArrayList<>(articleDeque))
        );
    }

    /**
     * Unchecked cast will always return (List<Article>), because method ArticleService.findLastFiveArticles returns List<Article>
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Around(value = "execution(* com.example.demo.service.ArticleService.findLastFiveArticles(..))")
    public List<Article> aroundCallFindLastFiveArticles(ProceedingJoinPoint pjp) {
        var lastFiveArticleRepository = cashingService.findById(CashedId.topFive);

        if (lastFiveArticleRepository.isPresent()) {
            return articleService.findByIdInOrderByDateDesc(lastFiveArticleRepository.get().getArticlesList());
        }

        var articles = (List<Article>) pjp.proceed();

        cashingService.save(
                cashingService.buildById(CashedId.topFive, articles.stream().map(Article::getId).toList())
        );

        return articles;
    }

}
