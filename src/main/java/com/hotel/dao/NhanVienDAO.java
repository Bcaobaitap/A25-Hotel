package com.hotel.dao;

import com.hotel.model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhanVienDAO {
    public NhanVien getByMaTK(int maTK) {
        String sql = "SELECT * FROM NHANVIEN WHERE MaTK = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, maTK);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhanVien(
                        rs.getInt("MaNV"),
                        rs.getInt("MaTK"),
                        rs.getString("HoTen")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO NHANVIEN (MaTK, HoTen) VALUES (?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, nv.getMaTK());
            ps.setString(2, nv.getHoTen());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}