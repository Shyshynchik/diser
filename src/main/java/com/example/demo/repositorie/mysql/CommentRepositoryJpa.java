package com.example.demo.repositorie.mysql;

import com.example.demo.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepositoryJpa extends CrudRepository<Comment, String> {
}
