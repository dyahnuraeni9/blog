package com.training.blog.dao;

import com.training.blog.data.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Integer>{
    
}