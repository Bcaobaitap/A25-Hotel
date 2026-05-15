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
         </nav>
      </div>
    </header>
  </div>

  <section class="about_section layout_padding">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <div class="img-box">
            <img src="${pageContext.request.contextPath}/assets/images/homepage_img/${room.anhPhong}" 
                 alt="${room.tenPhong}" style="border-radius: 15px; box-shadow: 0 5px 15px rgba(0,0,0,0.3);">
          </div>
        </div>
        
        <div class="col-md-6">
          <div class="detail-box">
            <div class="heading_container">
              <h2>${room.tenPhong}</h2>
            </div>
            
            <div style="margin: 20px 0; font-size: 18px; color: #f1f2f3;">
              <p><strong>Loại phòng:</strong> <span style="color: #ffbe33;">${room.loaiPhong}</span></p>
              <p><strong>Giá thuê:</strong> <span style="color: #ffbe33; font-size: 24px;">${room.gia} VNĐ</span> / đêm</p>
              <p><strong>Diện tích:</strong> ${room.dienTich} m²</p>
              <p><strong>Vị trí:</strong> Tầng ${room.soTang}</p>
              <p><strong>Tiện ích:</strong> 
                  <c:choose>
                      <c:when test="${not empty room.listTienIch}">
                          <c:forEach items="${room.listTienIch}" var="ti">
                              <span class="badge badge-info" 
                                    style="margin-right: 5px; font-size: 14px; background-color: #17a2b8; padding: 6px 10px; font-weight: normal;">
                                  <i class="fa fa-check-circle" aria-hidden="true" style="margin-right: 3px;"></i> ${ti.tenTienIch}
                              </span>
                          </c:forEach>
                      </c:when>
                      <c:otherwise>
                          <span style="color: #999; font-size: 15px; font-style: italic;">Chưa cập nhật tiện ích</span>
                      </c:otherwise>
                  </c:choose>
              </p>
            </div>
            
            <p style="line-height: 1.8; margin-bottom: 30px;">
              <strong>Mô tả chi tiết:</strong><br>
              ${room.moTa}
            </p>
            
            <div class="btn-box">
              <a href="${pageContext.request.contextPath}/booking?id=${room.maPhong}" 
                 style="background-color: #ffbe33; color: white; padding: 12px 35px; border-radius: 45px; font-weight: bold;">
                ĐẶT PHÒNG NGAY
              </a>
            </div>
          </div>
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