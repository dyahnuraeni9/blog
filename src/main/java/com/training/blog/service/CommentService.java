package com.training.blog.service;

import java.util.List;

import com.training.blog.model.Comment;

import org.springframework.http.ResponseEntity;

public interface CommentService {

    public ResponseEntity getAll();

    public ResponseEntity save(Comment comment);

    public ResponseEntity update(Comment comment, Integer id);

    public ResponseEntity delete(Integer id);
}