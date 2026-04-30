package com.hotel.service;

import com.hotel.dao.NhanVienDAO;
import com.hotel.model.NhanVien;

public class NhanVienService {
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public NhanVien getProfileByMaTK(int maTK) {
        return nhanVienDAO.getByMaTK(maTK);
    }
}