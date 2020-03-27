package com.training.blog.service;

import com.training.blog.model.Blog;

import org.springframework.http.ResponseEntity;

public interface BlogService {

    public ResponseEntity getById(Integer id);

    public ResponseEntity getAll();

    public ResponseEntity saveOrUpdate(Blog blog);

}