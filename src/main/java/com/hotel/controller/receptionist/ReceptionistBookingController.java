package com.hotel.controller.receptionist;

import java.io.IOException;

import com.hotel.model.DonDatPhong;
import com.hotel.model.NhanVien;
import com.hotel.model.Phong;
import com.hotel.service.DonDatService;
import com.hotel.service.PhongService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/receptionist/book-room")
public class ReceptionistBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PhongService phongService = new PhongService();
    private DonDatService donDatService = new DonDatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idRaw = request.getParameter("maPhong");
        if (idRaw != null) {
            int maPhong = Integer.parseInt(idRaw);
            Phong p = phongService.getRoomDetail(maPhong);
            
            if (p != null) {
                request.setAttribute("room", p);
                request.getRequestDispatcher("/WEB-INF/views/receptionist/booking-form.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/receptionist/room-status");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        NhanVien profile = (NhanVien) session.getAttribute("userProfile");

        if (profile != null) {
            try {
                int maPhong = Integer.parseInt(request.getParameter("maPhong"));
                String tenNguoiDat = request.getParameter("tenNguoiDat");
                String thongTinLienHe = request.getParameter("thongTinLienHe");
                String ngayNhanStr = request.getParameter("ngayNhan"); 
                String ngayTraStr = request.getParameter("ngayTra");
                int soNguoi = Integer.parseInt(request.getParameter("soNguoi"));

                DonDatPhong don = new DonDatPhong();
                don.setMaNV(profile.getMaNV()); 
                don.setMaPhong(maPhong);
                don.setTenNguoiDat(tenNguoiDat);
                don.setThongTinLienHe(thongTinLienHe);
                don.setNgayNhan(java.sql.Date.valueOf(ngayNhanStr));
                don.setNgayTra(java.sql.Date.valueOf(ngayTraStr));
                don.setSoNguoi(soNguoi);

                Phong p = phongService.getRoomDetail(maPhong);          
                boolean isCreated = donDatService.createWalkInBooking(don, p.getGia());

                if (isCreated) {
                    phongService.updateRoomStatus(maPhong, "CÓ KHÁCH");
                    response.sendRedirect(request.getContextPath() + "/receptionist/manage-booking?msg=success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/receptionist/room-status?msg=error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/receptionist/room-status?msg=error");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}