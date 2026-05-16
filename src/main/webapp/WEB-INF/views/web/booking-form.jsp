<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
              <span> Booking </span>
            </a>
         </nav>
      </div>
    </header>
  </div>

  <section class="book_section layout_padding">
    <div class="container">
      <div class="heading_container">
        <h2>Xác Nhận Đặt Phòng</h2>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="form_container">
          <c:if test="${not empty error}">
    		<div style="color: #721c24; background-color: #f8d7da; padding: 10px; border-radius: 5px; margin-bottom: 15px; border: 1px solid #f5c6cb;">
        		<i class="fa fa-exclamation-triangle" aria-hidden="true" style="margin-right: 5px;"></i>
        		${error}
    		</div>
		</c:if>
            <form action="${pageContext.request.contextPath}/booking" method="POST">
              <input type="hidden" name="maPhong" value="${room.maPhong}">
              
              <div>
                <input type="text" class="form-control" name="tenNguoiDat" placeholder="Họ và tên" required />
              </div>
              <div>
                <input type="text" class="form-control" name="thongTinLienHe" id="phoneInputBooking" placeholder="Số điện thoại" required />
              </div>
              <div>
                <label style="color: black;">Ngày nhận phòng:</label>
                <input type="date" class="form-control" name="ngayNhan" id="ngayNhan" required />
              </div>
              <div>
                <label style="color: black;">Ngày trả phòng:</label>
                <input type="date" class="form-control" name="ngayTra" id="ngayTra" required />
              </div>
              <div>
                <select class="form-control nice-select wide" name="soNguoi">
                  <option value="1">1 Người</option>
                  <option value="2">2 Người</option>
                  <option value="3">3 Người</option>
                  <option value="4">4 Người</option>
                </select>
              </div>
              <div class="btn_box">
                <button type="submit">XÁC NHẬN ĐẶT PHÒNG</button>
              </div>
            </form>
          </div>
        </div>
        
        <div class="col-md-6">
          <div class="detail-box" style="background: rgba(255,255,255,0.9); padding: 20px; border-radius: 10px;">
            <h4 style="color: #0066cc;">Thông tin phòng</h4>
            <hr style="border-color: #ddd;">
            <img src="${pageContext.request.contextPath}/assets/images/homepage_img/${room.anhPhong}" style="width: 100%; border-radius: 5px; margin-bottom: 15px;">
            <p style="color: black;"><strong>Phòng:</strong> ${room.tenPhong}</p>
            <p style="color: black;"><strong>Loại:</strong> ${room.loaiPhong}</p>
            <p style="color: black;"><strong>Giá niêm yết:</strong> <span style="color: #ffbe33;">${room.gia} VNĐ/đêm</span></p>
          </div>
        </div>
      </div>
    </div>
  </section>

  <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  
  <script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Validation cũ
        Validator.applyValidation('phoneInputBooking', 'phone', 'Số điện thoại không hợp lệ');
        
        // GỌI HÀM VALIDATION NGÀY THÁNG MỚI CHỈ VỚI 1 DÒNG
        Validator.applyDateValidation('ngayNhan', 'ngayTra');
    });
</script>

</body>
</html>