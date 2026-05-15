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

// Chỉ bảo vệ các URL cụ thể yêu cầu quyền thành viên
@WebFilter(urlPatterns = {"/booking", "/my-orders", "/cancel-order"})
public class MemberFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");
        
        if (user != null) {
            chain.doFilter(request, response);
        } else {
            String uri = req.getRequestURI();
            String query = req.getQueryString();
            String fullURL = uri + (query != null ? "?" + query : "");
            
            session.setAttribute("redirectAfterLogin", fullURL);
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}