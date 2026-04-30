package com.hotel.service;

import com.hotel.dao.DonDatPhongDAO;
import com.hotel.model.DonDatPhong;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class DonDatService {
    private DonDatPhongDAO donDatDAO = new DonDatPhongDAO();

    public boolean createBooking(DonDatPhong don, double giaPhong) {
        long diffInMillies = Math.abs(don.getNgayTra().getTime() - don.getNgayNhan().getTime());
        long nights = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        if (nights <= 0) nights = 1;
        don.setTongTien(nights * giaPhong);
        
        return donDatDAO.insert(don);
    }
    
    public List<DonDatPhong> getHistoryByCustomer(int maKH) {
        return donDatDAO.getByMaKH(maKH);
    }
    
    public boolean cancelBooking(int maDon, int maKH) {
        return donDatDAO.updateStatus(maDon, maKH, "ĐÃ HỦY");
    }
    
    public List<DonDatPhong> getAllBookings() {
        return donDatDAO.getAll();
    }

    public boolean changeBookingStatus(int maDon, String status, int maNV) {
        return donDatDAO.updateStatusByReceptionist(maDon, status, maNV);
    }
    
    public boolean createWalkInBooking(DonDatPhong don, double giaPhong) {
        long diffInMillies = Math.abs(don.getNgayTra().getTime() - don.getNgayNhan().getTime());
        long nights = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
        
        if (nights <= 0) nights = 1;
        don.setTongTien(nights * giaPhong);
        
        return donDatDAO.insertByReceptionist(don);
    }
}