package com.training.blog.dao;

import com.training.blog.data.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer>{
    
}