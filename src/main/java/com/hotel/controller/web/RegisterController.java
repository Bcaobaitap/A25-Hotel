package com.hotel.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.service.TaiKhoanService;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaiKhoanService taiKhoanService = new TaiKhoanService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/web/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String tenDN = request.getParameter("username");
        String matKhau = request.getParameter("password");
        String hoTen = request.getParameter("fullname");
        String email = request.getParameter("email");
        String sdt = request.getParameter("phone");
        
        hoTen = com.hotel.util.ValidationUtil.sanitize(hoTen);
        tenDN = com.hotel.util.ValidationUtil.sanitize(tenDN);

        if (!com.hotel.util.ValidationUtil.isValidEmail(email)) {
            request.setAttribute("error", "Email không đúng định dạng hợp lệ!");
            request.setAttribute("oldUsername", tenDN);
            request.setAttribute("oldFullName", hoTen);
            request.setAttribute("oldPhone", sdt);
            request.getRequestDispatcher("/WEB-INF/views/web/register.jsp").forward(request, response);
            return; 
        }

        if (!com.hotel.util.ValidationUtil.isValidPhone(sdt)) {
            request.setAttribute("error", "Số điện thoại phải gồm 10 số và bắt đầu bằng số 0!");
            request.setAttribute("oldUsername", tenDN);
            request.setAttribute("oldFullName", hoTen);
            request.setAttribute("oldPhone", sdt);
            request.getRequestDispatcher("/WEB-INF/views/web/register.jsp").forward(request, response);
            return;
        }
        
        String result = taiKhoanService.registerCustomer(tenDN, matKhau, hoTen, email, sdt);

        if ("SUCCESS".equals(result)) {
            request.setAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
            request.getRequestDispatcher("/WEB-INF/views/web/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", result);
            request.setAttribute("oldUsername", tenDN);
            request.setAttribute("oldFullName", hoTen);
            request.setAttribute("oldEmail", email);
            request.setAttribute("oldPhone", sdt);
            request.getRequestDispatcher("/WEB-INF/views/web/register.jsp").forward(request, response);
        }
    }
}