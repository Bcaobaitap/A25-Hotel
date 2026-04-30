<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<body class="sub_page">

  <div class="hero_area">
    <div class="bg-box">
      <img src="${pageContext.request.contextPath}/assets/images/homepage_img/hero-bg.jpg" alt="">
    </div>
    <!-- header section strats -->
    <header class="header_section">
      <div class="container">
        <nav class="navbar navbar-expand-lg custom_nav-container ">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
            <span>
              Quản lý khách sạn
            </span>
          </a>
         </nav>
      </div>
    </header>
  </div>
  
  <!-- book section -->
  <section class="book_section layout_padding">
    <div class="container">
      <div class="heading_container">
        <h2>
          Đăng ký tài khoản
        </h2>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="form_container">
          
          <c:if test="${not empty error}">
                <div style="color: #721c24; background-color: #f8d7da; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
                    ${error}
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/register" method="POST">
              <div>
                <input type="text" class="form-control" name="fullname" placeholder="Họ và tên" value="${oldFullName}" required />
              </div>
              <div>
                <input type="text" class="form-control" name="username" placeholder="Tên đăng nhập" value="${oldUsername}" required />
              </div>
              <div>
                <input type="password" class="form-control" name="password" placeholder="Mật khẩu" required />
              </div>
              <div>
                <input type="email" class="form-control" name="email" placeholder="Email liên hệ" value="${oldEmail}" required />
              </div>
              <div>
                <input type="text" class="form-control" name="phone" placeholder="Số điện thoại" value="${oldPhone}" required />
              </div>
              
              <div class="btn_box">
                <button>
                  Đăng ký ngay
                </button>
              </div>
              
              <div style="margin-top: 20px; text-align: center;">
                <span style="color: white;">Đã có tài khoản? </span>
                <a href="${pageContext.request.contextPath}/login" style="color: #ffbe33; font-weight: bold;">Đăng nhập</a>
              </div>
            </form>
          </div>
        </div>
        
          </div>
        </div>
        <div class="col-md-6">
          <div class="map_container ">
            <div id="googleMap"></div>
          </div>
        </div>
  </section>
  <!-- end book section -->

  <jsp:include page="/WEB-INF/views/common/footer.jsp" />

  <!-- jQery -->
 <script src="${pageContext.request.contextPath}/assets/js/homepage_js/jquery-3.4.1.min.js"></script>
 <!-- popper js -->
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
 <!-- bootstrap js -->
 <script src="${pageContext.request.contextPath}/assets/js/homepage_js/bootstrap.js"></script>
 <!-- owl slider -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
 <!-- isotope js -->
 <script src="https://unpkg.com/isotope-layout@3.0.4/dist/isotope.pkgd.min.js"></script>
 <!-- nice select -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
 <!-- custom js -->
 <script src="${pageContext.request.contextPath}/assets/js/homepage_js/custom.js"></script>
 <!-- Google Map -->
 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh39n5U-4IoWpsVGUHWdqB6puEkhRLdmI&callback=myMap"></script>
  <!-- End Google Map -->

</body>
</html>