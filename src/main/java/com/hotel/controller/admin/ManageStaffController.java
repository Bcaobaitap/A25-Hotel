package com.hotel.controller.admin;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hotel.model.TaiKhoan;
import com.hotel.service.TaiKhoanService;
import com.hotel.report.EmployeeReportGenerator;

@WebServlet("/admin/staff")
public class ManageStaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaiKhoanService taiKhoanService = new TaiKhoanService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // Gọi danh sách tài khoản (nhân viên) với 5 tham số
        List<TaiKhoan> listStaff  = taiKhoanService.getStaffList(); 

        try {
            if ("export_excel".equals(action)) {
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=DanhSach_NhanVien.xlsx");
                EmployeeReportGenerator.exportExcel(listStaff , response.getOutputStream());
                return; 
            } else if ("export_pdf".equals(action)) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=DanhSach_NhanVien.pdf");

                String fontPath = getServletContext().getRealPath("/assets/fonts/dashboard_fonts/Arial.ttf");
                EmployeeReportGenerator.exportPdf(listStaff , response.getOutputStream(), fontPath);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "error_export");
        }

        request.setAttribute("listStaff", listStaff );
        request.getRequestDispatcher("/WEB-INF/views/admin/staff-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            if ("create".equals(action)) {
                String hoTen = request.getParameter("hoTen");
                String tenDN = request.getParameter("tenDN");
                String matKhau = request.getParameter("matKhau");
                String role = request.getParameter("role");
                
                String result = taiKhoanService.createStaffAccount(tenDN, matKhau, hoTen, role);
                if ("SUCCESS".equals(result)) {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=create_success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=error");
                }
            } 
            else if ("updateRole".equals(action)) {
            	int maTK = Integer.parseInt(request.getParameter("maTK"));
                String newRole = request.getParameter("newRole");

                boolean success = false;
                // Bắt điều kiện: Nếu chọn "LOCKED" (Khoá TK) thì lập tức kích hoạt luồng xóa DB
                if ("LOCKED".equals(newRole)) {
                    success = taiKhoanService.deleteStaff(maTK);
                } else {
                    success = taiKhoanService.changeStaffRole(maTK, newRole);
                }
                
                if (success) {
                    String msg = "LOCKED".equals(newRole) ? "delete_success" : "update_success";
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=" + msg);
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=error");
                }
            }
         // Xử lý request từ nút Sửa thông tin
            else if ("updateInfo".equals(action)) {
                int maTK = Integer.parseInt(request.getParameter("maTK"));
                String hoTen = request.getParameter("hoTen");
                String tenDN = request.getParameter("tenDN");
                String matKhau = request.getParameter("matKhau");

                boolean success = taiKhoanService.updateStaffInfo(maTK, hoTen, tenDN, matKhau);
                
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=update_info_success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/staff?msg=error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/staff?msg=error");
        }
    }
}