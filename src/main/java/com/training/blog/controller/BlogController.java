package com.training.blog.controller;

import javax.validation.Valid;

import com.training.blog.model.Blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface BlogController{

    @GetMapping("/blog/{id}")
    public ResponseEntity GetBlog(@PathVariable("id") Integer id);

    @GetMapping("/blogs")
    public ResponseEntity GetAllBlogs();
    
    @PostMapping("/blog")
    public ResponseEntity saveBlog(@Valid @RequestBody Blog blog);

    @PutMapping("/blog")
    public ResponseEntity UpdateBlog(@Valid @RequestBody Blog blog, @PathVariable("id") Integer id);

}