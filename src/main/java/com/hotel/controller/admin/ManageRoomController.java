package com.hotel.controller.admin;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.model.Phong;
import com.hotel.service.PhongService;

@WebServlet("/admin/rooms")
public class ManageRoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PhongService phongService = new PhongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        List<Phong> listPhong = phongService.getAllRooms();
        request.setAttribute("listPhong", listPhong);
        request.getRequestDispatcher("/WEB-INF/views/admin/room-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            if ("delete".equals(action)) {
                int maPhong = Integer.parseInt(request.getParameter("maPhong"));
                boolean success = phongService.deleteRoom(maPhong);
                response.sendRedirect(request.getContextPath() + "/admin/rooms?msg=" + (success ? "delete_success" : "error"));
            } 
            else if ("create".equals(action)) {
                Phong p = new Phong();
                p.setTenPhong(request.getParameter("tenPhong"));
                p.setLoaiPhong(request.getParameter("loaiPhong"));
                p.setSoTang(Integer.parseInt(request.getParameter("soTang")));
                p.setGia(Double.parseDouble(request.getParameter("gia")));
                p.setDienTich(Double.parseDouble(request.getParameter("dienTich")));
                p.setMoTa(request.getParameter("moTa"));
                
                // Lấy tên file ảnh từ input text (Thực tế làm upload file sẽ phức tạp hơn, ta lưu tên file tĩnh trước)
                p.setAnhPhong(request.getParameter("anhPhong")); 
                
                boolean success = phongService.createRoom(p);
                response.sendRedirect(request.getContextPath() + "/admin/rooms?msg=" + (success ? "create_success" : "error"));
            }
            else if ("update".equals(action)) {
                Phong p = new Phong();
                p.setMaPhong(Integer.parseInt(request.getParameter("maPhong"))); // Cần lấy mã phòng để biết update dòng nào
                p.setTenPhong(request.getParameter("tenPhong"));
                p.setLoaiPhong(request.getParameter("loaiPhong"));
                p.setSoTang(Integer.parseInt(request.getParameter("soTang")));
                p.setGia(Double.parseDouble(request.getParameter("gia")));
                p.setDienTich(Double.parseDouble(request.getParameter("dienTich")));
                p.setMoTa(request.getParameter("moTa"));
                p.setAnhPhong(request.getParameter("anhPhong")); 
                
                boolean success = phongService.updateRoom(p);
                response.sendRedirect(request.getContextPath() + "/admin/rooms?msg=" + (success ? "update_success" : "error"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/rooms?msg=error");
        }
    }
}