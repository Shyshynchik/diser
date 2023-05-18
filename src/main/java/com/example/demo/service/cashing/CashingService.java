package com.example.demo.service.cashing;

import com.example.demo.entity.Article;
import com.example.demo.entity.cashing.CashingArticlesList;
import com.example.demo.entity.cashing.CashedId;

import java.util.List;
import java.util.Optional;

public interface CashingService {

    Optional<? extends CashingArticlesList> findById(CashedId cashedId);

    <T extends CashingArticlesList> void save(T cashingArticlesList);

    CashingArticlesList buildById(CashedId cashedId, List<String> list);

    <T extends CashingArticlesList> List<Article> findArticlesByCash(T cash);

}
