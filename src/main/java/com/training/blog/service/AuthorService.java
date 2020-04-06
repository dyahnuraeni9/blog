package com.training.blog.service;

import java.io.IOException;

import com.training.blog.model.Author;

import org.springframework.http.ResponseEntity;

public interface AuthorService {

    public ResponseEntity save(Author author);

    public ResponseEntity update(Author author, Integer id);

    public ResponseEntity getAllAuthor();

    public ResponseEntity getById(Integer id);

    public ResponseEntity delete(Integer id);

    public ResponseEntity updatePassword(String password, Integer id);

    public ResponseEntity exportToFile() throws IOException;

    public ResponseEntity getAllAuthors(Integer pageNo, Integer pageSize, String sortBy);

    

}