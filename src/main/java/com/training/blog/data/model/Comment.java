package com.training.blog.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="comment")
public class Comment {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	@Column(name = "blog_id")
    private int blogId;

	@Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "content")
    private String content;
    
	@Column(name = "created_at")
    private Date createdAt;
    
    @Column(name = "updated_at")
	private Date updatedAt;

    
}

