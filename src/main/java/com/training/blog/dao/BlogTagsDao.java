package com.training.blog.dao;

import com.training.blog.data.model.BlogTags;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogTagsDao extends JpaRepository<BlogTags, Integer>{
    
}