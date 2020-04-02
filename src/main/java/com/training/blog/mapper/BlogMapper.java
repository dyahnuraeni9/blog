package com.training.blog.mapper;

import java.util.List;

import com.training.blog.dto.response.ResponseBlogDTO;
import com.training.blog.model.Author;
import com.training.blog.model.Blog;
import com.training.blog.model.Categories;
import com.training.blog.model.Comment;
import com.training.blog.model.Tags;

public class BlogMapper {


    public ResponseBlogDTO convert(Blog blog, List<Tags> listTag,List<Comment> comments, Author author, Categories categories){
        System.out.println(" start1 " + blog);
        System.out.println(" start2 " + listTag);
        System.out.println(" start3 " + comments);
        System.out.println(" start4 " + author);
        System.out.println(" start5 " + categories);
        ResponseBlogDTO responseBlogDTO = new ResponseBlogDTO();
        responseBlogDTO.setId(blog.getId());
        responseBlogDTO.setTitle(blog.getTitle());
        responseBlogDTO.setContent(blog.getContent());
        responseBlogDTO.setCreatedAt(blog.getCreatedAt());
        responseBlogDTO.setUpdatedAt(blog.getUpdatedAt());
        responseBlogDTO.setAuthor(author);
        responseBlogDTO.setCategories(categories);
        responseBlogDTO.setTags(listTag);
        responseBlogDTO.setComments(comments);
    
        return responseBlogDTO;
    }

}
