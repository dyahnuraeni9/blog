package com.training.blog.dao;

import java.util.List;

import com.training.blog.model.Tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsDao extends JpaRepository<Tags, Integer>{
    
}