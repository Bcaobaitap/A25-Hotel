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
            <span>Quản Lý Khách Sạn</span>
          </a>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/rooms">Danh sách phòng</a>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </header>
  </div>

  <section class="food_section layout_padding">
    <div class="container">
      <div class="heading_container heading_center">
        <h2>Tất Cả Các Phòng</h2>
      </div>

      <div class="filters-content">
        <div class="row grid">
          <c:forEach items="${danhSachPhong}" var="phong">
            <div class="col-sm-6 col-lg-4 all">
              <div class="box">
                <div>
                  <div class="img-box">
                    <img src="${pageContext.request.contextPath}/assets/images/homepage_img/${phong.anhPhong}" alt="${phong.tenPhong}">
                  </div>
                  <div class="detail-box">
                    <h5>${phong.tenPhong}</h5>
                    <p>
                      Loại: <span style="font-weight: bold; color: #ffbe33;">${phong.loaiPhong}</span><br/>
                      ${phong.moTa}
                    </p>
                    <div class="options">
                      <h6>${phong.gia} VNĐ</h6>
                      <a href="${pageContext.request.contextPath}/room-detail?id=${phong.maPhong}" title="Xem chi tiết & Đặt phòng">
                        <i class="fa fa-bed" aria-hidden="true" style="color: white; font-size: 18px;"></i>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </section>

  <jsp:include page="/WEB-INF/views/common/footer.jsp" />

  <script src="${pageContext.request.contextPath}/assets/js/homepage_js/jquery-3.4.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/homepage_js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/homepage_js/custom.js"></script>
</body>
</html>