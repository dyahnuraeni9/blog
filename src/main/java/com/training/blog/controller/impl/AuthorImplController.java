package com.training.blog.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.training.blog.controller.AuthorController;
import com.training.blog.model.Author;
import com.training.blog.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorImplController implements AuthorController {

    @Autowired
    private AuthorService authorService;

    @Override
    public ResponseEntity getAuthor(Integer id) {
        return authorService.getById(id);
    }

    @Override
    public ResponseEntity getAllAuthors() {
        return authorService.getAllAuthor();
    }

    @Override
    public ResponseEntity saveAuthor(@Valid Author author) {
        return authorService.save(author);
    }

    @Override
    public ResponseEntity updateAuthor(@Valid Author author, Integer id) {
        return authorService.update(author, id);
    }

    @Override
    public ResponseEntity deleteAuthor(Integer id) {
        return authorService.delete(id);
    }

    @Override
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
       return authorService.exportToFile();
        }


}