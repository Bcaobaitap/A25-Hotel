package com.hotel.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationUtilTest {

    //TEST CASE 1: Kiểm thử rào chắn XSS (Cross-Site Scripting)
    @Test
    public void testSanitize_ShouldEscapeHtmlTags() {
        // Giả lập dữ liệu đầu vào chứa mã độc JS
        String inputMalicious = "<script>alert('Hacked!');</script>";
        // Ký tự < và > phải bị biến đổi thành entities an toàn
        String expectedSafe = "&lt;script&gt;alert(&#x27;Hacked!&#x27;);&lt;/script&gt;";
        
        String result = ValidationUtil.sanitize(inputMalicious);
        
        Assertions.assertEquals(expectedSafe, result, "Lỗi: Hàm Sanitize không mã hóa đúng các thẻ HTML/Script nguy hiểm!");
    }

    //TEST CASE 2: Kiểm thử số điện thoại
    @Test
    public void testIsValidPhone_WithInvalidPatterns_ShouldReturnFalse() {
        String phoneTooShort = "0987654"; // Thiếu số
        String phoneWithLetters = "098765abcd"; // Chứa chữ cái
        String phoneNotStartingWithZero = "84987654321"; // Không bắt đầu bằng 0
        
        Assertions.assertFalse(ValidationUtil.isValidPhone(phoneTooShort), "Lỗi: Lọt số điện thoại quá ngắn");
        Assertions.assertFalse(ValidationUtil.isValidPhone(phoneWithLetters), "Lỗi: Lọt số điện thoại chứa chữ cái");
        Assertions.assertFalse(ValidationUtil.isValidPhone(phoneNotStartingWithZero), "Lỗi: Lọt số không bắt đầu bằng số 0");
    }
}