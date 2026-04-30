package com.hotel.dao;

import com.hotel.model.Phong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO {
	
	//Lấy danh sách phòng và hiển thị lên trang chủ
	public List<Phong> getAllRooms(){
		List<Phong> list = new ArrayList<>();
		String sql = "SELECT * FROM PHONG";
		
		try (Connection conn = DBContext.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()){
			
			while(rs.next()) {
				list.add(new Phong(
					rs.getInt("MaPhong"),
					rs.getString("TenPhong"),
                    rs.getString("LoaiPhong"),
                    rs.getInt("SoTang"),
                    rs.getDouble("Gia"),
                    rs.getString("TrangThaiPhong"),
                    rs.getDouble("DienTich"),
                    rs.getString("MoTa"),
                    rs.getString("AnhPhong")
                 ));
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Tìm kiếm phòng
	public Phong getRoomById(int id) {
		String sql = "SELECT * FROM PHONG WHERE MaPhong = ?";
		try (Connection conn = DBContext.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            
	            ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return new Phong(
	                        rs.getInt("MaPhong"),
	                        rs.getString("TenPhong"),
	                        rs.getString("LoaiPhong"),
	                        rs.getInt("SoTang"),
	                        rs.getDouble("Gia"),
	                        rs.getString("TrangThaiPhong"),
	                        rs.getDouble("DienTich"),
	                        rs.getString("MoTa"),
	                        rs.getString("AnhPhong")
	                    );
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}
	
	// Cập nhật trạng thái phòng
	public boolean updateRoomStatus(int maPhong, String status) {
	    String sql = "UPDATE PHONG SET TrangThaiPhong = ? WHERE MaPhong = ?";
	    
	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	         
	        ps.setString(1, status);
	        ps.setInt(2, maPhong);
	        
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	// Thêm phòng mới
	public boolean insertRoom(Phong p) {
	    String sql = "INSERT INTO PHONG (TenPhong, LoaiPhong, SoTang, Gia, TrangThaiPhong, DienTich, MoTa, AnhPhong) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	         
	        ps.setString(1, p.getTenPhong());
	        ps.setString(2, p.getLoaiPhong());
	        ps.setInt(3, p.getSoTang());
	        ps.setDouble(4, p.getGia());
	        ps.setString(5, "TRỐNG"); // Mặc định phòng mới tạo là trống
	        ps.setDouble(6, p.getDienTich());
	        ps.setString(7, p.getMoTa());
	        ps.setString(8, p.getAnhPhong());
	        
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	// Cập nhật thông tin phòng
	public boolean updateRoom(Phong p) {
	    String sql = "UPDATE PHONG SET TenPhong=?, LoaiPhong=?, SoTang=?, Gia=?, DienTich=?, MoTa=?, AnhPhong=? "
	               + "WHERE MaPhong=?";
	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	         
	        ps.setString(1, p.getTenPhong());
	        ps.setString(2, p.getLoaiPhong());
	        ps.setInt(3, p.getSoTang());
	        ps.setDouble(4, p.getGia());
	        ps.setDouble(5, p.getDienTich());
	        ps.setString(6, p.getMoTa());
	        ps.setString(7, p.getAnhPhong());
	        ps.setInt(8, p.getMaPhong());
	        
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	// Xóa phòng
	public boolean deleteRoom(int maPhong) {
	    String sql = "DELETE FROM PHONG WHERE MaPhong=?";
	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	         
	        ps.setInt(1, maPhong);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	// Lấy sơ đồ phòng động theo một ngày cụ thể
	public List<Phong> getRoomStatusByDate(java.sql.Date targetDate) {
	    List<Phong> list = new ArrayList<>();

	    String sql = "SELECT *, "
	               + "CASE "
	               + "  WHEN TrangThaiPhong = 'BẢO TRÌ' THEN 'BẢO TRÌ' "
	               + "  WHEN EXISTS ("
	               + "      SELECT 1 FROM DONDATPHONG d "
	               + "      WHERE d.MaPhong = PHONG.MaPhong "
	               + "        AND d.TrangThaiDon IN ('CHỜ XÁC NHẬN', 'ĐÃ XÁC NHẬN') "
	               + "        AND ? >= d.NgayNhan AND ? < d.NgayTra "
	               + "  ) THEN 'CÓ KHÁCH' "
	               + "  ELSE 'TRỐNG' "
	               + "END AS TrangThaiDong " 
	               + "FROM PHONG";
	               
	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setDate(1, targetDate);
	        ps.setDate(2, targetDate);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                list.add(new Phong(
	                    rs.getInt("MaPhong"),
	                    rs.getString("TenPhong"),
	                    rs.getString("LoaiPhong"),
	                    rs.getInt("SoTang"),
	                    rs.getDouble("Gia"),
	                    rs.getString("TrangThaiDong"), 
	                    rs.getDouble("DienTich"),
	                    rs.getString("MoTa"),
	                    rs.getString("AnhPhong")
	                ));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}

