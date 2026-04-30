package com.hotel.model;

public class NhanVien {
    private int maNV;
    private int maTK;
    private String hoTen;

    public NhanVien() {}

    public NhanVien(int maNV, int maTK, String hoTen) {
        this.maNV = maNV;
        this.maTK = maTK;
        this.hoTen = hoTen;
    }

    public int getMaNV() { return maNV; }
    public void setMaNV(int maNV) { this.maNV = maNV; }

    public int getMaTK() { return maTK; }
    public void setMaTK(int maTK) { this.maTK = maTK; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
}