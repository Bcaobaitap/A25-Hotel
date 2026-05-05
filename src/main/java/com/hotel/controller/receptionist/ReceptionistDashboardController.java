package com.hotel.controller.receptionist;

import java.io.IOException;

import com.hotel.dao.PhongDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/receptionist/dashboard")
public class ReceptionistDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PhongDAO phongdao = new PhongDAO();
		// hiển thị tổng doanh thu trong ngày 
		double total = phongdao.GetRevenue();
		//hiển thị tổng số lượng khách đang lưu trú 
		double totalGuests=phongdao.GetTotalGuests();
		request.setAttribute("currentOccupancyRevenue", total);
		request.setAttribute("currentTotalGuests", totalGuests);
		request.getRequestDispatcher("/WEB-INF/views/receptionist/dashboard.jsp").forward(request, response);
	}
}