package com.hotel.model;

public class KhachHang {
	private int maKH;
    private int maTK; // Khóa ngoại liên kết tới TaiKhoan
    private String hoTen;
    private String email;
    private String sdt;

    public KhachHang() {}

    public KhachHang(int maKH, int maTK, String hoTen, String email, String sdt) {
        this.maKH = maKH;
        this.maTK = maTK;
        this.hoTen = hoTen;
        this.email = email;
        this.sdt = sdt;
    }

    public int getMaKH() { return maKH; }
    public void setMaKH(int maKH) { this.maKH = maKH; }

    public int getMaTK() { return maTK; }
    public void setMaTK(int maTK) { this.maTK = maTK; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    
    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH=" + maKH +
                ", maTK=" + maTK +
                ", hoTen='" + hoTen + '\'' +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
