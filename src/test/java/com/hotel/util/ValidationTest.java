package com.hotel.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationTest {

    @Test
    public void testSanitize_ChongXSS() {
        // Giả lập hacker nhập mã độc vào ô Tên Khách Hàng
        String inputDocHai = "<script>window.location='http://hacker.com';</script>";
        
        // Kì vọng: Các thẻ HTML phải bị bẻ gãy thành ký tự an toàn
        String expectedSafe = "&lt;script&gt;window.location=&#x27;http://hacker.com&#x27;;&lt;/script&gt;";
        
        String actualOutput = ValidationUtil.sanitize(inputDocHai);
        Assertions.assertEquals(expectedSafe, actualOutput, "Lỗi: Hàm Sanitize không mã hóa đúng các thẻ HTML nguy hiểm!");
    }

    @Test
    public void testValidationPhone_HopLe() {
        Assertions.assertTrue(ValidationUtil.isValidPhone("0987654321"));
        Assertions.assertFalse(ValidationUtil.isValidPhone("987654321")); // Không bắt đầu bằng 0
        Assertions.assertFalse(ValidationUtil.isValidPhone("098765432a")); // Chứa chữ cái
    }
}