package com.hotel.dto;

public class DoanhThuDTO {
    private String ngay;
    private double tongTien;

    public DoanhThuDTO(String ngay, double tongTien) {
        this.ngay = ngay;
        this.tongTien = tongTien;
    }
    public String getNgay() { return ngay; }
    public double getTongTien() { return tongTien; }
}