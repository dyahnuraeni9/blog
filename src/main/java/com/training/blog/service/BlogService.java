package com.training.blog.service;

import java.util.List;

import com.training.blog.dto.response.ResponseBlogDTO;
import com.training.blog.model.Blog;

import org.springframework.http.ResponseEntity;

public interface BlogService {

    public ResponseEntity getById(Integer id);

    public ResponseEntity getAll();

    public ResponseEntity saveOrUpdate(Blog blog);

}