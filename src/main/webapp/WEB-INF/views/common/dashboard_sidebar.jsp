<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark" id="sidenav-main">
  <div class="sidenav-header">
    <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
    <c:choose>
      <c:when test="${userSession.loaiTaiKhoan == 'ADMIN'}">
          <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/admin/dashboard">
      </c:when>
      <c:when test="${userSession.loaiTaiKhoan == 'RECEPTIONIST'}">
          <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/receptionist/dashboard">
      </c:when>
      <c:otherwise>
          <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/home">
      </c:otherwise>
  </c:choose>
      <img src="${pageContext.request.contextPath}/assets/images/dashboard_img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
      <span class="ms-1 font-weight-bold text-white">Quản Lý Khách Sạn</span>
    </a>
  </div>
  <hr class="horizontal light mt-0 mb-2">
  <div class="collapse navbar-collapse w-auto max-height-vh-100" id="sidenav-collapse-main">
    <ul class="navbar-nav">
      
      <!-- Lấy URL hiện tại để gán biến pageUri -->
      <c:set var="pageUri" value="${requestScope['jakarta.servlet.forward.request_uri']}" />
      
      <li class="nav-item">
        <!-- So sánh nếu URL có chứa 'dashboard' thì gán class active màu hồng -->
        <a class="nav-link text-white ${pageUri.contains('dashboard') ? 'active bg-gradient-primary' : ''}" 
           href="${pageContext.request.contextPath}/${userSession.loaiTaiKhoan == 'ADMIN' ? 'admin' : 'receptionist'}/dashboard">
          <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
            <i class="material-icons opacity-10">dashboard</i>
          </div>
          <span class="nav-link-text ms-1">Bảng điều khiển</span>
        </a>
      </li>

      <c:if test="${userSession.loaiTaiKhoan == 'ADMIN'}">
          <li class="nav-item">
            <a class="nav-link text-white ${pageUri.contains('/admin/staff') ? 'active bg-gradient-primary' : ''}" 
               href="${pageContext.request.contextPath}/admin/staff">
              <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                <i class="material-icons opacity-10">groups</i>
              </div>
              <span class="nav-link-text ms-1">Quản lý Nhân sự</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white ${pageUri.contains('/admin/rooms') ? 'active bg-gradient-primary' : ''}" 
               href="${pageContext.request.contextPath}/admin/rooms">
              <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                <i class="material-icons opacity-10">meeting_room</i>
              </div>
              <span class="nav-link-text ms-1">Danh sách Phòng</span>
            </a>
          </li>
      </c:if>

          <li class="nav-item">
            <a class="nav-link text-white ${pageUri.contains('/receptionist/manage-booking') ? 'active bg-gradient-primary' : ''}" 
               href="${pageContext.request.contextPath}/receptionist/manage-booking">
              <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                <i class="material-icons opacity-10">receipt_long</i>
              </div>
              <span class="nav-link-text ms-1">Duyệt Đơn Đặt Phòng</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white ${pageUri.contains('/receptionist/room-status') ? 'active bg-gradient-primary' : ''}" 
               href="${pageContext.request.contextPath}/receptionist/room-status">
              <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                <i class="material-icons opacity-10">grid_view</i>
              </div>
              <span class="nav-link-text ms-1">Sơ Đồ Phòng</span>
            </a>
          </li>

      <li class="nav-item mt-3">
        <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Tài khoản</h6>
      </li>
      <li class="nav-item">
        <a class="nav-link text-white" href="${pageContext.request.contextPath}/logout">
          <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
            <i class="material-icons opacity-10">logout</i>
          </div>
          <span class="nav-link-text ms-1">Đăng xuất</span>
        </a>
      </li>
    </ul>
  </div>
</aside>