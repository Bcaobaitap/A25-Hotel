package com.hotel.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hotel.model.TaiKhoan;
import com.hotel.service.DonDatService;

@WebServlet("/cancel-order")
public class CancelOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DonDatService donDatService = new DonDatService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        TaiKhoan user = (TaiKhoan) session.getAttribute("userSession");

        if (user != null) {
            try {
                int maDon = Integer.parseInt(request.getParameter("maDon"));
                boolean success = donDatService.cancelBooking(maDon, user.getMaTK());

                if (success) {
                    response.sendRedirect(request.getContextPath() + "/my-orders?cancel=success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/my-orders?cancel=failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/my-orders?cancel=error");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}