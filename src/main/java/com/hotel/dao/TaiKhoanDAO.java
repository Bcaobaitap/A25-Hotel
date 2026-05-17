package com.hotel.dao;

import com.hotel.model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class TaiKhoanDAO {
	//kiểm tra đăng nhập
	public TaiKhoan checkLogin(String tenDN, String matKhau) {
		String sql = "SELECT * FROM TAIKHOAN WHERE TenDN = ? AND MatKhau = ?";
		try (Connection conn = DBContext.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            
	            ps.setString(1, tenDN);
	            ps.setString(2, matKhau);
	            
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return new TaiKhoan(
	                        rs.getInt("MaTK"),
	                        rs.getString("TenDN"),
	                        rs.getString("MatKhau"),
	                        rs.getString("LoaiTaiKhoan")
	                    );
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}
	
	//Kiểm tra tên đăng nhập đã tồn tại hay chưa
	public boolean checkUsernameExist(String tenDN) {
        String sql = "SELECT 1 FROM TAIKHOAN WHERE TenDN = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tenDN);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }
	
	// Đăng ký tài khoản mới và trả về MaTK vừa tạo
    public int insert(TaiKhoan tk) {
        String sql = "INSERT INTO TAIKHOAN (TenDN, MatKhau, LoaiTaiKhoan) VALUES (?, ?, ?)";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, tk.getTenDN());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getLoaiTaiKhoan());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; 
    }

    public List<TaiKhoan> getAllStaffAccounts() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT t.MaTK, t.TenDN, t.MatKhau, t.LoaiTaiKhoan, n.HoTen " +
                     "FROM TAIKHOAN t " +
                     "LEFT JOIN NHANVIEN n ON t.MaTK = n.MaTK " +
                     "WHERE t.LoaiTaiKhoan IN ('ADMIN', 'RECEPTIONIST')";
                     
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                list.add(new TaiKhoan(
                    rs.getInt("MaTK"),
                    rs.getString("TenDN"),
                    rs.getString("MatKhau"),
                    rs.getString("LoaiTaiKhoan"),
                    rs.getString("HoTen")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hàm đổi Role 
    public boolean updateRole(int maTK, String newRole) {
        String sql = "UPDATE TAIKHOAN SET LoaiTaiKhoan = ? WHERE MaTK = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newRole);
            ps.setInt(2, maTK);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}