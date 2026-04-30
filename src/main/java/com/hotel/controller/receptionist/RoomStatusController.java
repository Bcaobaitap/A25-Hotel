package com.hotel.controller.receptionist;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.model.Phong;
import com.hotel.service.PhongService;

@WebServlet("/receptionist/room-status")
public class RoomStatusController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PhongService phongService = new PhongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //Nhận tham số ngày từ giao diện
        String dateParam = request.getParameter("checkDate");
        java.sql.Date targetDate;
        
        if (dateParam != null && !dateParam.isEmpty()) {
            targetDate = java.sql.Date.valueOf(dateParam);
        } else {
            targetDate = new java.sql.Date(System.currentTimeMillis());
        }

        List<Phong> listPhong = phongService.getRoomStatusByDate(targetDate);
        
        request.setAttribute("listPhong", listPhong);
        request.setAttribute("currentDate", targetDate.toString());
        
        request.getRequestDispatcher("/WEB-INF/views/receptionist/room-status.jsp").forward(request, response);
    }
}