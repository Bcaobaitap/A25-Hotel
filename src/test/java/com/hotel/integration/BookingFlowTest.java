package com.hotel.integration;

import com.hotel.dao.DBContext;
import com.hotel.dao.DonDatPhongDAO;
import com.hotel.dao.PhongDAO;
import com.hotel.model.DonDatPhong;
import com.hotel.model.Phong;
import com.hotel.service.DonDatService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingFlowTest {
    private DonDatService donDatService;
    private DonDatPhongDAO donDatDAO;
    private PhongDAO phongDAO;
    private int maPhongTest;

    @BeforeEach
    public void setUp() {
        donDatService = new DonDatService();
        donDatDAO = new DonDatPhongDAO();
        phongDAO = new PhongDAO();

        // 1. Tạo 1 phòng test (Giá 500k/đêm)
        Phong p = new Phong();
        p.setTenPhong("Phòng Test Flow");
        p.setLoaiPhong("Phòng đơn");
        p.setGia(500000.0);
        p.setTrangThaiPhong("TRỐNG");
        p.setDienTich(30.0);
        p.setMoTa("Test");
        phongDAO.insertRoom(p);

        maPhongTest = phongDAO.getRoomIdByName("Phòng Test Flow");
    }

    @AfterEach
    public void tearDown() {
        // Dọn dẹp đơn đặt phòng và phòng test
        try (Connection conn = DBContext.getConnection()) {
            PreparedStatement psDon = conn.prepareStatement("DELETE FROM DONDATPHONG WHERE TenNguoiDat = 'Khach Test Overbooking' OR TenNguoiDat = 'Khach Test Tinh Tien'");
            psDon.executeUpdate();
            
            PreparedStatement psPhong = conn.prepareStatement("DELETE FROM PHONG WHERE TenPhong = 'Phòng Test Flow'");
            psPhong.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TC Tính Tiền: Kiểm tra thuật toán nhân giá phòng và số đêm
    @Test
    public void testTinhTienPhong_NhieuDem() {
        DonDatPhong don = new DonDatPhong();
        don.setMaPhong(maPhongTest);
        don.setTenNguoiDat("Khach Test Tinh Tien");
        don.setNgayNhan(Date.valueOf("2026-06-01"));
        don.setNgayTra(Date.valueOf("2026-06-04")); // 3 đêm
        don.setSoNguoi(2);
        
        boolean isCreated = donDatService.createBooking(don, 500000.0);
        
        Assertions.assertTrue(isCreated);
        // Kì vọng: 3 đêm * 500.000 = 1.500.000 VNĐ
        Assertions.assertEquals(1500000.0, don.getTongTien(), "Logic tính tiền phòng theo ngày bị sai");
    }

    //Kiểm tra cơ chế chặn đặt trùng lịch (Overbooking)
    @Test
    public void testChongOverbooking_GiaoCatNgay() {
        // Khách A đặt phòng từ 10/06 đến 15/06
        DonDatPhong donA = new DonDatPhong();
        donA.setMaPhong(maPhongTest);
        donA.setTenNguoiDat("Khach Test Overbooking");
        donA.setNgayNhan(Date.valueOf("2026-07-10"));
        donA.setNgayTra(Date.valueOf("2026-07-15"));
        donDatService.createBooking(donA, 500000.0);

        // Hệ thống cập nhật trạng thái đơn A thành ĐÃ XÁC NHẬN để khóa lịch
        int maDonA = donDatDAO.getLatestBookingIdByName("Khach Test Overbooking");
        donDatDAO.updateStatus(maDonA, donA.getMaKH() == null ? 0 : donA.getMaKH(), "ĐÃ XÁC NHẬN");

        // Khách B cố tình đặt đè vào lịch 12/06 đến 16/06
        DonDatPhong donB = new DonDatPhong();
        donB.setMaPhong(maPhongTest);
        donB.setTenNguoiDat("Khach Test Overbooking"); 
        donB.setNgayNhan(Date.valueOf("2026-07-12"));
        donB.setNgayTra(Date.valueOf("2026-07-16"));

        // Kì vọng createBooking trả về false vì phòng đã bị chặn
        boolean isCreatedB = donDatService.createBooking(donB, 500000.0);
        Assertions.assertFalse(isCreatedB, "Lỗi nghiêm trọng: Hệ thống không chặn Overbooking");
    }
}