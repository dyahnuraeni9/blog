package com.training.blog.controller.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.training.blog.model.Blog;
import com.training.blog.service.BlogService;
import com.training.blog.service.DocGenerateService;
import com.training.blog.service.FileStorageService;
import com.training.blog.controller.BlogController;
import com.training.blog.dao.BlogDao;
import com.training.blog.dto.request.RequestBlogDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BlogControllerImpl implements BlogController  {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FileStorageService fileStorageService;

    private static final Logger logger = LoggerFactory.getLogger(BlogControllerImpl.class);


    @Override
    public ResponseEntity getAllBlogs() {
        return blogService.getAll();
    }

    @Override
    public ResponseEntity saveBlog(@Valid @RequestBody RequestBlogDTO requestBlogDTO, @RequestParam("file") MultipartFile file) {
        System.out.println(requestBlogDTO);
        return blogService.save(requestBlogDTO, file);
    }

    @Override
    public ResponseEntity UpdateBlog(@Valid @RequestBody RequestBlogDTO requestBlogDTO, @PathVariable("id") Integer id) {
        return  blogService.update(requestBlogDTO, id);
    }

    @Override
    public ResponseEntity getBlog(@PathVariable("id") Integer id)  {
        return blogService.getById(id);
    }

    @Override
    public ResponseEntity deleteBlog(@PathVariable("id") Integer id) {
        return blogService.delete(id);
    }

    @Override
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) {
        return fileStorageService.storeFile(file, id);
    }

    @Override
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        System.out.println(fileName);
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        System.out.println(resource);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @ResponseBody
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity writeToWord(@RequestParam(value = "id") Integer id) throws Exception {
        return blogService.exportToFile(id);
    }


    
}