<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/dashboard_img/favicon.png">
  <title>Quản Lý Nhân Sự - Admin</title>
  
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <link href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-icons.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-svg.css" rel="stylesheet" />
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <link id="pagestyle" href="${pageContext.request.contextPath}/assets/css/dashboard_css/material-dashboard.css?v=3.0.0" rel="stylesheet" />
</head>

<body class="g-sidenav-show bg-gray-200">
  
  <!--Sidebar -->
  <jsp:include page="/WEB-INF/views/common/dashboard_sidebar.jsp" />
  
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/common/dashboard_header.jsp" />
    
    <div class="container-fluid py-4">
      
      <!-- Hiện thông báo -->
      <c:if test="${param.msg == 'create_success'}">
          <div class="alert alert-success text-white">Thêm nhân sự mới thành công!</div>
      </c:if>
      <c:if test="${param.msg == 'update_success'}">
          <div class="alert alert-info text-white">Cập nhật quyền nhân sự thành công!</div>
      </c:if>
      <c:if test="${param.msg == 'error'}">
          <div class="alert alert-danger text-white">Có lỗi xảy ra. Tên đăng nhập có thể đã tồn tại.</div>
      </c:if>

      <div class="row">
        <div class="col-12">
          <div class="card my-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3 d-flex justify-content-between align-items-center">
                <h6 class="text-white text-capitalize ps-3 mb-0">Danh Sách Nhân Sự Hệ Thống</h6>
                <!-- Nút bật Modal Thêm Nhân viên -->
                <button type="button" class="btn btn-sm btn-light me-3 mb-0" data-bs-toggle="modal" data-bs-target="#addStaffModal">
                  <i class="material-icons text-sm">add</i> Thêm Nhân Sự
                </button>
              </div>
            </div>
            
            <div class="card-body px-0 pb-2">
              <div class="table-responsive p-0">
                <table class="table align-items-center mb-0">
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Họ Tên</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Tên Đăng Nhập</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Quyền (Role)</th>
                      <th class="text-secondary opacity-7"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${listStaff}" var="staff">
                        <tr>
                          <td>
                            <div class="d-flex px-2 py-1">
                              <div>
                                <!-- Icon đại diện ngẫu nhiên -->
                                <div class="avatar avatar-sm me-3 bg-gradient-dark">
                                    <i class="material-icons opacity-10">person</i>
                                </div>
                              </div>
                              <div class="d-flex flex-column justify-content-center">
                                <h6 class="mb-0 text-sm">${staff.hoTen}</h6>
                                <p class="text-xs text-secondary mb-0">Mã NV: ${staff.maTK}</p>
                              </div>
                            </div>
                          </td>
                          <td>
                            <p class="text-xs font-weight-bold mb-0">${staff.tenDN}</p>
                          </td>
                          <td class="align-middle text-center text-sm">
                            <span class="badge badge-sm ${staff.loaiTaiKhoan == 'ADMIN' ? 'bg-gradient-danger' : 'bg-gradient-success'}">
                                ${staff.loaiTaiKhoan}
                            </span>
                          </td>
                          <td class="align-middle">
                            <!-- Form đổi quyền trực tiếp -->
                            <form action="${pageContext.request.contextPath}/admin/staff" method="POST" class="d-flex align-items-center">
                                <input type="hidden" name="action" value="updateRole">
                                <input type="hidden" name="maTK" value="${staff.maTK}">
                                <select name="newRole" class="form-select form-select-sm px-2 py-1 border border-secondary rounded me-2" style="width: auto;">
                                    <option value="RECEPTIONIST" ${staff.loaiTaiKhoan == 'RECEPTIONIST' ? 'selected' : ''}>Lễ Tân</option>
                                    <option value="ADMIN" ${staff.loaiTaiKhoan == 'ADMIN' ? 'selected' : ''}>Admin</option>
                                    <option value="LOCKED">Khóa TK</option>
                                </select>
                                <button type="submit" class="btn btn-sm btn-outline-info mb-0 py-1 px-2" title="Cập nhật quyền">Lưu</button>
                            </form>
                          </td>
                        </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>

  <!-- Modal: Popup Thêm Nhân Sự -->
  <div class="modal fade" id="addStaffModal" tabindex="-1" role="dialog" aria-labelledby="addStaffModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header bg-gradient-primary">
          <h5 class="modal-title text-white" id="addStaffModalLabel">Cấp Tài Khoản Nhân Sự Mới</h5>
          <button type="button" class="btn-close text-white" data-bs-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        
        <form action="${pageContext.request.contextPath}/admin/staff" method="POST">
            <div class="modal-body">
              <input type="hidden" name="action" value="create">
              
              <div class="input-group input-group-outline mb-3">
                <label class="form-label">Họ và Tên</label>
                <input type="text" name="hoTen" class="form-control" required>
              </div>
              
              <div class="input-group input-group-outline mb-3">
                <label class="form-label">Tên đăng nhập</label>
                <input type="text" name="tenDN" class="form-control" required>
              </div>
              
              <div class="input-group input-group-outline mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" name="matKhau" class="form-control" required>
              </div>
              
              <div class="input-group input-group-static mb-3">
                <label for="roleSelect" class="ms-0">Phân quyền</label>
                <select name="role" id="roleSelect" class="form-control" required>
                  <option value="RECEPTIONIST">Lễ Tân (Receptionist)</option>
                  <option value="ADMIN">Quản Trị Viên (Admin)</option>
                </select>
              </div>
            </div>
            
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Hủy</button>
              <button type="submit" class="btn bg-gradient-primary">Tạo Tài Khoản</button>
            </div>
        </form>
      </div>
    </div>
  </div>
  
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/plugins/perfect-scrollbar.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/plugins/smooth-scrollbar.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dashboard_js/material-dashboard.min.js?v=3.0.0"></script>
</body>
</html>