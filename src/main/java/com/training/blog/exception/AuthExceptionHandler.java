package com.training.blog.exception;

import com.training.blog.dto.response.ResponseFailedDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthExceptionHandler
 */
public class AuthExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res,
                         org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {

        ResponseFailedDTO response = new ResponseFailedDTO();

        response.setCode(HttpStatus.UNAUTHORIZED.value());
        response.setStatus(false);
        response.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.getWriter().write(response.toString());

    }


}