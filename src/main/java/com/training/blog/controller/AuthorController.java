package com.training.blog.controller;

import java.io.IOException;

import javax.validation.Valid;

import com.training.blog.model.Author;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface AuthorController{

    @GetMapping("/author/{id}")
    public ResponseEntity getAuthor(@PathVariable("id") Integer id);

    @GetMapping("/author")
    public ResponseEntity getAllAuthors();
    
    @PostMapping("/author")
    public ResponseEntity saveAuthor(@Valid @RequestBody Author blog);

    @PutMapping("/author/{id}")
    public ResponseEntity updateAuthor(@Valid @RequestBody Author blog, @PathVariable("id") Integer id);

    @DeleteMapping("/author/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Integer id);

    @GetMapping(value = "/download/authors.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException;


}