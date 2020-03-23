package com.training.blog.service.impl;

import java.util.Date;

//import com.training.blog.dao.BlogDao;
import com.training.blog.dao.CommentDao;
import com.training.blog.data.model.Comment;
import com.training.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImplService implements CommentService {

    @Autowired
    private CommentDao CommentDao;

    // @Autowired
    // private BlogDao blogDao;

    @Override
    public Comment saveOrUpdate(Comment comment) {

        //Blog blog = blogDao.findById(blog.getBlogId()).orElse(null);
        // if (blog != null){
        //     return null;
        // }

        comment.setUpdatedAt(new Date());
        Comment comment1 = CommentDao.findById(comment.getId()).orElse(null);
        if (comment1 == null){
            comment.setCreatedAt(new Date());
            
        }
        return CommentDao.save(comment);
    }
}