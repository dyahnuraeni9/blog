package com.training.blog.controller.impl;

import javax.validation.Valid;

import com.training.blog.controller.CommentController;
import com.training.blog.model.Comment;
import com.training.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentImplController implements CommentController {

    @Autowired
    private CommentService commentService;

    @Override
    public ResponseEntity saveComment(@Valid @RequestBody Comment comment) {
        System.out.println(comment);
        return commentService.save(comment);
    }

    @Override
    public ResponseEntity GetAllComments() {
        return commentService.getAll();
    }

    @Override
    public ResponseEntity updateComment(@Valid @RequestBody Comment comment, @PathVariable("id") Integer id) {
        return commentService.update(comment, id);
    }

    @Override
    public ResponseEntity deleteCategories(@PathVariable("id") Integer id) {
        return commentService.delete(id);
    }

}