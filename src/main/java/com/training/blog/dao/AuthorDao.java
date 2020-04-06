package com.training.blog.dao;

import java.util.List;

import com.training.blog.model.Author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer>{

    public Author findByUsername(String username);

    // @Query("select a from Author a order by a.authorId desc")
    // public Page<Author> getUsingPagination(Pageable pageable);
    
}