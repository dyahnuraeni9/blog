package com.training.blog.exception;

import com.training.blog.enums.StatusEnum;

import org.springframework.http.HttpStatus;


public class DataException extends Exception {
   
	private Integer code;
	private StatusEnum message;
	private HttpStatus status;
	private Object data;

    public DataException(Integer code, StatusEnum notFound, HttpStatus status, Object data) {
        this.code = code;
        this.message = notFound;
        this.status = status;
        this.data = data;
    }

}