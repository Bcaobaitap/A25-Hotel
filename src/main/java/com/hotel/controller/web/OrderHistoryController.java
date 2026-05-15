package com.hotel.controller.web;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hotel.model.TaiKhoan;
import com.hotel.model.DonDatPhong;
import com.hotel.service.DonDatService;
import com.hotel.dao.KhachHangDAO;
import com.hotel.model.KhachHang;

@WebServlet("/my-orders")
public class OrderHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DonDatService donDatService = new DonDatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");

        if (user != null) {
        	KhachHangDAO khachHangDAO = new KhachHangDAO();
            KhachHang kh = khachHangDAO.getByMaTK(user.getMaTK());
            
            List<DonDatPhong> listDon = donDatService.getHistoryByCustomer(user.getMaTK());
  
            if (kh != null) {
                listDon = donDatService.getHistoryByCustomer(kh.getMaKH()); 
            }
            
            request.setAttribute("listDon", listDon);
            String status = request.getParameter("status");
            if ("success".equals(status)) {
                request.setAttribute("message", "Đặt phòng thành công.");
            }
            request.getRequestDispatcher("/WEB-INF/views/web/my-orders.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}