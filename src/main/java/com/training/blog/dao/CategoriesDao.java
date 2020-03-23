package com.training.blog.dao;

import com.training.blog.data.model.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesDao extends JpaRepository<Categories, Integer>{
    
}