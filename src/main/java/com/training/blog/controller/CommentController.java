package com.training.blog.controller;

import javax.validation.Valid;

import com.training.blog.model.Comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface CommentController{

    @GetMapping("/commments")
    public ResponseEntity GetAllComments();
    
    @PostMapping("/comment")
    public ResponseEntity saveComment(@Valid @RequestBody Comment comment);

}