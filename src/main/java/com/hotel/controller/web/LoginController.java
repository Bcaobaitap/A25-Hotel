package com.hotel.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hotel.model.TaiKhoan;
import com.hotel.model.NhanVien;
import com.hotel.model.KhachHang;
import com.hotel.service.TaiKhoanService;
import com.hotel.service.NhanVienService;
import com.hotel.service.KhachHangService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaiKhoanService taiKhoanService = new TaiKhoanService();
    private NhanVienService nhanVienService = new NhanVienService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/web/login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tenDN = request.getParameter("username");
        String matKhau = request.getParameter("password");
        
        TaiKhoanService taiKhoanService = new TaiKhoanService();
        NhanVienService nhanVienService = new NhanVienService();
        KhachHangService khachHangService = new KhachHangService();

        TaiKhoan user = taiKhoanService.login(tenDN, matKhau);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userSession", user);

            String role = user.getLoaiTaiKhoan();
            
            if ("ADMIN".equals(role) || "RECEPTIONIST".equals(role)) {
                NhanVien profile = nhanVienService.getProfileByMaTK(user.getMaTK());
                session.setAttribute("userProfile", profile);
            } else {
                KhachHang profile = khachHangService.getProfileByMaTK(user.getMaTK());
                session.setAttribute("userProfile", profile);
            }

            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
            if (redirectUrl != null) {
                session.removeAttribute("redirectAfterLogin");
                response.sendRedirect(redirectUrl);
                return;
            }

            if ("ADMIN".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if ("RECEPTIONIST".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/receptionist/dashboard"); 
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            request.setAttribute("oldUsername", tenDN);
            request.getRequestDispatcher("/WEB-INF/views/web/login.jsp").forward(request, response);
        }
    }
}