package com.training.blog.controller.impl;

import javax.validation.Valid;

import com.training.blog.model.Blog;
import com.training.blog.service.BlogService;
import com.training.blog.controller.BlogController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogImplController implements BlogController  {

    @Autowired
    private BlogService blogService;

    @Override
    public ResponseEntity GetAllBlogs() {
        return blogService.getAll();
    }

    @Override
    public ResponseEntity saveBlog(@Valid @RequestBody final Blog blog) {
        return blogService.saveOrUpdate(blog);
    }

    @Override
    public ResponseEntity UpdateBlog(@Valid @RequestBody final Blog blog, @PathVariable("id") final Integer id) {
        return  blogService.saveOrUpdate(blog);
    }

    @Override
    public ResponseEntity GetBlog(@PathVariable("id") final Integer id)  {
        return blogService.getById(id);
    }
    
}