package com.training.blog.dao;

import com.training.blog.data.model.Tags;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsDao extends JpaRepository<Tags, Integer>{
    
}