package com.hotel.model;

public class TienIch {
    private int maTienIch;
    private String tenTienIch;

    public TienIch() {}

    public TienIch(int maTienIch, String tenTienIch) {
        this.maTienIch = maTienIch;
        this.tenTienIch = tenTienIch;
    }

    public int getMaTienIch() { return maTienIch; }
    public void setMaTienIch(int maTienIch) { this.maTienIch = maTienIch; }

    public String getTenTienIch() { return tenTienIch; }
    public void setTenTienIch(String tenTienIch) { this.tenTienIch = tenTienIch; }
}