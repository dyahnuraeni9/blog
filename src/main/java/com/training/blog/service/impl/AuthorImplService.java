package com.training.blog.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.training.blog.dao.AuthorDao;
import com.training.blog.dto.response.ResponseBaseDTO;
import com.training.blog.model.Author;
import com.training.blog.service.AuthorService;
import com.training.blog.service.ExcelGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorImplService implements AuthorService {

    @Autowired
    private AuthorDao AuthorDao;

    @Override
    public ResponseEntity save(Author author) {
        ResponseBaseDTO<Author> responseBaseDTO = new ResponseBaseDTO<>();
        author.setPassword(new BCryptPasswordEncoder().encode(author.getPassword()));
        author.setCreatedAt(new Date());
        author.setUpdatedAt(new Date());
        author = AuthorDao.save(author);
        responseBaseDTO = new ResponseBaseDTO<Author>(200, true, "success", author);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity update(Author author, Integer id) {
        ResponseBaseDTO<Author> responseBaseDTO = new ResponseBaseDTO<>();
        Author author1 = AuthorDao.findById(id).orElse(null);
        if (author1 == null) {
            responseBaseDTO = new ResponseBaseDTO<Author>(404, false, "Author Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        author.setId(id);
        author.setPassword(new BCryptPasswordEncoder().encode(author.getPassword()));
        author.setUpdatedAt(new Date());
        author1 = AuthorDao.save(author);
        responseBaseDTO = new ResponseBaseDTO<Author>(200, true, "success", author1);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity getAllAuthor() {
        ResponseBaseDTO<List<Author>> responseBaseDTO = new ResponseBaseDTO<>();
        List<Author> ListAuthor = AuthorDao.findAll();
        responseBaseDTO = new ResponseBaseDTO<List<Author>>(200, true, "success", ListAuthor);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity getById(Integer id) {
        ResponseBaseDTO<Author> responseBaseDTO = new ResponseBaseDTO<>();
        Author author = AuthorDao.findById(id).orElse(null);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<Author>(404, false, "Author Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        responseBaseDTO = new ResponseBaseDTO<Author>(200, true, "success", author);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity delete(Integer id) {
        ResponseBaseDTO<Author> responseBaseDTO = new ResponseBaseDTO<>();
        Author author = AuthorDao.findById(id).orElse(null);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<Author>(404, false, "Author Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        AuthorDao.deleteById(id);
        responseBaseDTO = new ResponseBaseDTO<Author>(200, true, "success", null);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity updatePassword(String password, Integer id) {
        ResponseBaseDTO<Author> responseBaseDTO = new ResponseBaseDTO<>();
        Author author = AuthorDao.findById(id).orElse(null);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<Author>(404, false, "Author Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        author.setPassword(new BCryptPasswordEncoder().encode(author.getPassword()));
        responseBaseDTO = new ResponseBaseDTO<Author>(200, true, "success", author);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity exportToFile() {
        ResponseBaseDTO<Author> responseBaseDTO = new ResponseBaseDTO<>();
        List<Author> Author = AuthorDao.findAll();
        
        ByteArrayInputStream in;
        try {
            in = ExcelGeneratorService.AuthorToExcel(Author);
        } catch (IOException e) {
            responseBaseDTO = new ResponseBaseDTO<Author>(404, false, "Failed export", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        // return IOUtils.toByteArray(in);
        
        HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
        
         return ResponseEntity
                      .ok()
                      .headers(headers)
                      .body(new InputStreamResource(in));
    }
    
}
