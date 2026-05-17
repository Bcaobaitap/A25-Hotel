package com.hotel.service;

import com.hotel.dao.TaiKhoanDAO;
import com.hotel.dao.KhachHangDAO;
import com.hotel.dao.NhanVienDAO;
import com.hotel.model.TaiKhoan;
import com.hotel.model.KhachHang;
import com.hotel.model.NhanVien;

import java.util.List;

public class TaiKhoanService {
	private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();
    
    //Xử lý logic đăng nhập
    public TaiKhoan login(String tenDN, String matKhau) {
    	return taiKhoanDAO.checkLogin(tenDN, matKhau);
    }
    
    //Xử lý logic đăng ký tài khoản
    public String registerCustomer(String tenDN, String matKhau, String hoTen, String email, String sdt) {
        if (tenDN == null || tenDN.trim().isEmpty() || matKhau == null || matKhau.trim().isEmpty()) {
            return "Tên đăng nhập và mật khẩu không được để trống!";
        }
        if (hoTen == null || hoTen.trim().isEmpty()) {
            return "Họ tên không được để trống!";
        }

        boolean isExist = taiKhoanDAO.checkUsernameExist(tenDN);
        if (isExist) {
            return "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác!";
        }
        
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDN(tenDN);
        tk.setMatKhau(matKhau);
        tk.setLoaiTaiKhoan("CUSTOMER");
        
        int maTK = taiKhoanDAO.insert(tk);
        if (maTK > 0) {
            KhachHang kh = new KhachHang();
            kh.setMaTK(maTK);
            kh.setHoTen(hoTen);
            kh.setEmail(email);
            kh.setSdt(sdt);
            
            boolean isKhCreated = khachHangDAO.insert(kh);
            
            if (isKhCreated) {
                return "SUCCESS"; 
            } else {
                return "Tạo tài khoản thành công nhưng có lỗi khi lưu hồ sơ khách hàng!";
            }
        }
        return "Đã xảy ra lỗi hệ thống, không thể tạo tài khoản lúc này!";
    }
    
    public List<TaiKhoan> getStaffList() {
        return taiKhoanDAO.getAllStaffAccounts();
    }

    public String createStaffAccount(String tenDN, String matKhau, String hoTen, String role) {
        if (tenDN == null || tenDN.trim().isEmpty() || matKhau == null || matKhau.trim().isEmpty()) {
            return "Tên đăng nhập và mật khẩu không được để trống!";
        }
        if (taiKhoanDAO.checkUsernameExist(tenDN)) {
            return "Tên đăng nhập đã tồn tại!";
        }

        TaiKhoan tk = new TaiKhoan();
        tk.setTenDN(tenDN);
        tk.setMatKhau(matKhau);
        tk.setLoaiTaiKhoan(role); 
        
        int maTK = taiKhoanDAO.insert(tk); 

        if (maTK > 0) {
            NhanVien nv = new NhanVien();
            nv.setMaTK(maTK);
            nv.setHoTen(hoTen);
            
            boolean isNvCreated = nhanVienDAO.insert(nv);
            if (isNvCreated) {
                return "SUCCESS";
            } else {
                return "Tạo tài khoản thành công nhưng lỗi lưu hồ sơ!";
            }
        }
        return "Lỗi hệ thống khi tạo tài khoản!";
    }

    public boolean changeStaffRole(int maTK, String newRole) {
        return taiKhoanDAO.updateRole(maTK, newRole);
    }
}