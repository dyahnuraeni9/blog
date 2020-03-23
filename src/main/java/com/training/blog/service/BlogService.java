package com.training.blog.service;

import java.util.List;
import com.training.blog.data.model.Blog;

public interface BlogService {

    public List<Blog> getAll();

    public Blog saveOrUpdate(Blog blog);

}