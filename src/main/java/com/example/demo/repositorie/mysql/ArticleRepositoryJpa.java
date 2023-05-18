package com.example.demo.repositorie.mysql;

import com.example.demo.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepositoryJpa extends CrudRepository<Article, String> {

    @Query("SELECT ar FROM Article ar left join ar.comments c WHERE year(c.published) = 2023 GROUP BY ar.id HAVING SUM(c.likes) > SUM(c.dislikes) ORDER BY ar.date LIMIT 10")
    List<Article> findActualNews();

    List<Article> findByIdInOrderByDateDesc(List<String> idList);

    @Query("SELECT ar FROM Article ar ORDER BY ar.date DESC LIMIT 5")
    List<Article> findLastFiveArticles();
}
