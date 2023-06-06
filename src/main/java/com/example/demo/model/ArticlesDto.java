package com.example.demo.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticlesDto {

    private List<ArticleDto> articleDtos;


    @Data
    @Builder
    public static class ArticleDto {
        private String title;
        private String text;
    }

}
