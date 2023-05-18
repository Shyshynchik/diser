package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@RedisHash("Article")
@Data
@Entity
@Table(name = "articles", schema = "test")
public class Article implements Serializable {

    @Id
    @Column(name = "article_id")
    private String id;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "views")
    private int views;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments;



}
