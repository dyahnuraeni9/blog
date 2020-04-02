package com.training.blog.controller.impl;

import javax.validation.Valid;

import com.training.blog.model.Blog;
import com.training.blog.service.BlogService;
import com.training.blog.controller.BlogController;
import com.training.blog.dto.request.RequestBlogDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BlogImplController implements BlogController  {

    @Autowired
    private BlogService blogService;

    @Override
    public ResponseEntity getAllBlogs() {
        return blogService.getAll();
    }

    @Override
    public ResponseEntity saveBlog(@Valid @RequestBody RequestBlogDTO requestBlogDTO, @RequestParam("file") MultipartFile file) {
        System.out.println(requestBlogDTO);
        return blogService.save(requestBlogDTO, file);
    }

    @Override
    public ResponseEntity UpdateBlog(@Valid @RequestBody RequestBlogDTO requestBlogDTO, @PathVariable("id") Integer id) {
        return  blogService.update(requestBlogDTO, id);
    }

    @Override
    public ResponseEntity getBlog(@PathVariable("id") Integer id)  {
        return blogService.getById(id);
    }

    @Override
    public ResponseEntity deleteBlog(@PathVariable("id") Integer id) {
        return blogService.delete(id);
    }

    @Override
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) {
        return blogService.saveImage(file, id);
    }

    
}