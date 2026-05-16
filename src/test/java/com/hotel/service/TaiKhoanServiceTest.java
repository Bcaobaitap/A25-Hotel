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

    // TEST CASE 1: Kiểm thử đăng nhập với mật khẩu đúng
    @Test
    public void testLogin_Success() {
        String tenDNHopLe = "admin"; 
        String matKhauHopLe = "123";

        TaiKhoan result = taiKhoanService.login(tenDNHopLe, matKhauHopLe);
        Assertions.assertNotNull(result, "Đăng nhập hợp lệ phải trả về đối tượng TaiKhoan");
        Assertions.assertEquals(tenDNHopLe, result.getTenDN(), "Tên đăng nhập không khớp");
    }

    // TEST CASE 2: Kiểm thử đăng nhập với mật khẩu sai
    @Test
    public void testLogin_Fail_WrongPassword() {
        String tenDN = "admin";
        String matKhauSai = "mat_khau_bay_ba";

        TaiKhoan result = taiKhoanService.login(tenDN, matKhauSai);
        Assertions.assertNull(result, "Đăng nhập sai mật khẩu phải trả về null");
    }

    // TEST CASE 3: Kiểm thử đăng nhập với tài khoản không tồn tại
    @Test
    public void testLogin_Fail_AccountNotExist() {
        String tenDNKhongTonTai = "user_ghost_9999";
        String matKhau = "123456";

        TaiKhoan result = taiKhoanService.login(tenDNKhongTonTai, matKhau);
        Assertions.assertNull(result, "Tài khoản không tồn tại phải trả về null");
    }
    
    // TEST CASE 4: Kiểm thử ngăn chặn đăng ký trùng tên đăng nhập
    @Test
    public void testRegisterCustomer_WithDuplicateUsername_ShouldReturnErrorMessage() {
        String duplicateUsername = "admin";
        String matKhau = "password123";
        String hoTen = "Nguoi Dung Clone";
        String email = "clone@gmail.com";
        String sdt = "0988777666";
        
        String result = taiKhoanService.registerCustomer(duplicateUsername, matKhau, hoTen, email, sdt);   
        String expectedError = "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác!";

        Assertions.assertEquals(expectedError, result, "Lỗi: Hệ thống không chặn được việc đăng ký trùng tên đăng nhập!");
    }
}