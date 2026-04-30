package com.hotel.dao;

import com.hotel.model.DonDatPhong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class DonDatPhongDAO {
    public boolean insert(DonDatPhong don) {
        String sql = "INSERT INTO DONDATPHONG (MaKH, MaPhong, NgayNhan, NgayTra, TongTien, TenNguoiDat, SoNguoi, ThongTinLienHe, TrangThaiDon) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (don.getMaKH() != null) ps.setInt(1, don.getMaKH()); 
            else ps.setNull(1, java.sql.Types.INTEGER);
            
            ps.setInt(2, don.getMaPhong());
            ps.setDate(3, new java.sql.Date(don.getNgayNhan().getTime()));
            ps.setDate(4, new java.sql.Date(don.getNgayTra().getTime()));
            ps.setDouble(5, don.getTongTien());
            ps.setString(6, don.getTenNguoiDat());
            ps.setInt(7, don.getSoNguoi());
            ps.setString(8, don.getThongTinLienHe());
            ps.setString(9, "CHỜ XÁC NHẬN");

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<DonDatPhong> getByMaKH(int maKH) {
        List<DonDatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM DONDATPHONG WHERE MaKH = ? ORDER BY NgayTaoDon DESC";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DonDatPhong don = new DonDatPhong(
                        rs.getInt("MaDon"),
                        (Integer) rs.getObject("MaKH"), 
                        (Integer) rs.getObject("MaNV"),
                        rs.getInt("MaPhong"),
                        rs.getTimestamp("NgayTaoDon"),
                        rs.getString("TrangThaiDon"),
                        rs.getDouble("TongTien"),
                        rs.getString("TenNguoiDat"),
                        rs.getDate("NgayNhan"),
                        rs.getDate("NgayTra"),
                        rs.getInt("SoNguoi"),
                        rs.getString("ThongTinLienHe")
                    );
                    list.add(don);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean updateStatus(int maDon, int maKH, String status) {
        String sql = "UPDATE DONDATPHONG SET TrangThaiDon = ? WHERE MaDon = ? AND MaKH = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setInt(2, maDon);
            ps.setInt(3, maKH);
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Lấy toàn bộ danh sách đơn đặt phòng cho Lễ tân/Admin quản lý
    public List<DonDatPhong> getAll() {
        List<DonDatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM DONDATPHONG ORDER BY NgayTaoDon DESC";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                DonDatPhong don = new DonDatPhong(
                    rs.getInt("MaDon"),
                    (Integer) rs.getObject("MaKH"), 
                    (Integer) rs.getObject("MaNV"),
                    rs.getInt("MaPhong"),
                    rs.getTimestamp("NgayTaoDon"),
                    rs.getString("TrangThaiDon"),
                    rs.getDouble("TongTien"),
                    rs.getString("TenNguoiDat"),
                    rs.getDate("NgayNhan"),
                    rs.getDate("NgayTra"),
                    rs.getInt("SoNguoi"),
                    rs.getString("ThongTinLienHe")
                );
                list.add(don);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Cập nhật trạng thái đơn (Dành riêng cho Lễ tân / Admin)
    public boolean updateStatusByReceptionist(int maDon, String status, int maNV) {
        String sql = "UPDATE DONDATPHONG SET TrangThaiDon = ?, MaNV = ? WHERE MaDon = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setInt(2, maNV);
            ps.setInt(3, maDon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Lễ tân tạo đơn
    public boolean insertByReceptionist(DonDatPhong don) {
        String sql = "INSERT INTO DONDATPHONG (MaNV, MaPhong, NgayNhan, NgayTra, TongTien, TenNguoiDat, SoNguoi, ThongTinLienHe, TrangThaiDon) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, don.getMaNV());
            ps.setInt(2, don.getMaPhong());
            ps.setDate(3, new java.sql.Date(don.getNgayNhan().getTime()));
            ps.setDate(4, new java.sql.Date(don.getNgayTra().getTime()));
            ps.setDouble(5, don.getTongTien());
            ps.setString(6, don.getTenNguoiDat());
            ps.setInt(7, don.getSoNguoi());
            ps.setString(8, don.getThongTinLienHe());
            ps.setString(9, "ĐANG LƯU TRÚ"); 
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}