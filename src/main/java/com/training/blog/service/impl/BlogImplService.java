package com.training.blog.service.impl;

import java.util.Date;
import java.util.List;

import com.training.blog.service.BlogService;
//import com.training.blog.dao.AuthorDao;
import com.training.blog.dao.BlogDao;
// import com.training.blog.dao.BlogTagsDao;
// import com.training.blog.dao.CategoriesDao;
import com.training.blog.data.model.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogImplService implements BlogService {

    @Autowired
    private BlogDao blogDao;

    // @Autowired
    // private AuthorDao authorDao;

    // @Autowired
    // private CategoriesDao categoriesDao;

    // @Autowired
    // private BlogTagsDao blogTagsDao;

    // @Override
    public List<Blog> getAll() {
        return blogDao.findAll();
    }

    public Blog saveOrUpdate(Blog blog){
        // Author author = authorDao.findById(blog.getAuthorId()).orElse(null);
        // if (author != null){
        //     return null;
        // }
        // Categories categories = categoriesDao.findById(blog.getCategoriesId()).orElse(null);
        // if (categories != null){
        //     return null;
        // }

        // BlogTags blogTags = blogTagsDao.findById(blog.getBlogTagsId()).orElse(null);
        // if (categories != null){
        //     return null;
        // }

        blog.setUpdatedAt(new Date());
        Blog blog1 = blogDao.findById(blog.getId()).orElse(null);
        if (blog1 == null){
            blog.setCreatedAt(new Date());
            
        }
        return blogDao.save(blog);

    }
    
    
}