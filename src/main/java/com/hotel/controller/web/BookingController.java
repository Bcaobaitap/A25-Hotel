package com.hotel.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hotel.model.TaiKhoan;
import com.hotel.model.DonDatPhong;
import com.hotel.model.Phong;
import com.hotel.service.DonDatService;
import com.hotel.service.PhongService;

@WebServlet("/booking")
public class BookingController extends HttpServlet {
    private PhongService phongService = new PhongService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idRaw = request.getParameter("id");
        HttpSession session = request.getSession();
        TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");

        if (idRaw != null && user != null) {
            int maPhong = Integer.parseInt(idRaw);
            Phong p = phongService.getRoomDetail(maPhong);

            request.setAttribute("room", p);
            request.getRequestDispatcher("/WEB-INF/views/web/booking-form.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int maPhong = Integer.parseInt(request.getParameter("maPhong"));
            String tenNguoiDat = request.getParameter("tenNguoiDat");
            String thongTinLienHe = request.getParameter("thongTinLienHe");
            String ngayNhanStr = request.getParameter("ngayNhan");
            String ngayTraStr = request.getParameter("ngayTra");
            int soNguoi = Integer.parseInt(request.getParameter("soNguoi"));

            java.sql.Date ngayNhan = java.sql.Date.valueOf(ngayNhanStr);
            java.sql.Date ngayTra = java.sql.Date.valueOf(ngayTraStr);

            HttpSession session = request.getSession();
            TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");

            DonDatPhong don = new DonDatPhong();
            don.setMaKH(user.getMaTK());
            don.setMaPhong(maPhong);
            don.setTenNguoiDat(tenNguoiDat);
            don.setThongTinLienHe(thongTinLienHe);
            don.setNgayNhan(ngayNhan);
            don.setNgayTra(ngayTra);
            don.setSoNguoi(soNguoi);

            Phong p = phongService.getRoomDetail(maPhong);          
            DonDatService donDatService = new DonDatService();
            boolean success = donDatService.createBooking(don, p.getGia());

            if (success) {
                response.sendRedirect(request.getContextPath() + "/my-orders?status=success");
            } else {
                request.setAttribute("error", "Không thể đặt phòng, vui lòng thử lại!");
                request.getRequestDispatcher("/WEB-INF/views/web/booking-form.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}

