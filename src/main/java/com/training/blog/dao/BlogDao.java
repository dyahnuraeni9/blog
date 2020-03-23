package com.training.blog.dao;

import com.training.blog.data.model.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogDao extends JpaRepository<Blog, Integer>{
    
}