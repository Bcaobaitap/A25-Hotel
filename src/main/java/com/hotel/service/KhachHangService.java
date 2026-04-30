package com.hotel.service;

import com.hotel.dao.KhachHangDAO;
import com.hotel.model.KhachHang;

public class KhachHangService {
    private KhachHangDAO khachHangDAO = new KhachHangDAO();

    public KhachHang getProfileByMaTK(int maTK) {
        return khachHangDAO.getByMaTK(maTK);
    }
}