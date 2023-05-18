package com.example.demo.service.cashing;

import com.example.demo.entity.redis.CashedId;
import com.example.demo.entity.redis.CashingArticlesList;

import java.util.Optional;

public interface CashingService<T, ID> {

    Optional<T> findById(ID cashedId);

    void save(T cashingArticlesList);

}
