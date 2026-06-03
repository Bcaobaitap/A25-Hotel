package com.hotel.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import com.hotel.dto.DoanhThuDTO;
import com.hotel.report.RevenueReportGenerator;

import com.hotel.dao.DBContext;

@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tongSoPhong = getCount("SELECT COUNT(*) FROM PHONG");
        int tongDonDat = getCount("SELECT COUNT(*) FROM DONDATPHONG");
        int tongKhachHang = getCount("SELECT COUNT(*) FROM KHACHHANG");
        int tongNhanSu = getCount("SELECT COUNT(*) FROM NHANVIEN");
        
        request.setAttribute("tongSoPhong", tongSoPhong);
        request.setAttribute("tongDonDat", tongDonDat);
        request.setAttribute("tongKhachHang", tongKhachHang);
        request.setAttribute("tongNhanSu", tongNhanSu);

        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String action = request.getParameter("action");

        List<DoanhThuDTO> listDoanhThu = new ArrayList<>();
        String sqlDoanhThu = "";

        try (Connection conn = DBContext.getConnection()) {
            PreparedStatement ps;
            // Nếu có bộ lọc thời gian
            if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
                sqlDoanhThu = "SELECT DATE_FORMAT(NgayNhan, '%d/%m/%Y') AS Ngay, SUM(TongTien) AS DoanhThu " +
                              "FROM DONDATPHONG " +
                              "WHERE TrangThaiDon IN ('ĐÃ XÁC NHẬN', 'ĐANG LƯU TRÚ', 'ĐÃ HOÀN THÀNH') " + 
                              "AND NgayNhan >= ? AND NgayNhan <= ? " +
                              "GROUP BY DATE_FORMAT(NgayNhan, '%d/%m/%Y'), NgayNhan " +
                              "ORDER BY NgayNhan ASC";
                ps = conn.prepareStatement(sqlDoanhThu);
                ps.setDate(1, Date.valueOf(fromDate));
                ps.setDate(2, Date.valueOf(toDate));
            } else {
                // Mặc định: 7 ngày gần nhất
                sqlDoanhThu = "SELECT DATE_FORMAT(NgayNhan, '%d/%m/%Y') AS Ngay, SUM(TongTien) AS DoanhThu " +
                              "FROM DONDATPHONG " +
                              "WHERE TrangThaiDon IN ('ĐÃ XÁC NHẬN', 'ĐANG LƯU TRÚ', 'ĐÃ HOÀN THÀNH') " +
                              "GROUP BY DATE_FORMAT(NgayNhan, '%d/%m/%Y'), NgayNhan " +
                              "ORDER BY NgayNhan DESC LIMIT 7";
                ps = conn.prepareStatement(sqlDoanhThu);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listDoanhThu.add(new DoanhThuDTO(rs.getString("Ngay"), rs.getDouble("DoanhThu")));
            }
            
            // Đảo ngược lại nếu là 7 ngày gần nhất để biểu đồ vẽ đúng trục thời gian
            if (fromDate == null || fromDate.isEmpty()) {
                Collections.reverse(listDoanhThu);
            }
        } catch (Exception e) { e.printStackTrace(); }

        // Tính năng Xuất File
        try {
            if ("export_excel".equals(action)) {
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=BaoCao_DoanhThu.xlsx");
                RevenueReportGenerator.exportExcel(listDoanhThu, response.getOutputStream());
                return;
            } else if ("export_pdf".equals(action)) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=BaoCao_DoanhThu.pdf");
                String fontPath = getServletContext().getRealPath("/assets/fonts/dashboard_fonts/Arial.ttf");
                RevenueReportGenerator.exportPdf(listDoanhThu, response.getOutputStream(), fontPath);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "error_export");
        }

        // Đổ dữ liệu ra Chart.js
        List<String> labels = new ArrayList<>();
        List<String> data = new ArrayList<>();
        for(DoanhThuDTO dto : listDoanhThu) {
            labels.add("\"" + dto.getNgay() + "\"");
            data.add(String.valueOf(dto.getTongTien()));
        }
        if (labels.isEmpty()) { labels.add("\"Không có dữ liệu\""); data.add("0"); }

        request.setAttribute("labelsChart", "[" + String.join(",", labels) + "]");
        request.setAttribute("dataChart", "[" + String.join(",", data) + "]");
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);

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