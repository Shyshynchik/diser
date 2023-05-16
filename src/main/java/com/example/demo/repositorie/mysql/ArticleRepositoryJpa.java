package com.example.demo.repositorie.mysql;

import com.example.demo.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepositoryJpa extends CrudRepository<Article, UUID> {

    @Query("SELECT ar FROM Article ar left join Comment ")
    List<Article> findActualNews();

}
