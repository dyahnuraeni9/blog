package com.training.blog.dao;

import java.util.List;

import com.training.blog.model.BlogTags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogTagsDao extends JpaRepository<BlogTags, Integer>{

    public void deleteByBlogId(Integer blogId);

    public BlogTags findByBlogIdAndTagsId(Integer blogId, Integer TagsId);

    public List<BlogTags> findByBlogId(Integer blogId);
    
}