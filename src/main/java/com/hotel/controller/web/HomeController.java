package com.hotel.controller.web;

import java.io.IOException;
import java.util.List;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.model.Phong;
import com.hotel.service.PhongService;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhongService phongService = new PhongService();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	List<Phong> listPhong = phongService.getAllRooms();
    	
    	request.setAttribute("danhSachPhong", listPhong);
        request.getRequestDispatcher("/WEB-INF/views/web/index.jsp").forward(request, response);
    }
}
