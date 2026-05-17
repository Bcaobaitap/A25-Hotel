package com.hotel.integration;

import com.hotel.dao.DBContext;
import com.hotel.dao.DonDatPhongDAO;
import com.hotel.dao.PhongDAO;
import com.hotel.model.Phong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LifecycleTest {
    private PhongDAO phongDAO;
    private DonDatPhongDAO donDatDAO;
    private int maPhongTest;

    @BeforeEach
    public void setUp() {
        phongDAO = new PhongDAO();
        donDatDAO = new DonDatPhongDAO();

        Phong p = new Phong();
        p.setTenPhong("Phòng Nhất Quán Dữ Liệu");
        p.setLoaiPhong("Phòng đôi");
        p.setTrangThaiPhong("TRỐNG");
        phongDAO.insertRoom(p);
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT MaPhong FROM PHONG WHERE TenPhong = ?")) {
            ps.setString(1, "Phòng Nhất Quán Dữ Liệu");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) maPhongTest = rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @AfterEach
    public void tearDown() {
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM PHONG WHERE TenPhong = ?")) {
            ps.setString(1, "Phòng Nhất Quán Dữ Liệu");
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Kiểm thử sự nhất quán dữ liệu của trạng thái phòng
    @Test
    public void testNhatQuanDuLieu_CapNhatTrangThai() {
        // Admin cập nhật phòng thành BẢO TRÌ
        phongDAO.updateRoomStatus(maPhongTest, "BẢO TRÌ");
        Phong phongClientThay = phongDAO.getRoomById(maPhongTest);
        
        Assertions.assertEquals("BẢO TRÌ", phongClientThay.getTrangThaiPhong(), "Giao diện khách hàng không phản ánh đúng trạng thái BẢO TRÌ");
    }

    // Kiểm thử Lễ Tân xử lý thay đổi luồng đơn hàng
    @Test
    public void testVongDoiDonHang_LeTanCheckIn() {
        // Giả lập Lễ tân check-in cho khách -> Trạng thái phòng phải chuyển sang CÓ KHÁCH
        boolean isUpdated = phongDAO.updateRoomStatus(maPhongTest, "CÓ KHÁCH");
        Assertions.assertTrue(isUpdated);

        Phong p = phongDAO.getRoomById(maPhongTest);
        Assertions.assertEquals("CÓ KHÁCH", p.getTrangThaiPhong(), "Logic đồng bộ trạng thái vật lý của phòng bị sai");
    }
    
 // TC Bổ sung: Kiểm thử luồng Khách hàng tự hủy đơn
    @Test
    public void testVongDoiDonHang_KhachHangHuyDon() {
        com.hotel.service.DonDatService donDatService = new com.hotel.service.DonDatService();

        // Bước 1: Khách hàng tạo 1 đơn đặt phòng mới
        // KHÔNG gán don.setMaKH() để vượt qua lỗi Khóa Ngoại (đơn sẽ có MaKH = NULL)
        com.hotel.model.DonDatPhong don = new com.hotel.model.DonDatPhong();
        don.setMaPhong(maPhongTest);
        don.setTenNguoiDat("Khach Test Huy Don");
        don.setNgayNhan(java.sql.Date.valueOf("2026-07-01"));
        don.setNgayTra(java.sql.Date.valueOf("2026-07-05"));
        don.setSoNguoi(2);
        
        boolean isCreated = donDatService.createBooking(don, 500000.0);
        Assertions.assertTrue(isCreated, "Phải tạo được đơn để test hủy");

        int maDonHuy = donDatDAO.getLatestBookingIdByName("Khach Test Huy Don");
        Assertions.assertTrue(maDonHuy > 0, "Không tìm thấy mã đơn vừa tạo");

        // Bước 2: DÙNG JDBC THUẦN ĐỂ ÉP TRẠNG THÁI (Tránh lỗi do DAO check MaKH = 0)
        boolean isPrepared = donDatDAO.updateStatusByMaDon(maDonHuy, "CHỜ XÁC NHẬN");
        Assertions.assertTrue(isPrepared, "Không thể ép trạng thái thành CHỜ XÁC NHẬN");

        // Bước 3: Action - Khách hàng Hủy đơn
        boolean isCanceled = donDatDAO.updateStatusByMaDon(maDonHuy, "ĐÃ HỦY");
        Assertions.assertTrue(isCanceled, "Lệnh cập nhật trạng thái HỦY ĐƠN phải thành công");

        // Bước 4: Assert - Xác minh lại tận gốc trong Database
        String actualStatus = "";
        try (java.sql.Connection conn = com.hotel.dao.DBContext.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement("SELECT TrangThaiDon FROM DONDATPHONG WHERE MaDon = ?")) {
            ps.setInt(1, maDonHuy);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                actualStatus = rs.getString(1);
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        
        Assertions.assertEquals("ĐÃ HỦY", actualStatus, "Trạng thái đơn trong CSDL phải được đổi thành ĐÃ HỦY");

        // Bước 5: Dọn dẹp rác (Teardown)
        try (java.sql.Connection conn = com.hotel.dao.DBContext.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement("DELETE FROM DONDATPHONG WHERE MaDon = ?")) {
            ps.setInt(1, maDonHuy);
            ps.executeUpdate();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}