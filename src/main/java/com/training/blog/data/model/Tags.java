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
@Table(name="tags")
public class Tags {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

	@Column(name="name")
    private String name;
    
	@Column(name="created_at")
    private Date createdAt;
    
    @Column(name="updated_at")
	private Date updatedAt;

    
}