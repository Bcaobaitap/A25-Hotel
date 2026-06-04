package com.hotel.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hotel.model.TaiKhoan;
import com.hotel.model.KhachHang;
import com.hotel.model.DonDatPhong;
import com.hotel.model.Phong;
import com.hotel.service.DonDatService;
import com.hotel.service.PhongService;
import com.hotel.dao.KhachHangDAO;

@WebServlet("/order-detail")
public class OrderDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DonDatService donDatService = new DonDatService();
    private PhongService phongService = new PhongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");
        
        if (user != null) {
            try {
                String maDonStr = request.getParameter("maDon");
                if (maDonStr == null || maDonStr.isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/my-orders");
                    return;
                }
                int maDon = Integer.parseInt(maDonStr);
                
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                KhachHang kh = khachHangDAO.getByMaTK(user.getMaTK());
                
                if (kh != null) {
                    // Lấy thông tin đơn hàng và xác thực sở hữu đơn
                    DonDatPhong don = donDatService.getBookingDetail(maDon, kh.getMaKH());
                    
                    if (don != null) {
                        // Lấy thông tin phòng để hiển thị ảnh, tên phòng
                        Phong phong = phongService.getRoomDetail(don.getMaPhong());
                        
                        request.setAttribute("donDat", don);
                        request.setAttribute("phong", phong);
                        
                        request.getRequestDispatcher("/WEB-INF/views/web/order-detail.jsp").forward(request, response);
                        return;
                    }
                }
                // Nếu đơn hàng không tồn tại hoặc khách hàng đang cố tình đổi tham số URL
                response.sendRedirect(request.getContextPath() + "/my-orders?error=notfound");
                
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/my-orders");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}