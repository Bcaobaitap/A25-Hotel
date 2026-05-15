package com.hotel.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
    private static final String PHONE_REGEX = "^(0)[0-9]{9}$"; // Bắt buộc 10 số, bắt đầu bằng 0

    //Kiểm tra rỗng
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    //Validate định dạng Email
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    //Validate định dạng Số điện thoại
    public static boolean isValidPhone(String phone) {
        if (isNullOrEmpty(phone)) return false;
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }

    //BỘ LỌC CHỐNG XSS (Cross-Site Scripting)
    // Biến các ký tự thực thi script thành mã an toàn để hiển thị
    public static String sanitize(String input) {
        if (input == null) return null;
        return input.replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;")
                    .trim();
    }
}