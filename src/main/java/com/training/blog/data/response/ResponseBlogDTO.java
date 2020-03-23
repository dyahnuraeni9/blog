package com.training.blog.data.response;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseBlogDTO {

    private int id;
    private int authorId;
    private int categoriesId;
    private int blogTagsId;
    private String title;
    private String content;
    private Date createdAt;
	private Date updatedAt;
 
}
