package com.hotel.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.model.Phong;
import com.hotel.service.PhongService;

@WebServlet("/room-detail")
public class RoomDetailController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private PhongService phongService = new PhongService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idRaw = request.getParameter("id");
        try {
            if (idRaw != null) {
                int id = Integer.parseInt(idRaw);
                Phong p = phongService.getRoomDetail(id);
                
                if (p != null) { 
                    request.setAttribute("room", p);
                    request.getRequestDispatcher("/WEB-INF/views/web/room-detail.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
