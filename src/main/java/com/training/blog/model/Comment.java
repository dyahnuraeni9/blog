package com.training.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="comment")
public class Comment implements Serializable{
	
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	@Column(name = "blog_id")
    private Integer blogId;

    @Column(name = "guest_email", length = 100)
    @Size(min = 10, max = 100)
    @Email(message = "email not valid")
    private String guestEmail;

    @Column(name = "content", nullable = false, length = 1000, columnDefinition = "TEXT")
    @Size(min = 3, max = 1000)
    @NotBlank
    private String content;
    
    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    @CreationTimestamp
    private Date createdAt;
    
    @Column(name = "updated_at")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
    @UpdateTimestamp
	private Date updatedAt;

    
}

