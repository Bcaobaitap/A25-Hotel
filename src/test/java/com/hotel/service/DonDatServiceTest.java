package com.hotel.service;

import com.hotel.model.DonDatPhong;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;

public class DonDatServiceTest {

    private DonDatService donDatService;

    @BeforeEach
    public void setUp() {
        donDatService = new DonDatService();
    }

    // TEST CASE 1: Đặt phòng nhiều ngày, kiểm tra tính toán tổng tiền
    @Test
    public void testCreateBooking_CalculateTotalPrice_Success() {
        DonDatPhong don = new DonDatPhong();
        don.setMaKH(2); 
        don.setMaPhong(1); 
        don.setTenNguoiDat("Nguyen Tien Thanh");
        don.setThongTinLienHe("0987654321");
        don.setSoNguoi(2);
        don.setNgayNhan(Date.valueOf("2026-05-10"));
        don.setNgayTra(Date.valueOf("2026-05-13"));

        double giaPhongMoiDem = 500000.0; 
        boolean isCreated = donDatService.createBooking(don, giaPhongMoiDem);

        // Hàm phải trả về true 
        Assertions.assertTrue(isCreated, "Lỗi: Không thể tạo đơn đặt phòng trong Database.");
        
        // Tổng tiền phải được tính đúng (3 đêm * 500.000 = 1.500.000)
        double expectedTongTien = 1500000.0;
        Assertions.assertEquals(expectedTongTien, don.getTongTien(), "Lỗi: Logic tính tổng tiền bị sai!");
    }

    // TEST CASE 2: Đặt và trả phòng trong cùng một ngày 
    @Test
    public void testCreateBooking_SameDay_ShouldCountAsOneNight() {
        DonDatPhong don = new DonDatPhong();
        don.setMaKH(1);
        don.setMaPhong(2);
        don.setTenNguoiDat("Khach Test Cung Ngay");
        don.setThongTinLienHe("0111222333");
        don.setSoNguoi(1);

        don.setNgayNhan(Date.valueOf("2026-05-15"));
        don.setNgayTra(Date.valueOf("2026-05-15")); 
        
        double giaPhong = 400000.0;
        boolean isCreated = donDatService.createBooking(don, giaPhong);

        Assertions.assertTrue(isCreated, "Lỗi: Không thể tạo đơn đặt phòng.");
        Assertions.assertEquals(giaPhong, don.getTongTien(), "Lỗi: Đặt cùng ngày phải được tính tiền tối thiểu 1 đêm!");
    }
    
    // TEST CASE 3: Kiểm thử chặn trùng lịch 
    @Test
    public void testCreateBooking_WhenRoomAlreadyBooked_ShouldReturnFalse() {
        com.hotel.model.DonDatPhong don = new com.hotel.model.DonDatPhong();
        don.setMaKH(1);
        don.setMaPhong(1); // Giả sử Phòng 1 đang có đơn tồn tại trong DB
        don.setTenNguoiDat("Khach Den Sau");
        don.setThongTinLienHe("0999888777");
        
        // Cố tình đặt đè thời gian
        don.setNgayNhan(java.sql.Date.valueOf("2026-05-16")); 
        don.setNgayTra(java.sql.Date.valueOf("2026-05-19"));
        
        boolean isCreated = donDatService.createBooking(don, 500000.0);

        Assertions.assertFalse(isCreated, "Lỗi Nghiệp vụ: Hệ thống cho phép đặt trùng phòng (Overbooking)!");
    }

    // TEST CASE 4: Kiểm thử luồng Hủy đơn đặt phòng hợp lệ
    @Test
    public void testCancelBooking_WithValidData_ShouldReturnTrue() {
        int maDonHopLe = 34; // Giả sử đơn số 1 thuộc về khách hàng số 2
        int maKHHopLe = 1;  
        
        boolean isCanceled = donDatService.cancelBooking(maDonHopLe, maKHHopLe);

        Assertions.assertTrue(isCanceled, "Lỗi: Không thể cập nhật trạng thái đơn thành ĐÃ HỦY với dữ liệu hợp lệ");
    }
}