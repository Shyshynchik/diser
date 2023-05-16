package com.example.demo.repositorie.redis;

import com.example.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepositoryRedis extends CrudRepository<Article, UUID> {
}
