package com.training.blog.dto.response;

import lombok.Data;

@Data
public class ResponseBaseDTO<Any> {

    private boolean status;
    private int code;
    private String message;
    private Any data;

    public ResponseBaseDTO(int code, boolean status, String message, Any data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseBaseDTO() {
    }
    
}