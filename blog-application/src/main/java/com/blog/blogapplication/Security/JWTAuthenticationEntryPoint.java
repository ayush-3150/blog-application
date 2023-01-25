package com.blog.blogapplication.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //This method will be called when any unauthorized person will try to access APIs
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // TODO Auto-generated method stub
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied");
    }
    
}
