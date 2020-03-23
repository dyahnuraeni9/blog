package com.training.blog.controller.impl;

import java.util.List;

import javax.validation.Valid;

import com.training.blog.data.model.Blog;
import com.training.blog.data.response.ResponseBaseDTO;
import com.training.blog.service.BlogService;
import com.training.blog.controller.BlogController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogImplController implements BlogController  {

    @Autowired
    private BlogService blogService;

    @Override
    public ResponseEntity GetAll() {
        ResponseBaseDTO<List<Blog>> responseBaseDTO = new ResponseBaseDTO<>();
        List<Blog> listBlog= blogService.getAll();
        responseBaseDTO.setCode(200);
        responseBaseDTO.setStatus(true);
        responseBaseDTO.setMessage("success");
        responseBaseDTO.setData(listBlog);

        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity saveOrUpdate(@Valid @RequestBody Blog blog) {
        ResponseBaseDTO<Blog> responseBaseDTO = new ResponseBaseDTO<>();

        Blog blog1 = blogService.saveOrUpdate(blog);
        responseBaseDTO.setCode(200);
        responseBaseDTO.setStatus(true);
        responseBaseDTO.setMessage("success");
        responseBaseDTO.setData(blog1);
        return ResponseEntity.ok(responseBaseDTO);
    }
    
}