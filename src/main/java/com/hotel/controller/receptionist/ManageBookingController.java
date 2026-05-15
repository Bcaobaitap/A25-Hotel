package com.hotel.controller.receptionist;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hotel.model.DonDatPhong;
import com.hotel.model.NhanVien;
import com.hotel.service.DonDatService;
import com.hotel.service.PhongService;

@WebServlet("/receptionist/manage-booking")
public class ManageBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DonDatService donDatService = new DonDatService();
    private PhongService phongService = new PhongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        List<DonDatPhong> listDon = donDatService.getAllBookings();
        request.setAttribute("listDon", listDon);
        request.getRequestDispatcher("/WEB-INF/views/receptionist/booking-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        NhanVien profile = (NhanVien) session.getAttribute("userProfile"); 
        
        if (profile != null) {
            try {
                int maDon = Integer.parseInt(request.getParameter("maDon"));
                String action = request.getParameter("action"); 
                int maPhong = Integer.parseInt(request.getParameter("maPhong"));
                String status = "";
                boolean success = false;
                
                if ("approve".equals(action)) {
                    status = "ĐÃ XÁC NHẬN";
                } else if ("reject".equals(action)) {
                    status = "ĐÃ TỪ CHỐI";
                } else if ("checkin".equals(action)) {
                    status = "ĐANG LƯU TRÚ";
                    phongService.updateRoomStatus(maPhong, "CÓ KHÁCH");
                } else if ("checkout".equals(action)) {
                    status = "ĐÃ HOÀN THÀNH";
                    phongService.updateRoomStatus(maPhong, "TRỐNG");
                }

                if (!status.isEmpty()) {
                    success = donDatService.changeBookingStatus(maDon, status, profile.getMaNV());
                }
                
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/receptionist/manage-booking?msg=success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/receptionist/manage-booking?msg=error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/receptionist/manage-booking?msg=error");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}