package com.hotel.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.dao.DBContext;

@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int tongSoPhong = getCount("SELECT COUNT(*) FROM PHONG");
        int tongDonDat = getCount("SELECT COUNT(*) FROM DONDATPHONG");
        int tongKhachHang = getCount("SELECT COUNT(*) FROM KHACHHANG");
        int tongNhanSu = getCount("SELECT COUNT(*) FROM NHANVIEN");

        request.setAttribute("tongSoPhong", tongSoPhong);
        request.setAttribute("tongDonDat", tongDonDat);
        request.setAttribute("tongKhachHang", tongKhachHang);
        request.setAttribute("tongNhanSu", tongNhanSu);

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }

    private int getCount(String sql) {
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}