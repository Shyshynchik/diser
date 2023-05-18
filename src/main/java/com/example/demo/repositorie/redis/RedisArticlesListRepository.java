package com.example.demo.repositorie.redis;

import com.example.demo.entity.cashing.redis.RedisCashingArticlesList;
import com.example.demo.entity.cashing.CashedId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisArticlesListRepository extends CrudRepository<RedisCashingArticlesList, CashedId> {
}
