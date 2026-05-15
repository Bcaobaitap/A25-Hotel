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

@WebFilter("/admin/*") 
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");
        
        //Phải là ADMIN mới được đi tiếp
        if (user != null && "ADMIN".equals(user.getLoaiTaiKhoan())) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("error", "Khu vực dành riêng cho Quản trị viên!");
            req.getRequestDispatcher("/WEB-INF/views/web/login.jsp").forward(req, res);
        }
    }
}