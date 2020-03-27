package com.training.blog.dao;

import com.training.blog.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer>{

    public Author findByUsername(String username);
    
}