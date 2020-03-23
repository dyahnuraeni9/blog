package com.training.blog.controller.impl;

import javax.validation.Valid;

import com.training.blog.controller.CommentController;
import com.training.blog.data.model.Blog;
import com.training.blog.data.model.Comment;
import com.training.blog.data.response.ResponseBaseDTO;
import com.training.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentImplController implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity saveOrUpdate(@Valid Comment comment) {
        ResponseBaseDTO<Comment> responseBaseDTO = new ResponseBaseDTO<>();

        Comment comment1 = commentService.saveOrUpdate(comment);
        responseBaseDTO.setCode(200);
        responseBaseDTO.setStatus(true);
        responseBaseDTO.setMessage("success");
        responseBaseDTO.setData(comment1);
        return ResponseEntity.ok(responseBaseDTO);
    }

}