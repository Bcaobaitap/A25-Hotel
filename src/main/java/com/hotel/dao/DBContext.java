package com.hotel.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBContext {
    private static HikariDataSource dataSource;

    // Khối static {} chỉ chạy ĐÚNG 1 LẦN duy nhất khi Tomcat vừa khởi động xong
    static {
        try {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl("jdbc:mysql://localhost:3306/qlks_11");
            config.setUsername("root");
            config.setPassword("thanhcv000");
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            // Tối ưu hóa "Hồ chứa" (Pool Sizing)
            config.setMaximumPoolSize(10); // Khởi tạo sẵn tối đa 10 kết nối cùng lúc
            config.setMinimumIdle(2);      // Luôn giữ tối thiểu 2 kết nối "ngủ" chờ việc
            config.setIdleTimeout(30000);  
            config.setConnectionTimeout(10000); 

            // Tối ưu hóa MySQL Driver 
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            dataSource = new HikariDataSource(config);
            
        } catch (Exception e) {
            System.err.println("Lỗi nghiêm trọng: Không thể khởi tạo HikariCP.");
            e.printStackTrace();
        }
    }

    // Hàm gọi để mượn kết nối từ Pool
    public static Connection getConnection() {
        try {
            return dataSource.getConnection(); 
        } catch (SQLException e) {
            System.err.println("Lỗi: Pool đã cạn hoặc Database ngưng hoạt động.");
            e.printStackTrace();
            return null;
        }
    }

    // Hàm gọi để trả kết nối về Pool
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