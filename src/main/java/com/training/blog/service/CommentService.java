package com.training.blog.service;

import java.util.List;

import com.training.blog.model.Comment;

import org.springframework.http.ResponseEntity;

public interface CommentService {

    public ResponseEntity getAll();

    public ResponseEntity saveOrUpdate(Comment comment);

}