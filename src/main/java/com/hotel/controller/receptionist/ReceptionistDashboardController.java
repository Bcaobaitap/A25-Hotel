package com.hotel.controller.receptionist;

import java.io.IOException;
import java.util.List;

import com.hotel.dao.DonDatPhongDAO;
import com.hotel.dao.PhongDAO;
import com.hotel.model.DonDatPhong;

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
		PhongDAO phongDao = new PhongDAO();
		DonDatPhongDAO donDatPhongDao=new DonDatPhongDAO();
		// hiển thị tổng doanh thu trong ngày 
		double total = phongDao.GetRevenue();
		//hiển thị tổng số lượng khách đang lưu trú 
		double totalGuests=phongDao.GetTotalGuests();
		//hiển thị tổng số lượng đơn cần duyệt 
		int totalApplication = donDatPhongDao.getConfirmApplication();
		//hiển thị tổng số phòng còn trống 
		int totalEmptyRoom=phongDao.getEmptyRoom();
		// hiển thị 5 đơn đặt phòng mời nhất
		List<DonDatPhong> getRecentBookings = donDatPhongDao.getRecentBookings();
		request.setAttribute("currentOccupancyRevenue", total);
		request.setAttribute("currentTotalGuests", totalGuests);
		request.setAttribute("currentConfirmApplication", totalApplication);
		request.setAttribute("currentEmptyRoom", totalEmptyRoom);
		request.setAttribute("recentBookings", getRecentBookings);
		request.getRequestDispatcher("/WEB-INF/views/receptionist/dashboard.jsp").forward(request, response);
	}
}