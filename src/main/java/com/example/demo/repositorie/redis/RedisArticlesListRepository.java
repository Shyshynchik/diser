package com.example.demo.repositorie.redis;

import com.example.demo.entity.redis.RedisArticlesList;
import com.example.demo.entity.redis.RedisIds;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisArticlesListRepository extends CrudRepository<RedisArticlesList, RedisIds> {
}
