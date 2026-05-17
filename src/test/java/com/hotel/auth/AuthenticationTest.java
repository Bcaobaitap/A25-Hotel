package com.hotel.auth;

import com.hotel.dao.DBContext;
import com.hotel.dao.TaiKhoanDAO;
import com.hotel.model.TaiKhoan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuthenticationTest {
    private TaiKhoanDAO taiKhoanDAO;

    @BeforeEach
    public void setUp() {
        taiKhoanDAO = new TaiKhoanDAO();
        // Tạo 1 tài khoản cứng trực tiếp bằng JDBC để test
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO TAIKHOAN (TenDN, MatKhau, LoaiTaiKhoan) VALUES (?, ?, ?)")) {
            ps.setString(1, "test_user_tc1");
            ps.setString(2, "password_123");
            ps.setString(3, "USER");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Dọn dẹp: Xóa tài khoản test
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM TAIKHOAN WHERE TenDN = ?")) {
            ps.setString(1, "test_user_tc1");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDangNhap_ThanhCong() {
        TaiKhoan tk = taiKhoanDAO.checkLogin("test_user_tc1", "password_123");
        Assertions.assertNotNull(tk, "Đăng nhập với mật khẩu đúng phải trả về tài khoản");
        Assertions.assertEquals("USER", tk.getLoaiTaiKhoan());
    }

    @Test
    public void testDangNhap_SaiMatKhau() {
        TaiKhoan tk = taiKhoanDAO.checkLogin("test_user_tc1", "sai_mat_khau");
        Assertions.assertNull(tk, "Đăng nhập sai mật khẩu phải trả về NULL");
    }
}