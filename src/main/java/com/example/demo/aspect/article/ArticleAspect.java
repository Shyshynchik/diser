package com.example.demo.aspect.article;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashedId;
import com.example.demo.service.cashing.CashingService;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Aspect
public class ArticleAspect {

    private final CashingService cashingService;

    public ArticleAspect(@Qualifier("templateService") CashingService cashingService) {
        this.cashingService = cashingService;
    }

    /**
     * Unchecked cast will always return (List<Article>), because method ArticleService.findActualArticles returns List<Article>
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Around(value = "execution(* com.example.demo.service.ArticleService.findActualArticles(..))")
    public List<Article> aroundCallFindActualArticles(ProceedingJoinPoint pjp) {
        var popularArticlesOpt = cashingService.findById(CashedId.popular);

        if (popularArticlesOpt.isPresent()) {
            return popularArticlesOpt.get();
        }

        var articles = (List<Article>) pjp.proceed();

        cashingService.save(CashedId.popular, articles);

        return articles;
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.service.ArticleService.save(..))", returning = "retVal")
    public void afterReturnSave(JoinPoint jp, Object retVal) {
        cashingService.updateCash(CashedId.topFive, (Article) retVal);
    }

    /**
     * Unchecked cast will always return (List<Article>), because method ArticleService.findLastFiveArticles returns List<Article>
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Around(value = "execution(* com.example.demo.service.ArticleService.findLastFiveArticles(..))")
    public List<Article> aroundCallFindLastFiveArticles(ProceedingJoinPoint pjp) {
        var lastFiveArticles = cashingService.findById(CashedId.topFive);

        if (lastFiveArticles.isPresent()) {
            return lastFiveArticles.get();
        }

        var articles = (List<Article>) pjp.proceed();

        cashingService.save(CashedId.topFive, articles);

        return articles;
    }

}
