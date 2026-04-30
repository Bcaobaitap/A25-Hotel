package com.hotel.dao;

import com.hotel.model.TienIch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TienIchDAO {
    public List<TienIch> getTienIchByPhong(int maPhong) {
        List<TienIch> list = new ArrayList<>();
        String sql = "SELECT t.MaTienIch, t.TenTienIch FROM TIENICH t " +
                     "INNER JOIN CHITIET_PHONG c ON t.MaTienIch = c.MaTienIch " +
                     "WHERE c.MaPhong = ?";
                     
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maPhong);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new TienIch(
                        rs.getInt("MaTienIch"),
                        rs.getString("TenTienIch")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}