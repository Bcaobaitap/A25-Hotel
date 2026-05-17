package com.hotel.model;

import java.util.Date;

public class DonDatPhong {
    private int maDon;
    private Integer maKH; // Dùng Integer thay vì int để cho phép giá trị null
    private Integer maNV;
    private int maPhong;
    private Date ngayTaoDon;
    private String trangThaiDon;
    private double tongTien;
    private String tenNguoiDat;
    private Date ngayNhan;
    private Date ngayTra;
    private int soNguoi;
    private String thongTinLienHe;
    private java.sql.Timestamp ngayCapNhat;

    public DonDatPhong() {}

    public DonDatPhong(int maDon, Integer maKH, Integer maNV, int maPhong, Date ngayTaoDon, String trangThaiDon, double tongTien, String tenNguoiDat, Date ngayNhan, Date ngayTra, int soNguoi, String thongTinLienHe) {
        this.maDon = maDon;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maPhong = maPhong;
        this.ngayTaoDon = ngayTaoDon;
        this.trangThaiDon = trangThaiDon;
        this.tongTien = tongTien;
        this.tenNguoiDat = tenNguoiDat;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.soNguoi = soNguoi;
        this.thongTinLienHe = thongTinLienHe;
    }

    public int getMaDon() { return maDon; }
    public void setMaDon(int maDon) { this.maDon = maDon; }

    public Integer getMaKH() { return maKH; }
    public void setMaKH(Integer maKH) { this.maKH = maKH; }

    public Integer getMaNV() { return maNV; }
    public void setMaNV(Integer maNV) { this.maNV = maNV; }

    public int getMaPhong() { return maPhong; }
    public void setMaPhong(int maPhong) { this.maPhong = maPhong; }

    public Date getNgayTaoDon() { return ngayTaoDon; }
    public void setNgayTaoDon(Date ngayTaoDon) { this.ngayTaoDon = ngayTaoDon; }

    public String getTrangThaiDon() { return trangThaiDon; }
    public void setTrangThaiDon(String trangThaiDon) { this.trangThaiDon = trangThaiDon; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getTenNguoiDat() { return tenNguoiDat; }
    public void setTenNguoiDat(String tenNguoiDat) { this.tenNguoiDat = tenNguoiDat; }

    public Date getNgayNhan() { return ngayNhan; }
    public void setNgayNhan(Date ngayNhan) { this.ngayNhan = ngayNhan; }

    public Date getNgayTra() { return ngayTra; }
    public void setNgayTra(Date ngayTra) { this.ngayTra = ngayTra; }

    public int getSoNguoi() { return soNguoi; }
    public void setSoNguoi(int soNguoi) { this.soNguoi = soNguoi; }

    public String getThongTinLienHe() { return thongTinLienHe; }
    public void setThongTinLienHe(String thongTinLienHe) { this.thongTinLienHe = thongTinLienHe; }
    
    public java.sql.Timestamp getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(java.sql.Timestamp ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat; 
    }
}