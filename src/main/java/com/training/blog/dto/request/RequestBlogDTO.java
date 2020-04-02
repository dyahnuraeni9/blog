package com.training.blog.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class RequestBlogDTO {

    private int id;
    private int authorId;
    private int categoriesId;
    private String title;
    private String content;
    private List<Integer> listTags;
    

}