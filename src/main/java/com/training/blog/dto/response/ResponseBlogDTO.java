package com.training.blog.dto.response;

import java.util.Date;
import java.util.List;

import com.training.blog.model.Author;
import com.training.blog.model.Categories;
import com.training.blog.model.Comment;
import com.training.blog.model.Tags;

import lombok.Data;

@Data
public class ResponseBlogDTO {

    private int id;
    private Author author;
    private Categories categories;
    private List<Tags> tags;
    private List<Comment> comments;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
 
}
