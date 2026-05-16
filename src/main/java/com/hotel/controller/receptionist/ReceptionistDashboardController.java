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
		DonDatPhongDAO donDatPhongDao = new DonDatPhongDAO();
		
		// 1. Lấy số liệu thống kê cho các thẻ đầu trang
		double total = phongDao.GetRevenue();
		double totalGuests = phongDao.GetTotalGuests();
		int totalApplication = donDatPhongDao.getConfirmApplication();
		int totalEmptyRoom = phongDao.getEmptyRoom();
		
		// 2. Lấy danh sách phân luồng theo nghiệp vụ
		List<DonDatPhong> newestBookings = donDatPhongDao.getNewestBookings();     
		List<DonDatPhong> recentActivities = donDatPhongDao.getRecentActivities(); 
		
		// 3. Gắn dữ liệu vào Request Attribute
		request.setAttribute("currentOccupancyRevenue", total);
		request.setAttribute("currentTotalGuests", totalGuests);
		request.setAttribute("currentConfirmApplication", totalApplication);
		request.setAttribute("currentEmptyRoom", totalEmptyRoom);
		
		request.setAttribute("newestBookings", newestBookings);
		request.setAttribute("recentActivities", recentActivities);
		
		// 4. Chuyển tiếp dữ liệu sang trang JSP
		request.getRequestDispatcher("/WEB-INF/views/receptionist/dashboard.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}