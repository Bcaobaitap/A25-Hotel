package com.hotel.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*") 
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //Ép kiểu dữ liệu về UTF-8 trước khi vào Controller
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        chain.doFilter(request, response); 
    }
}