package com.training.blog.controller.impl;

import javax.validation.Valid;

import com.training.blog.controller.CommentController;
import com.training.blog.model.Comment;
import com.training.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentImplController implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity saveComment(@Valid @RequestBody Comment comment) {
        return commentService.saveOrUpdate(comment);
    }

    @Override
    public ResponseEntity GetAllComments() {
        return commentService.getAll();
    }

}