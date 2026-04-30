package com.hotel.controller.admin;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.model.TaiKhoan;
import com.hotel.service.TaiKhoanService;

@WebServlet("/admin/staff")
public class ManageStaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaiKhoanService taiKhoanService = new TaiKhoanService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<TaiKhoan> listStaff = taiKhoanService.getStaffList();
        request.setAttribute("listStaff", listStaff);
        request.getRequestDispatcher("/WEB-INF/views/admin/staff-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            if ("create".equals(action)) {
                String hoTen = request.getParameter("hoTen");
                String tenDN = request.getParameter("tenDN");
                String matKhau = request.getParameter("matKhau");
                String role = request.getParameter("role");
                
                String result = taiKhoanService.createStaffAccount(tenDN, matKhau, hoTen, role);
                if ("SUCCESS".equals(result)) {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=create_success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=error");
                }
            } 
            else if ("updateRole".equals(action)) {
                int maTK = Integer.parseInt(request.getParameter("maTK"));
                String newRole = request.getParameter("newRole");
                
                boolean success = taiKhoanService.changeStaffRole(maTK, newRole);
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=update_success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/staff?msg=error");
        }
    }
}