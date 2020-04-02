package com.training.blog.service;

import com.training.blog.dto.request.RequestBlogDTO;
import com.training.blog.model.Blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface BlogService {

    public ResponseEntity getById(Integer id);

    public ResponseEntity getAll();

    public ResponseEntity save(RequestBlogDTO requestBlogDTO, MultipartFile file);

    public ResponseEntity update(RequestBlogDTO requestBlogDTO, Integer id);

    public ResponseEntity delete(Integer id);

    public ResponseEntity saveImage(MultipartFile file, Integer id);

}