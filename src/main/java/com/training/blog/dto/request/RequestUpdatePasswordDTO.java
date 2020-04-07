package com.training.blog.dto.request;

import lombok.Data;

@Data
public class RequestUpdatePasswordDTO {

    private Integer id;
    private String password;

}