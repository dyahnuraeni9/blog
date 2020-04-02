package com.training.blog.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFailedDTO {

    private Boolean status = false;
    private Integer code = 500;
    private String message = "Internal Server Error";

    @Override
    public String toString() {
        return "{\"status\": " + status + ", \"code\": " + code + ", \"message\": \"" + message  + "\"}";
    }
}