package com.training.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="tags")
public class Tags implements Serializable{
	
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

	@Column(name="name")
    private String name;
    
    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

    
}