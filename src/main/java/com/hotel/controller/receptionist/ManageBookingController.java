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
import com.hotel.report.BookingReportGenerator;

@WebServlet("/receptionist/manage-booking")
public class ManageBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DonDatService donDatService = new DonDatService();
    private PhongService phongService = new PhongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String action = request.getParameter("action");

        List<DonDatPhong> listDon;
        
        // 1. Kiểm tra tính hợp lệ của tham số ngày
        if (fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()) {
            // Kiểm tra logic: Nếu từ ngày lớn hơn đến ngày 
            if (fromDate.compareTo(toDate) > 0) {
                // Biện pháp an toàn: Hoán đổi hai mốc ngày để câu lệnh SQL không bị lỗi logic
                String temp = fromDate;
                fromDate = toDate;
                toDate = temp;
            }
            
            listDon = donDatService.getBookingsByDateRange(fromDate, toDate);
            request.setAttribute("fromDate", fromDate);
            request.setAttribute("toDate", toDate);
        } else {
            listDon = donDatService.getAllBookings();
        }

        // 2. Xử lý xuất báo cáo
        try {
            if ("export_excel".equals(action)) {
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=BaoCao_DonDatPhong.xlsx");
                BookingReportGenerator.exportExcel(listDon, response.getOutputStream());
                return;
            } else if ("export_pdf".equals(action)) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=BaoCao_DonDatPhong.pdf");
                String fontPath = getServletContext().getRealPath("/assets/fonts/dashboard_fonts/Arial.ttf");
                BookingReportGenerator.exportPdf(listDon, response.getOutputStream(), fontPath);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "error");
        }

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