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
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao CommentDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public ResponseEntity getAll() {
        ResponseBaseDTO<List<Comment>> responseBaseDTO = new ResponseBaseDTO<>();
        List<Comment> ListComment = CommentDao.findAll();
        responseBaseDTO = new ResponseBaseDTO<List<Comment>>(200, true, "success", ListComment);
        return ResponseEntity.ok(responseBaseDTO);
        
    }

    @Override
    public ResponseEntity save(Comment comment) {
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
        comment.setCreatedAt(new Date());

        comment = CommentDao.save(comment);
        responseBaseDTO = new ResponseBaseDTO<Comment>(200, true, "success", comment);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity update(Comment comment, Integer id) {
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

        Comment comment1 = CommentDao.findById(id).orElse(null);
        if (comment1 == null) {
            responseBaseDTO = new ResponseBaseDTO<Comment>(404, false, "Comment Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);

        }
        comment.setId(id);
        comment.setUpdatedAt(new Date());

        comment1 = CommentDao.save(comment);
        responseBaseDTO = new ResponseBaseDTO<Comment>(200, true, "success", comment1);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity delete(Integer id) {
        ResponseBaseDTO<Comment> responseBaseDTO = new ResponseBaseDTO<>();
        Comment comment = CommentDao.findById(id).orElse(null);
        if (comment == null){
            responseBaseDTO = new ResponseBaseDTO<Comment>(404, false, "comment Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        CommentDao.deleteById(id);
        responseBaseDTO = new ResponseBaseDTO<Comment>(200, true, "success", null);
        return ResponseEntity.ok(responseBaseDTO);
    }

    // @Override
    // public ResponseEntity getByBlogId(Integer id) {
    //     ResponseBaseDTO<Comment> responseBaseDTO = new ResponseBaseDTO<>();
    //     Blog blog = blogDao.findById(id).orElse(null);
    //     if (blog == null){
    //         responseBaseDTO = new ResponseBaseDTO<Comment>(404, false, "Blog Not Found", null);
    //         return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
    //     }

    //     List<Comment> comment = CommentDao.findByBlogId(id);
    //     if (comment.size() == 0){
    //         responseBaseDTO = new ResponseBaseDTO<Comment>(404, false, "comment Not Found", null);
    //         return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
    //     }
    //     responseBaseDTO = new ResponseBaseDTO<Comment>(200, true, "success", null);
    //     return ResponseEntity.ok(responseBaseDTO);
    // }
}