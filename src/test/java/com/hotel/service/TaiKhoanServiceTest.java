package com.hotel.service;

import com.hotel.model.TaiKhoan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaiKhoanServiceTest {

    private TaiKhoanService taiKhoanService;

    @BeforeEach
    public void setUp() {
        taiKhoanService = new TaiKhoanService();
    }

    @Test
    public void testLogin_Success() {
        String tenDNHopLe = "admin"; 
        String matKhauHopLe = "123";

        TaiKhoan result = taiKhoanService.login(tenDNHopLe, matKhauHopLe);
        Assertions.assertNotNull(result, "Đăng nhập hợp lệ phải trả về đối tượng TaiKhoan");
        Assertions.assertEquals(tenDNHopLe, result.getTenDN(), "Tên đăng nhập không khớp");
    }

    @Test
    public void testLogin_Fail_WrongPassword() {
        String tenDN = "admin";
        String matKhauSai = "mat_khau_bay_ba";

        TaiKhoan result = taiKhoanService.login(tenDN, matKhauSai);
        Assertions.assertNull(result, "Đăng nhập sai mật khẩu phải trả về null");
    }

    @Test
    public void testLogin_Fail_AccountNotExist() {
        String tenDNKhongTonTai = "user_ghost_9999";
        String matKhau = "123456";

        TaiKhoan result = taiKhoanService.login(tenDNKhongTonTai, matKhau);
        Assertions.assertNull(result, "Tài khoản không tồn tại phải trả về null");
    }
}