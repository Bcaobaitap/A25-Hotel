<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Tạo Đơn Tại Quầy - Lễ Tân</title>
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <link href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-icons.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-svg.css" rel="stylesheet" />
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <link id="pagestyle" href="${pageContext.request.contextPath}/assets/css/dashboard_css/material-dashboard.css?v=3.0.0" rel="stylesheet" />
</head>
<body class="g-sidenav-show bg-gray-200">
  
  <jsp:include page="/WEB-INF/views/common/dashboard_sidebar.jsp" />
  
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <jsp:include page="/WEB-INF/views/common/dashboard_header.jsp" />
    
    <div class="container-fluid py-4">
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">Check-in Khách Mới</h4>
                <p class="text-white text-center text-sm mb-2">Phòng: ${room.tenPhong} (${room.loaiPhong})</p>
              </div>
            </div>
            <div class="card-body">
              <form role="form" class="text-start" action="${pageContext.request.contextPath}/receptionist/book-room" method="POST">
                <input type="hidden" name="maPhong" value="${room.maPhong}">
                
                <div class="row">
                  <div class="col-md-6 mb-3">
                    <div class="input-group input-group-outline">
                      <label class="form-label">Tên khách hàng</label>
                      <input type="text" name="tenNguoiDat" class="form-control" required>
                    </div>
                  </div>
                  <div class="col-md-6 mb-3">
                    <div class="input-group input-group-outline">
                      <label class="form-label">Số điện thoại / Căn cước</label>
                      <input type="text" name="thongTinLienHe" class="form-control" required>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-4 mb-3">
                    <div class="input-group input-group-static">
                      <label>Ngày Nhận</label>
                      <!-- Thường khách tại quầy sẽ nhận phòng ngay hôm nay -->
                      <input type="date" name="ngayNhan" class="form-control" id="todayDate" required>
                    </div>
                  </div>
                  <div class="col-md-4 mb-3">
                    <div class="input-group input-group-static">
                      <label>Ngày Trả (Dự kiến)</label>
                      <input type="date" name="ngayTra" class="form-control" required>
                    </div>
                  </div>
                  <div class="col-md-4 mb-3">
                    <div class="input-group input-group-static">
                      <label>Số người ở</label>
                      <input type="number" name="soNguoi" class="form-control" value="1" min="1" required>
                    </div>
                  </div>
                </div>

                <div class="text-center mt-4">
                  <a href="${pageContext.request.contextPath}/receptionist/room-status" class="btn btn-outline-secondary w-30 my-4 mb-2 me-2">Hủy</a>
                  <button type="submit" class="btn bg-gradient-primary w-40 my-4 mb-2">Tạo Đơn & Nhận Phòng</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  
  <script>

    document.addEventListener('DOMContentLoaded', function() {
        var today = new Date().toISOString().split('T')[0];
        document.getElementById("todayDate").value = today;
    });
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/material-dashboard.min.js?v=3.0.0"></script>
</body>
</html>