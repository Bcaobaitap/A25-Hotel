package com.hotel.dao;

import com.hotel.model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KhachHangDAO {
	// Thêm mới hồ sơ khách hàng
    public boolean insert(KhachHang kh) {
        String sql = "INSERT INTO KHACHHANG (MaTK, HoTen, Email, SDT) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, kh.getMaTK());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getSdt());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public KhachHang getByMaTK(int maTK) {
        String sql = "SELECT * FROM KHACHHANG WHERE MaTK = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, maTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang(
                        rs.getInt("MaKH"),
                        rs.getInt("MaTK"),
                        rs.getString("HoTen"),
                        rs.getString("Email"),
                        rs.getString("SDT")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
