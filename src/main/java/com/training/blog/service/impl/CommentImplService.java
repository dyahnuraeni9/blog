package com.training.blog.service.impl;

import java.util.Date;
import java.util.List;

import com.training.blog.dao.BlogDao;
import com.training.blog.dao.CommentDao;
import com.training.blog.dto.response.ResponseBaseDTO;
import com.training.blog.model.Blog;
import com.training.blog.model.Comment;
import com.training.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentImplService implements CommentService {

    @Autowired
    private CommentDao CommentDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public ResponseEntity saveOrUpdate(Comment comment) {
        ResponseBaseDTO<Comment> responseBaseDTO = new ResponseBaseDTO<>();

        if(comment.getGuestEmail() == "" ) {
            responseBaseDTO = new ResponseBaseDTO<Comment>(400, false, "Bad Request", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.BAD_REQUEST);
        }

        Blog blog = blogDao.findById(comment.getBlogId()).orElse(null);
        if (blog == null){
            responseBaseDTO = new ResponseBaseDTO<Comment>(404, false, "Blog Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        comment.setUpdatedAt(new Date());
        Comment comment1 = CommentDao.findById(comment.getId()).orElse(null);
        if (comment1 == null) {
            comment.setCreatedAt(new Date());

        }
        comment1 = CommentDao.save(comment);
        responseBaseDTO = new ResponseBaseDTO<Comment>(200, true, "success", comment1);
        return ResponseEntity.ok(responseBaseDTO);
       
    }

    @Override
    public ResponseEntity getAll() {
        ResponseBaseDTO<List<Comment>> responseBaseDTO = new ResponseBaseDTO<>();
        List<Comment> ListComment = CommentDao.findAll();
        responseBaseDTO = new ResponseBaseDTO<List<Comment>>(200, true, "success", ListComment);
        return ResponseEntity.ok(responseBaseDTO);
        
    }
}