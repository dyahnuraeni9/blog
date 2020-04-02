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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="author")
public class Author implements Serializable{
	
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="first_name", length = 45, nullable = false)
    @Size(min = 3, max = 45)
    @NotBlank
    private String firstName;
    
    @Column(name="last_name", length = 45, nullable = false)
    @Size(min = 3, max = 45)
    @NotBlank
    private String lastName;

    @Column(name="username", length = 45, nullable = false)
    @Size(min = 3, max = 45)
    @NotBlank
    private String username;

    @Column(name="password", length = 150, nullable = false)
    @NotBlank
    private String password;

    @Column(name="created_at", updatable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    @CreationTimestamp
    private Date createdAt;
    
    @Column(name="updated_at")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    @UpdateTimestamp
	private Date updatedAt;



}