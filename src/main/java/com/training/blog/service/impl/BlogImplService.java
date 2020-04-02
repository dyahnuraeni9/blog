package com.training.blog.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.training.blog.service.BlogService;
import com.training.blog.dao.AuthorDao;
import com.training.blog.dao.BlogDao;
import com.training.blog.dao.BlogTagsDao;
import com.training.blog.dao.CategoriesDao;
import com.training.blog.dao.CommentDao;
import com.training.blog.dao.TagsDao;
import com.training.blog.dto.request.RequestBlogDTO;
import com.training.blog.dto.response.ResponseBaseDTO;
import com.training.blog.dto.response.ResponseBlogDTO;
import com.training.blog.dto.response.ResponseUploadFileDTO;
import com.training.blog.exception.FileStorageException;
import com.training.blog.mapper.BlogMapper;
import com.training.blog.model.Author;
import com.training.blog.model.Blog;
import com.training.blog.model.BlogTags;
import com.training.blog.model.Categories;
import com.training.blog.model.Comment;
import com.training.blog.model.Tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.micrometer.core.instrument.Tag;

@Service
public class BlogImplService implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private BlogTagsDao blogTagsDao;

    @Autowired
    private TagsDao tagsDao;

    @Autowired
    private CommentDao commentDao;

    private BlogMapper blogMapper;

    @Override
    public ResponseEntity getAll() {
        List<Blog> listBlog = blogDao.findAll();
        ResponseBaseDTO<List<Blog>> responseBaseDTO = new ResponseBaseDTO<>(200, true, "success", listBlog);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity getById(Integer id) {
        System.out.println(id);
        ResponseBaseDTO<ResponseBlogDTO> responseBaseDTO = new ResponseBaseDTO<>();
        ResponseBlogDTO responseBlogDTO = new ResponseBlogDTO();
        List<Tags> listTags = new ArrayList<>();

        Blog blog = blogDao.findById(id).orElse(null);
        System.out.println(blog);
        if (blog == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Blog Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        Author author = authorDao.findById(blog.getAuthorId()).orElse(null);
        System.out.println(author);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Author Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        Categories categories = categoriesDao.findById(blog.getCategoriesId()).orElse(null);
        System.out.println(categories);
        if (categories == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        List<BlogTags> listBlogTags = blogTagsDao.findByBlogId(id);
        System.out.println(listBlogTags);
        if (listBlogTags.size()== 0) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "BlogTags Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        for (BlogTags blogTags : listBlogTags){
            System.out.println("blogTags.getTagsId() " +blogTags.getTagsId());
            Tags tag = tagsDao.findById(blogTags.getTagsId()).orElse(null);
            if (tag == null) {
                responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Tags Not Found", null);
                return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
            }
            listTags.add(tag);
        }
        System.out.println("blog.getId()" + blog.getId());
        List<Comment> Comments = commentDao.findByBlogId(blog.getId());
        System.out.println("Comments" + Comments);
        if (Comments.size() == 0){
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Comments Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        responseBlogDTO.setId(blog.getId());
        responseBlogDTO.setTitle(blog.getTitle());
        responseBlogDTO.setContent(blog.getContent());
        responseBlogDTO.setCreatedAt(blog.getCreatedAt());
        responseBlogDTO.setUpdatedAt(blog.getUpdatedAt());
        responseBlogDTO.setAuthor(author);
        responseBlogDTO.setCategories(categories);
        responseBlogDTO.setTags(listTags);
        responseBlogDTO.setComments(Comments);

        responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(200, true, "success", responseBlogDTO);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity save(RequestBlogDTO requestBlogDTO,  MultipartFile file) {
        ResponseBaseDTO<ResponseBlogDTO> responseBaseDTO = new ResponseBaseDTO<>();
        ResponseBlogDTO responseBlogDTO = new ResponseBlogDTO();
        Blog blog = new Blog();
        List<Tags> listTags = new ArrayList<>();
        

        if(requestBlogDTO.getAuthorId() == 0 || requestBlogDTO.getCategoriesId() == 0 ){
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(400, false, "Bad Request", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.BAD_REQUEST);
        }

        Author author = authorDao.findById(requestBlogDTO.getAuthorId()).orElse(null);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Author Not Found" , null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        Categories categories = categoriesDao.findById(requestBlogDTO.getCategoriesId()).orElse(null);
        if (categories == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        for (Integer tagId: requestBlogDTO.getListTags()){
            Tags tags = tagsDao.findById(tagId).orElse(null);
            if (tags == null) {
                responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Tag Not Found", null);
                return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
            }
            listTags.add(tags);
        }
        blog.setCategoriesId(requestBlogDTO.getCategoriesId());
        blog.setAuthorId(requestBlogDTO.getAuthorId());
        blog.setTitle(requestBlogDTO.getTitle());
        blog.setContent(requestBlogDTO.getContent());
        blog.setUpdatedAt(new Date());
        blog.setCreatedAt(new Date());
        System.out.println(blog);
        blog = blogDao.save(blog);

        BlogTags blogTags = new BlogTags();
        blogTags.setBlogId(blog.getId());

        for (Integer tagId: requestBlogDTO.getListTags()){
            blogTags.setTagsId(tagId);
            blogTags.setUpdatedAt(new Date());
            blogTags.setCreatedAt(new Date());
            blogTags = blogTagsDao.save(blogTags);
        }

        responseBlogDTO.setId(blog.getId());
        responseBlogDTO.setTitle(blog.getTitle());
        responseBlogDTO.setContent(blog.getContent());
        responseBlogDTO.setCreatedAt(blog.getCreatedAt());
        responseBlogDTO.setUpdatedAt(blog.getUpdatedAt());
        responseBlogDTO.setAuthor(author);
        responseBlogDTO.setCategories(categories);
        responseBlogDTO.setTags(listTags);
        responseBlogDTO.setComments(null);

        responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(200, true, "success", responseBlogDTO);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity update(RequestBlogDTO requestBlogDTO, Integer id) {
        ResponseBaseDTO<ResponseBlogDTO> responseBaseDTO = new ResponseBaseDTO<>();
        ResponseBlogDTO responseBlogDTO = new ResponseBlogDTO();
        List<Tags> listTags = new ArrayList<>();
        Blog blog = new Blog();
        
        if(requestBlogDTO.getAuthorId() == 0 || requestBlogDTO.getCategoriesId() == 0 ){
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(400, false, "Bad Request", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.BAD_REQUEST);
        }

        Author author = authorDao.findById(requestBlogDTO.getAuthorId()).orElse(null);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Author Not Found" , null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        Categories categories = categoriesDao.findById(requestBlogDTO.getCategoriesId()).orElse(null);
        if (categories == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        for (Integer tagId: requestBlogDTO.getListTags()){
            Tags tags = tagsDao.findById(tagId).orElse(null);
            if (tags == null) {
                responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Tag Not Found", null);
                return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
            }

            listTags.add(tags);
            System.out.println(listTags);
        }

        blog.setId(id);
        blog.setCategoriesId(requestBlogDTO.getCategoriesId());
        blog.setAuthorId(requestBlogDTO.getAuthorId());
        blog.setTitle(requestBlogDTO.getTitle());
        blog.setContent(requestBlogDTO.getContent());
        blog.setUpdatedAt(new Date());
        blog = blogDao.save(blog);

        List<BlogTags> listBlogTag = blogTagsDao.findByBlogId(blog.getId());
        for (BlogTags blogTags: listBlogTag){
            blogTagsDao.deleteById(blogTags.getId());
        }

        
        for (Integer tagId: requestBlogDTO.getListTags()){
            BlogTags blogTag = new BlogTags();
            blogTag.setBlogId(blog.getId());
            blogTag.setTagsId(tagId);
            blogTag.setUpdatedAt(new Date());
            blogTag.setCreatedAt(new Date());
            blogTag = blogTagsDao.save(blogTag);    
        }
        
        responseBlogDTO.setId(blog.getId());
        responseBlogDTO.setTitle(blog.getTitle());
        responseBlogDTO.setContent(blog.getContent());
        responseBlogDTO.setCreatedAt(blog.getCreatedAt());
        responseBlogDTO.setUpdatedAt(blog.getUpdatedAt());
        responseBlogDTO.setAuthor(author);
        responseBlogDTO.setCategories(categories);
        responseBlogDTO.setTags(listTags);
        responseBlogDTO.setComments(null);

        responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(200, true, "success", responseBlogDTO);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity delete(Integer id) {
        ResponseBaseDTO<Blog> responseBaseDTO = new ResponseBaseDTO<>();
        Blog blog = blogDao.findById(id).orElse(null);
        if (blog == null){
            responseBaseDTO = new ResponseBaseDTO<Blog>(404, false, "blog Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        blogDao.deleteById(id);
        responseBaseDTO = new ResponseBaseDTO<Blog>(200, true, "success", null);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity saveImage(MultipartFile file, Integer id) {
        ResponseBaseDTO<ResponseUploadFileDTO> responseBaseDTO = new ResponseBaseDTO<>();
        ResponseUploadFileDTO responseUploadFileDTO = new ResponseUploadFileDTO();
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                return null;
            }
           Blog blog = blogDao.findById(id).orElse(null);
           if (blog == null){
                responseBaseDTO = new ResponseBaseDTO<ResponseUploadFileDTO>(404, false, "blog Not Found", null);
                return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
            }
           blog.setImage(file.getBytes());

           blogDao.save(blog);
          

           String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
           .path("/downloadFile/")
           .path(Integer.toString(blog.getId()))
           .toUriString();

           responseUploadFileDTO.setImageURL(fileDownloadUri);
           responseUploadFileDTO.setType(file.getContentType());
           responseUploadFileDTO.setSize(file.getSize());

           responseBaseDTO = new ResponseBaseDTO<ResponseUploadFileDTO>(200, true, "success", responseUploadFileDTO);
           return ResponseEntity.ok(responseBaseDTO);
        } catch (IOException ex) {
            responseBaseDTO = new ResponseBaseDTO<ResponseUploadFileDTO>(500, false, "Failed upload image", null);
                return new ResponseEntity<>(responseBaseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}