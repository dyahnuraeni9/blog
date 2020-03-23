package com.training.blog.service;

import com.training.blog.data.model.Comment;

public interface CommentService {

    public Comment saveOrUpdate(Comment comment);

}