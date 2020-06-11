package com.software.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.web.access.AccessDeniedHandler;

// Referemce: https://medium.com/%E4%BC%81%E9%B5%9D%E4%B9%9F%E6%87%82%E7%A8%8B%E5%BC%8F%E8%A8%AD%E8%A8%88/spring-security-%E7%B5%90%E5%90%88restfulapi%E7%9A%84%E8%A8%AD%E8%A8%88-60b778fd3b22
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        // The follow code is to generate JSON response for restful api
        // ObjectMapper mapper = new ObjectMapper();
        // Map<String, String> result = Map.of("message", "Access Denied");
        // response.setContentType("application/json;charset=UTF-8");
        // response.setCharacterEncoding("UTF-8");
        // response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // PrintWriter out = response.getWriter();
        // out.write(mapper.writeValueAsString(result));
        // out.flush();
        // out.close();

        response.sendRedirect("/accessDenied");
    }
}