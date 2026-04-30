package com.hotel.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hotel.model.TaiKhoan;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        
        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        boolean isPublicResource = uri.startsWith(contextPath + "/assets/") || 
                                   uri.endsWith("/home") || 
                                   uri.endsWith("/login") || 
                                   uri.endsWith("/register") ||
                                   uri.endsWith("/rooms") || 
                                   uri.endsWith("/room-detail") ||
                                   uri.equals(contextPath + "/");

        TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");

        if (user != null || isPublicResource) {
            if (uri.contains("/admin/") && (user == null || !"ADMIN".equals(user.getLoaiTaiKhoan()))) {
                httpResponse.sendRedirect(contextPath + "/login");
                return;
            }
            if (uri.contains("/receptionist/") && (user == null || !"RECEPTIONIST".equals(user.getLoaiTaiKhoan()) 
            		&& !"ADMIN".equals(user.getLoaiTaiKhoan()))) {
                httpResponse.sendRedirect(contextPath + "/login");
                return;
            }
            chain.doFilter(request, response);
        } else {
            if (uri.endsWith("/booking")) {
                String roomId = request.getParameter("id");
                if (roomId != null) {
                    session.setAttribute("redirectAfterLogin", contextPath + "/room-detail?id=" + roomId);
                }
            }
            httpResponse.sendRedirect(contextPath + "/login");
        }
    }
}