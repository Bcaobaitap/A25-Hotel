package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.DonDatPhong;

public class DonDatPhongDAO {
    
	public boolean insert(DonDatPhong don) {
	    // Bổ sung thêm cột NgayCapNhat và gán giá trị = NOW() ở cuối câu VALUE
	    String sql = "INSERT INTO DONDATPHONG (MaKH, MaPhong, NgayNhan, NgayTra, TongTien, TenNguoiDat, SoNguoi, ThongTinLienHe, TrangThaiDon, NgayCapNhat) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
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
                    DonDatPhong don = new DonDatPhong();
                    don.setMaDon(rs.getInt("MaDon"));
                    don.setMaKH((Integer) rs.getObject("MaKH"));
                    don.setMaNV((Integer) rs.getObject("MaNV"));
                    don.setMaPhong(rs.getInt("MaPhong"));
                    don.setNgayTaoDon(rs.getTimestamp("NgayTaoDon"));
                    don.setTrangThaiDon(rs.getString("TrangThaiDon"));
                    don.setTongTien(rs.getDouble("TongTien"));
                    don.setTenNguoiDat(rs.getString("TenNguoiDat"));
                    don.setNgayNhan(rs.getDate("NgayNhan"));
                    don.setNgayTra(rs.getDate("NgayTra"));
                    don.setSoNguoi(rs.getInt("SoNguoi"));
                    don.setThongTinLienHe(rs.getString("ThongTinLienHe"));
                    don.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
                    list.add(don);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean updateStatus(int maDon, int maKH, String status) {
        String sql = "UPDATE DONDATPHONG SET TrangThaiDon = ?, NgayCapNhat = NOW() WHERE MaDon = ? AND MaKH = ?";
        
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
    
    // Đã sửa lại: Sắp xếp theo NgayCapNhat DESC để lấy toàn bộ đơn, cập nhật mới nhất lên đầu
    public List<DonDatPhong> getAll() {
        List<DonDatPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM DONDATPHONG ORDER BY NgayCapNhat DESC";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                DonDatPhong don = new DonDatPhong();
                don.setMaDon(rs.getInt("MaDon"));
                don.setMaKH((Integer) rs.getObject("MaKH"));
                don.setMaNV((Integer) rs.getObject("MaNV"));
                don.setMaPhong(rs.getInt("MaPhong"));
                don.setNgayTaoDon(rs.getTimestamp("NgayTaoDon"));
                don.setTrangThaiDon(rs.getString("TrangThaiDon"));
                don.setTongTien(rs.getDouble("TongTien"));
                don.setTenNguoiDat(rs.getString("TenNguoiDat"));
                don.setNgayNhan(rs.getDate("NgayNhan"));
                don.setNgayTra(rs.getDate("NgayTra"));
                don.setSoNguoi(rs.getInt("SoNguoi"));
                don.setThongTinLienHe(rs.getString("ThongTinLienHe"));
                don.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
                list.add(don);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStatusByReceptionist(int maDon, String status, int maNV) {
        String sql = "UPDATE DONDATPHONG SET TrangThaiDon = ?, MaNV = ?, NgayCapNhat = NOW() WHERE MaDon = ?";
        
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
    
    public boolean insertByReceptionist(DonDatPhong don) {
        // Bổ sung thêm cột NgayCapNhat và gán giá trị = NOW() ở cuối câu VALUE
        String sql = "INSERT INTO DONDATPHONG (MaNV, MaPhong, NgayNhan, NgayTra, TongTien, TenNguoiDat, SoNguoi, ThongTinLienHe, TrangThaiDon, NgayCapNhat) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
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

    public Integer getConfirmApplication() {
        int total = 0;
        String sql = "SELECT count(TrangThaiDon) FROM dondatphong b WHERE b.TrangThaiDon = 'CHỜ XÁC NHẬN'";
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if(rs.next()) {
                total = rs.getInt(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // Đã dọn sạch lỗi cú pháp rác và đồng bộ chạy chuẩn theo NgayCapNhat DESC
 // 1. Hàm phục vụ bảng "Đơn đặt phòng mới nhất" (Xếp theo Mã đơn giảm dần)
    public List<DonDatPhong> getNewestBookings() {
        List<DonDatPhong> list = new ArrayList<>();
        // Sửa thành ORDER BY NgayTaoDon DESC để lấy đơn vừa mới tạo lên đầu tiên
        String sql = "SELECT * FROM DONDATPHONG ORDER BY NgayTaoDon DESC"; 
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DonDatPhong don = new DonDatPhong();
                don.setMaDon(rs.getInt("MaDon"));
                don.setMaKH((Integer) rs.getObject("MaKH")); 
                don.setMaNV((Integer) rs.getObject("MaNV"));
                don.setMaPhong(rs.getInt("MaPhong"));
                don.setNgayTaoDon(rs.getTimestamp("NgayTaoDon"));
                don.setTrangThaiDon(rs.getString("TrangThaiDon") != null ? rs.getString("TrangThaiDon").trim() : "");
                don.setTongTien(rs.getDouble("TongTien"));
                don.setTenNguoiDat(rs.getString("TenNguoiDat"));
                don.setNgayNhan(rs.getDate("NgayNhan"));
                don.setNgayTra(rs.getDate("NgayTra"));
                don.setSoNguoi(rs.getInt("SoNguoi"));
                don.setThongTinLienHe(rs.getString("ThongTinLienHe"));
                don.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
                list.add(don);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // 2. Hàm phục vụ khung "Hoạt động gần đây" (Xếp theo Ngày cập nhật giảm dần)
    public List<DonDatPhong> getRecentActivities() {
        List<DonDatPhong> list = new ArrayList<>();
        // Lấy theo ngày cập nhật mới nhất
        String sql = "SELECT * FROM DONDATPHONG ORDER BY NgayCapNhat DESC"; 
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DonDatPhong don = new DonDatPhong();
                don.setMaDon(rs.getInt("MaDon"));
                don.setMaKH((Integer) rs.getObject("MaKH")); 
                don.setMaNV((Integer) rs.getObject("MaNV"));
                don.setMaPhong(rs.getInt("MaPhong"));
                don.setNgayTaoDon(rs.getTimestamp("NgayTaoDon"));
                // Sử dụng .trim() để ép chuỗi sạch, không bị dính khoảng trắng từ SQL CHAR/VARCHAR
                don.setTrangThaiDon(rs.getString("TrangThaiDon") != null ? rs.getString("TrangThaiDon").trim() : "");
                don.setTongTien(rs.getDouble("TongTien"));
                don.setTenNguoiDat(rs.getString("TenNguoiDat"));
                don.setNgayNhan(rs.getDate("NgayNhan"));
                don.setNgayTra(rs.getDate("NgayTra"));
                don.setSoNguoi(rs.getInt("SoNguoi"));
                don.setThongTinLienHe(rs.getString("ThongTinLienHe"));
                don.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
                list.add(don);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public boolean isRoomAvailable(int maPhong, java.util.Date ngayNhan, java.util.Date ngayTra) {
        String sql = "SELECT COUNT(*) FROM DONDATPHONG WHERE MaPhong = ? "
                   + "AND TrangThaiDon IN ('CHỜ XÁC NHẬN', 'ĐÃ XÁC NHẬN', 'ĐANG LƯU TRÚ') "
                   + "AND NgayNhan < ? AND NgayTra > ?";
                   
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, maPhong);
            ps.setDate(2, new java.sql.Date(ngayTra.getTime()));   
            ps.setDate(3, new java.sql.Date(ngayNhan.getTime()));  
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }

    public int getLatestBookingIdByName(String tenNguoiDat) {
        int maDon = -1;
        String query = "SELECT MaDon FROM DONDATPHONG WHERE TenNguoiDat = ? ORDER BY MaDon DESC LIMIT 1";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tenNguoiDat);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    maDon = rs.getInt(1);
                }
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return maDon;
    }
    
    /**
     * Cập nhật trạng thái đơn hàng chỉ dựa vào Mã Đơn (Dùng cho test hoặc Admin ép trạng thái)
     * Bỏ qua kiểm tra MaKH để áp dụng được cho cả đơn của khách vãng lai (MaKH = null)
     */
    public boolean updateStatusByMaDon(int maDon, String status) {
        boolean result = false;
        String query = "UPDATE DONDATPHONG SET TrangThaiDon = ? WHERE MaDon = ?";
        try (java.sql.Connection conn = com.hotel.dao.DBContext.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, maDon);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

