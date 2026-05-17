package com.hotel.service;

import com.hotel.dao.PhongDAO;
import com.hotel.dao.TienIchDAO;
import com.hotel.model.Phong;
import java.util.List;

public class PhongService {
	private PhongDAO phongDAO = new PhongDAO();
	private TienIchDAO tienIchDAO = new TienIchDAO();
	
	public List<Phong> getAllRooms() {
        return phongDAO.getAllRooms();
    }
	
	public Phong getRoomDetail(int id) {
		Phong p = phongDAO.getRoomById(id);
		
		if (p != null) {
            p.setListTienIch(tienIchDAO.getTienIchByPhong(id));
        }
        return p;
	}
	
	public boolean updateRoomStatus(int maPhong, String status) {
	    return phongDAO.updateRoomStatus(maPhong, status);
	}
	
	// Thêm phòng mới
	public boolean createRoom(Phong p) {
	    return phongDAO.insertRoom(p);
	}

	// Cập nhật phòng
	public boolean updateRoom(Phong p) {
	    return phongDAO.updateRoom(p);
	}

	// Xóa phòng
	public boolean deleteRoom(int maPhong) {
	    return phongDAO.deleteRoom(maPhong);
	}
	
	public List<Phong> getRoomStatusByDate(java.sql.Date targetDate) {
	    return phongDAO.getRoomStatusByDate(targetDate);
	}
}