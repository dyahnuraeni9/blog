package com.training.blog.dao;

import java.util.List;

import com.training.blog.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer>{

    List<Comment> findByBlogId(Integer blogId);
    
}