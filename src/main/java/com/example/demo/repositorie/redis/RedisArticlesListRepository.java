package com.example.demo.repositorie.redis;

import com.example.demo.entity.redis.CashingArticlesList;
import com.example.demo.entity.redis.CashedId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisArticlesListRepository extends CrudRepository<CashingArticlesList, CashedId> {
}
