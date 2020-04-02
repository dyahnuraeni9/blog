package com.training.blog.controller;

import javax.validation.Valid;

import com.training.blog.model.Comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface CommentController{

    @GetMapping("/comments")
    public ResponseEntity GetAllComments();
    
    @PostMapping("/comment")
    public ResponseEntity saveComment(@Valid @RequestBody Comment comment);

    @PutMapping("/comment/{id}")
    public ResponseEntity updateComment(@Valid @RequestBody Comment comment, @PathVariable("id") Integer id);

    @DeleteMapping("/comment/{id}")
    public ResponseEntity deleteCategories(@PathVariable("id") Integer id);

}