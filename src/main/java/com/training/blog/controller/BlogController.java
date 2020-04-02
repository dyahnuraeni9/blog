package com.training.blog.controller;

import javax.validation.Valid;

import com.training.blog.dto.request.RequestBlogDTO;
import com.training.blog.model.Blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api")
public interface BlogController{

    @GetMapping("/blog/{id}")
    public ResponseEntity getBlog(@PathVariable("id") Integer id);

    @GetMapping("/blog")
    public ResponseEntity getAllBlogs();
    
    @PostMapping("/blog")
    public ResponseEntity saveBlog(@Valid @RequestBody RequestBlogDTO requestBlogDTO, @RequestParam("file") MultipartFile file);

    @PutMapping("/blog/{id}")
    public ResponseEntity UpdateBlog(@Valid @RequestBody RequestBlogDTO requestBlogDTO, @PathVariable("id") Integer id);

    @DeleteMapping("/blog/{id}")
    public ResponseEntity deleteBlog(@PathVariable("id") Integer id);

    @PostMapping("/uploadFile/{id}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id);

}