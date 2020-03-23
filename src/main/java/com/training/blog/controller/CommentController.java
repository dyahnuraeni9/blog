package com.training.blog.controller;

import javax.validation.Valid;

import com.training.blog.data.model.Comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface CommentController{

    
    @PostMapping("/comment")
    public ResponseEntity saveOrUpdate(@Valid @RequestBody Comment comment);

}