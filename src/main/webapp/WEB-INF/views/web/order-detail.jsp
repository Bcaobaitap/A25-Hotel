<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body class="sub_page">
  <div class="hero_area">
    <div class="bg-box">
      <img src="${pageContext.request.contextPath}/assets/images/homepage_img/hero-bg.jpg" alt="">
    </div>
    <header class="header_section">
      <div class="container">
         <nav class="navbar navbar-expand-lg custom_nav-container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
              <span>Chi Tiết Đơn Đặt Phòng</span>
            </a>
         </nav>
      </div>
    </header>
  </div>

  <section class="book_section layout_padding">
    <div class="container">
      <div class="heading_container mb-4">
        <h2>Mã Đơn: #${donDat.maDon}</h2>
      </div>

      <div class="row">
        <!-- Cột thông tin Đơn hàng và Người đặt -->
        <div class="col-md-7">
          <div class="card shadow-sm mb-4" style="border-radius: 10px; border: none;">
            <div class="card-body p-4">
              <h4 style="color: #222831; border-bottom: 2px solid #ffbe33; padding-bottom: 10px; margin-bottom: 20px;">
                <i class="fa fa-info-circle me-2"></i> Thông Tin Khách Hàng
              </h4>
              <p><strong>Người nhận phòng:</strong> ${donDat.tenNguoiDat}</p>
              <p><strong>Số điện thoại:</strong> ${donDat.thongTinLienHe}</p>
              <p><strong>Số lượng khách:</strong> ${donDat.soNguoi} người</p>

              <h4 style="color: #222831; border-bottom: 2px solid #ffbe33; padding-bottom: 10px; margin-bottom: 20px; margin-top: 30px;">
                <i class="fa fa-calendar me-2"></i> Chi Tiết Lưu Trú
              </h4>
              <p><strong>Ngày tạo đơn:</strong> <fmt:formatDate value="${donDat.ngayTaoDon}" pattern="dd/MM/yyyy HH:mm:ss" /></p>
              <p><strong>Check-in (Nhận phòng):</strong> <fmt:formatDate value="${donDat.ngayNhan}" pattern="dd/MM/yyyy" /></p>
              <p><strong>Check-out (Trả phòng):</strong> <fmt:formatDate value="${donDat.ngayTra}" pattern="dd/MM/yyyy" /></p>
              
              <div class="mt-4 p-3 rounded" style="background-color: #f8f9fa; border-left: 5px solid #ffbe33;">
                <h5 style="margin-bottom: 10px;">Trạng thái đơn hàng: 
                    <span class="badge 
                        ${donDat.trangThaiDon == 'CHỜ XÁC NHẬN' ? 'badge-warning' : 
                          donDat.trangThaiDon == 'ĐÃ XÁC NHẬN' ? 'badge-success' : 
                          donDat.trangThaiDon == 'ĐÃ HỦY' ? 'badge-danger' : 'badge-secondary'}">
                        ${donDat.trangThaiDon}
                    </span>
                </h5>
                <h4 style="color: #e74c3c; margin-bottom: 0;"><strong>Tổng tiền: <fmt:formatNumber value="${donDat.tongTien}" type="number" /> VNĐ</strong></h4>
              </div>
            </div>
          </div>
          
          <a href="${pageContext.request.contextPath}/my-orders" class="btn btn-secondary mt-2" style="border-radius: 20px;">
             <i class="fa fa-arrow-left"></i> Quay lại Lịch sử
          </a>
        </div>
        
        <!-- Cột thông tin Phòng -->
        <div class="col-md-5">
          <div class="detail-box shadow-sm" style="background: rgba(255,255,255,1); padding: 25px; border-radius: 10px; border: 1px solid #eaeaea;">
            <h4 style="color: #0066cc;">
              <i class="fa fa-bed me-2"></i> ${phong.tenPhong}
            </h4>
            <hr style="border-color: #ddd;">
            <img src="${pageContext.request.contextPath}/assets/images/homepage_img/${phong.anhPhong}" 
                 style="width: 100%; height: 250px; object-fit: cover; border-radius: 8px; margin-bottom: 15px;" 
                 onerror="this.src='${pageContext.request.contextPath}/assets/images/homepage_img/hero-bg.jpg'">
            
            <p style="color: black; font-size: 16px;"><strong>Phân loại:</strong> ${phong.loaiPhong}</p>
            <p style="color: black; font-size: 16px;"><strong>Vị trí:</strong> Tầng ${phong.soTang}</p>
            <p style="color: black; font-size: 16px;"><strong>Đơn giá:</strong> <span style="color: #ffbe33; font-weight: bold;"><fmt:formatNumber value="${phong.gia}" type="number" /> VNĐ/đêm</span></p>
            
            <a href="${pageContext.request.contextPath}/room-detail?id=${phong.maPhong}" 
               class="btn btn-outline-primary w-100 mt-3" 
               style="border-radius: 20px;">
               Xem phòng này
            </a>
          </div>
        </div>
      </div>
    </div>
  </section>

  <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  
  <script src="${pageContext.request.contextPath}/assets/js/homepage_js/jquery-3.4.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/homepage_js/bootstrap.js"></script>
</body>
</html>