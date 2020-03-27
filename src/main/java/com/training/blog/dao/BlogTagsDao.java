package com.training.blog.dao;

import com.training.blog.model.BlogTags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogTagsDao extends JpaRepository<BlogTags, Integer>{

    
}