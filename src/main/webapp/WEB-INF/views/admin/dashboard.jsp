<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/dashboard_img/favicon.png">
  <title>Trang Chủ Quản Trị - Khách Sạn A25</title>
  
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <link href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-icons.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-svg.css" rel="stylesheet" />
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <link id="pagestyle" href="${pageContext.request.contextPath}/assets/css/dashboard_css/material-dashboard.css?v=3.0.0" rel="stylesheet" />
</head>

<body class="g-sidenav-show bg-gray-200">
  
  <!-- Sidebar dùng chung -->
  <jsp:include page="/WEB-INF/views/common/dashboard_sidebar.jsp" />
  
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <!-- Header dùng chung -->
    <jsp:include page="/WEB-INF/views/common/dashboard_header.jsp" />
    
    <div class="container-fluid py-4">
      <div class="row">
        <!-- Card 1: Tổng doanh thu (Chờ lấy dữ liệu động) -->
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-dark shadow-dark text-center border-radius-xl mt-n4 position-absolute">
                <i class="material-icons opacity-10">weekend</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">Tổng số phòng</p>
                <h4 class="mb-0">${tongSoPhong != null ? tongSoPhong : '0'}</h4>
              </div>
            </div>
            <hr class="dark horizontal my-0">
            <div class="card-footer p-3">
              <p class="mb-0"><span class="text-success text-sm font-weight-bolder">Trạng thái: </span>Đang hoạt động</p>
            </div>
          </div>
        </div>
        
        <!-- Card 2: Lượt đặt phòng (Chờ lấy dữ liệu động) -->
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-primary shadow-primary text-center border-radius-xl mt-n4 position-absolute">
                <i class="material-icons opacity-10">receipt_long</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">Tổng đơn đặt</p>
                <h4 class="mb-0">${tongDonDat != null ? tongDonDat : '0'}</h4>
              </div>
            </div>
            <hr class="dark horizontal my-0">
            <div class="card-footer p-3">
              <p class="mb-0"><span class="text-success text-sm font-weight-bolder">Chi tiết: </span>Gồm lịch sử tất cả các đơn</p>
            </div>
          </div>
        </div>

        <!-- Card 3: Số lượng Khách hàng (Chờ lấy dữ liệu động) -->
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-success shadow-success text-center border-radius-xl mt-n4 position-absolute">
                <i class="material-icons opacity-10">person</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">Khách hàng ĐK</p>
                <h4 class="mb-0">${tongKhachHang != null ? tongKhachHang : '0'}</h4>
              </div>
            </div>
            <hr class="dark horizontal my-0">
            <div class="card-footer p-3">
              <p class="mb-0"><span class="text-danger text-sm font-weight-bolder">Lưu ý: </span>Khách hàng có tài khoản</p>
            </div>
          </div>
        </div>

        <!-- Card 4: Số lượng Nhân sự (Chờ lấy dữ liệu động) -->
        <div class="col-xl-3 col-sm-6">
          <div class="card">
            <div class="card-header p-3 pt-2">
              <div class="icon icon-lg icon-shape bg-gradient-info shadow-info text-center border-radius-xl mt-n4 position-absolute">
                <i class="material-icons opacity-10">badge</i>
              </div>
              <div class="text-end pt-1">
                <p class="text-sm mb-0 text-capitalize">Nhân viên / Admin</p>
                <h4 class="mb-0">${tongNhanSu != null ? tongNhanSu : '0'}</h4>
              </div>
            </div>
            <hr class="dark horizontal my-0">
            <div class="card-footer p-3">
              <p class="mb-0"><span class="text-success text-sm font-weight-bolder">Quản lý: </span>Nhân sự hệ thống</p>
            </div>
          </div>
        </div>
      </div>
      
      <!-- Giữ lại biểu đồ rỗng làm Placeholder -->
      <div class="row mt-4">
        <div class="col-lg-12 col-md-12 mt-4 mb-4">
          <div class="card z-index-2  ">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
              <div class="bg-gradient-success shadow-success border-radius-lg py-3 pe-1">
                <div class="chart">
                  <canvas id="chart-line" class="chart-canvas" height="300"></canvas>
                </div>
              </div>
            </div>
            <div class="card-body">
              <h6 class="mb-0 "> Biểu đồ Doanh Thu Khái Quát </h6>
              <p class="text-sm "> (Dữ liệu sẽ được cập nhật trong các phiên bản sau) </p>
            </div>
          </div>
        </div>
      </div>

    </div>
  </main>
  
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/plugins/perfect-scrollbar.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/plugins/smooth-scrollbar.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/material-dashboard.min.js?v=3.0.0"></script>
</body>
</html>