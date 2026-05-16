package com.hotel.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        
        // Lấy dữ liệu doanh thu 7 ngày gần nhất dựa trên giá phòng đặt
        String sqlDoanhThu = "SELECT DATE_FORMAT(NgayNhan, '%d/%m') AS Ngay, SUM(TongTien) AS DoanhThu " +
                "FROM DONDATPHONG " +
                "GROUP BY DATE_FORMAT(NgayNhan, '%d/%m') " +
                "ORDER BY MIN(NgayNhan) DESC " +
                "LIMIT 7";
        
        String labelsChart = getChartLabelsAndData(sqlDoanhThu, true);  // Trả về chuỗi dạng: "12/05","13/05",...
        String dataChart = getChartLabelsAndData(sqlDoanhThu, false);   // Trả về chuỗi dạng: 1500000,2400000,...

        request.setAttribute("labelsChart", labelsChart);
        request.setAttribute("dataChart", dataChart);

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
    
    
    // Hàm bổ sung giúp lấy chuỗi dữ liệu vẽ biểu đồ cực nhanh không cần cài thêm thư viện JSON
    private String getChartLabelsAndData(String sql, boolean isLabel) {
        List<String> list = new ArrayList<>();
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                if (isLabel) {
                    list.add("\"" + rs.getString("Ngay") + "\""); // Thêm dấu ngoặc kép cho chuỗi chữ
                } else {
                    list.add(String.valueOf(rs.getInt("DoanhThu")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Vì SQL lấy DESC (mới nhất lên trước) để lấy đúng 7 ngày, ta đảo ngược lại cho biểu đồ chạy từ trái sang phải hợp lý
        Collections.reverse(list); 
        
        // Nếu DB chưa có dữ liệu, trả về dữ liệu mẫu tránh lỗi biểu đồ trắng trơn
        if(list.isEmpty()) {
            return isLabel ? "[\"T2\",\"T3\",\"T4\",\"T5\",\"T6\",\"T7\",\"CN\"]" : "[0,0,0,0,0,0,0]";
        }
        
        return "[" + String.join(",", list) + "]";
    }
    
}