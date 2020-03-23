package com.training.blog.data.response;

import lombok.Data;

@Data
public class ResponseBaseDTO<Any> {

    private boolean status;
    private int code;
    private String message;
    private Any data;
    
}