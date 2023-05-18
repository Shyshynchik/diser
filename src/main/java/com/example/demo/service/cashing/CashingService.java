package com.example.demo.service.cashing;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashingArticlesList;
import com.example.demo.entity.cashing.CashedId;

import java.util.List;
import java.util.Optional;

public interface CashingService {

    Optional<List<Article>> findById(CashedId cashedId);

    void save(CashedId cashedId, List<Article> list);

    void updateCash(CashedId cashedId, Article article);
}
