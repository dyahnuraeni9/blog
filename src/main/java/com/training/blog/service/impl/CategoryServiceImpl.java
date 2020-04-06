package com.training.blog.service.impl;

import java.util.Date;
import java.util.List;

import com.training.blog.dao.CategoriesDao;
import com.training.blog.dto.response.ResponseBaseDTO;
import com.training.blog.model.Categories;
import com.training.blog.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    public ResponseEntity save(Categories categories) {
        ResponseBaseDTO<Categories> responseBaseDTO = new ResponseBaseDTO<>();
        categories.setCreatedAt(new Date());
        categories.setUpdatedAt(new Date());
        categories = categoriesDao.save(categories);
        responseBaseDTO = new ResponseBaseDTO<Categories>(200, true, "success", categories);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity update(Categories categories, Integer id) {
        ResponseBaseDTO<Categories> responseBaseDTO = new ResponseBaseDTO<>();
        Categories Categories1 = categoriesDao.findById(id).orElse(null);
        if (Categories1 == null){
            responseBaseDTO = new ResponseBaseDTO<Categories>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        categories.setId(id);
        categories.setUpdatedAt(new Date());
        categories = categoriesDao.save(categories);
        responseBaseDTO = new ResponseBaseDTO<Categories>(200, true, "success", categories);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity getAllCategories() {
        ResponseBaseDTO<List<Categories>> responseBaseDTO = new ResponseBaseDTO<>();
        List<Categories> ListCategories = categoriesDao.findAll();
        responseBaseDTO = new ResponseBaseDTO<List<Categories>>(200, true, "success", ListCategories);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity getById(Integer id) {
        ResponseBaseDTO<Categories> responseBaseDTO = new ResponseBaseDTO<>();
        Categories Categories = categoriesDao.findById(id).orElse(null);
        if (Categories == null){
            responseBaseDTO = new ResponseBaseDTO<Categories>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        responseBaseDTO = new ResponseBaseDTO<Categories>(200, true, "success", Categories);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity delete(Integer id) {
        ResponseBaseDTO<Categories> responseBaseDTO = new ResponseBaseDTO<>();
        Categories Categories = categoriesDao.findById(id).orElse(null);
        if (Categories == null){
            responseBaseDTO = new ResponseBaseDTO<Categories>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        categoriesDao.deleteById(id);
        responseBaseDTO = new ResponseBaseDTO<Categories>(200, true, "success", null);
        return ResponseEntity.ok(responseBaseDTO);
    }

}