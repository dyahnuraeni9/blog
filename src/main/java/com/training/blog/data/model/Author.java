package com.training.blog.data.model;

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
@Table(name="author")
public class Author {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

	@Column(name="first_name")
    private String firstName;
    
	@Column(name="last_name")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;


    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;



}