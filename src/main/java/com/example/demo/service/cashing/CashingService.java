package com.example.demo.service.cashing;

import com.example.demo.entity.cashing.CashingArticles;
import com.example.demo.entity.cashing.CashedId;

import java.util.Optional;

public interface CashingService {

    Optional<? extends CashingArticles> findById(CashedId cashedId);

    <T extends CashingArticles> void save(T cashingArticlesList);

}
