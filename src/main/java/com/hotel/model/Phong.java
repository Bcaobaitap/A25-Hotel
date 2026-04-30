package com.hotel.model;

import java.util.List;

public class Phong {
    private int maPhong;
    private String tenPhong;
    private String loaiPhong;
    private int soTang;
    private double gia;
    private String trangThaiPhong;
    private double dienTich;
    private String moTa;
    private String anhPhong;

    private List<TienIch> listTienIch;
    
    public Phong() {}

    public Phong(int maPhong, String tenPhong, String loaiPhong, int soTang, double gia, String trangThaiPhong, double dienTich, String moTa, String anhPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.loaiPhong = loaiPhong;
        this.soTang = soTang;
        this.gia = gia;
        this.trangThaiPhong = trangThaiPhong;
        this.dienTich = dienTich;
        this.moTa = moTa;
        this.anhPhong = anhPhong;
    }

    public int getMaPhong() { return maPhong; }
    public void setMaPhong(int maPhong) { this.maPhong = maPhong; }

    public String getTenPhong() { return tenPhong; }
    public void setTenPhong(String tenPhong) { this.tenPhong = tenPhong; }

    public String getLoaiPhong() { return loaiPhong; }
    public void setLoaiPhong(String loaiPhong) { this.loaiPhong = loaiPhong; }

    public int getSoTang() { return soTang; }
    public void setSoTang(int soTang) { this.soTang = soTang; }

    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public String getTrangThaiPhong() { return trangThaiPhong; }
    public void setTrangThaiPhong(String trangThaiPhong) { this.trangThaiPhong = trangThaiPhong; }

    public double getDienTich() { return dienTich; }
    public void setDienTich(double dienTich) { this.dienTich = dienTich; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getAnhPhong() { return anhPhong; }
    public void setAnhPhong(String anhPhong) { this.anhPhong = anhPhong; }
    
    public List<TienIch> getListTienIch() {
        return listTienIch;
    }

    public void setListTienIch(List<TienIch> listTienIch) {
        this.listTienIch = listTienIch;
    }
}