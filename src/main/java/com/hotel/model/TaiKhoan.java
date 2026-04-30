package com.hotel.model;

public class TaiKhoan {
	private int maTK;
    private String tenDN;
    private String matKhau;
    private String loaiTaiKhoan;
    
    //Trường phụ để chứa dữ liệu khi JOIN với các bảng Profile
    private String hoTen; 
    
    public TaiKhoan() {};
    
    public TaiKhoan(int maTK, String tenDN, String matKhau, String loaiTaiKhoan) {
        this.maTK = maTK;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
    
    public TaiKhoan(int maTK, String tenDN, String matKhau, String loaiTaiKhoan, String hoTen) {
        this.maTK = maTK;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.hoTen = hoTen;
    }

    
    public int getMaTK() { return maTK; }
    public void setMaTK(int maTK) { this.maTK = maTK; }

    public String getTenDN() { return tenDN; }
    public void setTenDN(String tenDN) { this.tenDN = tenDN; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getLoaiTaiKhoan() { return loaiTaiKhoan; }
    public void setLoaiTaiKhoan(String loaiTaiKhoan) { this.loaiTaiKhoan = loaiTaiKhoan; }
    
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
}
