package com.training.blog.service.impl;

import java.util.Date;
import java.util.List;

import com.training.blog.service.BlogService;
import com.training.blog.dao.AuthorDao;
import com.training.blog.dao.BlogDao;
import com.training.blog.dao.BlogTagsDao;
import com.training.blog.dao.CategoriesDao;
import com.training.blog.dao.CommentDao;
import com.training.blog.dao.TagsDao;
import com.training.blog.dto.response.ResponseBaseDTO;
import com.training.blog.dto.response.ResponseBlogDTO;
import com.training.blog.enums.StatusEnum;
import com.training.blog.exception.DataException;
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

    @Override
    public ResponseEntity getAll() {
        List<Blog> listBlog = blogDao.findAll();
        ResponseBaseDTO<List<Blog>> responseBaseDTO = new ResponseBaseDTO<>(200, true, "success", listBlog);
        return ResponseEntity.ok(responseBaseDTO);
    }

    @Override
    public ResponseEntity saveOrUpdate(Blog blog) {
        ResponseBaseDTO<Blog> responseBaseDTO = new ResponseBaseDTO<>();

        if(blog.getAuthorId() == 0 || blog.getCategoriesId() == 0 ){
            responseBaseDTO = new ResponseBaseDTO<Blog>(400, false, "Bad Request", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.BAD_REQUEST);
        }

        Author author = authorDao.findById(blog.getAuthorId()).orElse(null);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<Blog>(404, false, "Author Not Found" , null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        Categories categories = categoriesDao.findById(blog.getCategoriesId()).orElse(null);
        if (categories == null) {
            responseBaseDTO = new ResponseBaseDTO<Blog>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        BlogTags blogTags = blogTagsDao.findById(blog.getBlogTagsId()).orElse(null);
        if (blogTags == null) {
            responseBaseDTO = new ResponseBaseDTO<Blog>(404, false, "BlogTags Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        blog.setUpdatedAt(new Date());
        Blog blog1 = blogDao.findById(blog.getId()).orElse(null);
        if (blog1 == null) {
            blog.setCreatedAt(new Date());
        }

        blog1 = blogDao.save(blog);
        responseBaseDTO = new ResponseBaseDTO<Blog>(200, true, "success", blog1);
        return ResponseEntity.ok(responseBaseDTO);

    }

    @Override
    public ResponseEntity getById(Integer id) {
        ResponseBaseDTO<ResponseBlogDTO> responseBaseDTO = new ResponseBaseDTO<>();
        ResponseBlogDTO responseBlogDTO = new ResponseBlogDTO();

        Blog blog = blogDao.findById(id).orElse(null);
        if (blog == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Blog Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        Author author = authorDao.findById(blog.getAuthorId()).orElse(null);
        if (author == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Author Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        Categories categories = categoriesDao.findById(blog.getCategoriesId()).orElse(null);
        if (categories == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Categories Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        BlogTags blogTags = blogTagsDao.findById(blog.getBlogTagsId()).orElse(null);
        if (blogTags == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "BlogTags Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        Tags Tag = tagsDao.findById(blogTags.getTagsId()).orElse(null);
        if (Tag == null) {
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Tags Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }
        List<Comment> Comments = commentDao.findByBlogId(blog.getId());
        if (Comments.size() == 0){
            responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(404, false, "Comments Not Found", null);
            return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
        }

        responseBlogDTO.setAuthor(author);
        responseBlogDTO.setCategories(categories);
        responseBlogDTO.setContent(blog.getContent());
        responseBlogDTO.setTitle(blog.getTitle());
        responseBlogDTO.setBlogTagsId(blog.getBlogTagsId());
        responseBlogDTO.setComments(Comments);
        responseBlogDTO.setTag(Tag);
        responseBlogDTO.setCreatedAt(blog.getCreatedAt());
        responseBlogDTO.setUpdatedAt(blog.getUpdatedAt());

        responseBaseDTO = new ResponseBaseDTO<ResponseBlogDTO>(200, true, "success", responseBlogDTO);
        return ResponseEntity.ok(responseBaseDTO);
    }
    
    
}