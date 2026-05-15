package com.hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
	private static final String URL = "jdbc:mysql://localhost:3306/qlks_11";
	private static final String USER = "root";
	private static final String PASS = "123456";
	
	//chạy 1 lần khi ứng dụng Web khởi động
	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi nghiêm trọng: Không tìm thấy MySQL Driver.");
            e.printStackTrace();
        }
    }
	
	// Lấy kết nối
	public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Lỗi: Không thể kết nối tới Database.");
            e.printStackTrace();
            return null;
        }
    }
	
	// Đóng kết nối an toàn
	public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
