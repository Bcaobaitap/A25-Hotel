<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<body>
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
             Feane
           </span>
         </a>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
           <span class=""> </span>
         </button>
         <div class="collapse navbar-collapse" id="navbarSupportedContent">
           <ul class="navbar-nav  mx-auto ">
             <li class="nav-item active">
               <a class="nav-link" href="${pageContext.request.contextPath}/home">Home <span class="sr-only">(current)</span></a>
             </li>
             <li class="nav-item">
               <a class="nav-link" href="${pageContext.request.contextPath}/home#room-section">Danh sách phòng</a>
             </li>
             <li class="nav-item">
               <a class="nav-link" href="#">About</a>
             </li>
             <li class="nav-item">
               <a class="nav-link" href="#">Đặt phòng ngay</a>
             </li>
           </ul>
           <div class="user_option" style="display: flex; align-items: center;">
    
    			<a class="cart_link" href="${pageContext.request.contextPath}/my-orders" title="Lịch sử đặt phòng" style="margin-right: 15px;">
        			<svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 456.029 456.029" style="enable-background:new 0 0 456.029 456.029;" xml:space="preserve">
            			<g><g><path d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248 c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z"/></g></g>
            			<g><g><path d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48 C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064 c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4 C457.728,97.71,450.56,86.958,439.296,84.91z"/></g></g>
            			<g><g><path d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296 c1.024,28.16,24.064,50.688,52.224,50.688h1.024C193.536,443.31,216.576,418.734,215.04,389.55z"/></g></g>
        			</svg>
    			</a>

    			<c:choose>
        			<c:when test="${empty userSession}">
            			<a href="${pageContext.request.contextPath}/login" class="order_online">
                			Đăng nhập
            			</a>
            			<a href="${pageContext.request.contextPath}/register" class="order_online" style="margin-left: 10px; background-color: #222831; color: white;">
                			Đăng ký
            			</a>
        			</c:when>
        			<c:otherwise>
            			<span style="color: white; margin-right: 15px; font-weight: 500;">
                			<i class="fa fa-user-circle-o" aria-hidden="true" style="margin-right: 5px; color: #ffbe33;"></i> 
                			${userSession.tenDN}
            			</span>
            			<a href="${pageContext.request.contextPath}/logout" class="order_online" style="background-color: #222831; padding: 8px 20px;">
                			Đăng xuất
            			</a>
        			</c:otherwise>
    			</c:choose>

			</div>
         </div>
       </nav>
     </div>
   </header>
   <!-- end header section -->
   <!-- slider section -->
   <section class="slider_section ">
     <div id="customCarousel1" class="carousel slide" data-ride="carousel">
       <div class="carousel-inner">
         <div class="carousel-item active">
           <div class="container ">
             <div class="row">
               <div class="col-md-7 col-lg-6 ">
                 <div class="detail-box">
                   <h1>
                     Fast Food Restaurant
                   </h1>
                   <p>
                     Doloremque, itaque aperiam facilis rerum, commodi, temporibus sapiente ad mollitia laborum quam quisquam esse error unde. Tempora ex doloremque, labore, sunt repellat dolore, iste magni quos nihil ducimus libero ipsam.
                   </p>
                   <div class="btn-box">
                     <a href="" class="btn1">
                       Order Now
                     </a>
                   </div>
                 </div>
               </div>
             </div>
           </div>
         </div>
         <div class="carousel-item ">
           <div class="container ">
             <div class="row">
               <div class="col-md-7 col-lg-6 ">
                 <div class="detail-box">
                   <h1>
                     Fast Food Restaurant
                   </h1>
                   <p>
                     Doloremque, itaque aperiam facilis rerum, commodi, temporibus sapiente ad mollitia laborum quam quisquam esse error unde. Tempora ex doloremque, labore, sunt repellat dolore, iste magni quos nihil ducimus libero ipsam.
                   </p>
                   <div class="btn-box">
                     <a href="" class="btn1">
                       Order Now
                     </a>
                   </div>
                 </div>
               </div>
             </div>
           </div>
         </div>
         <div class="carousel-item">
           <div class="container ">
             <div class="row">
               <div class="col-md-7 col-lg-6 ">
                 <div class="detail-box">
                   <h1>
                     Fast Food Restaurant
                   </h1>
                   <p>
                     Doloremque, itaque aperiam facilis rerum, commodi, temporibus sapiente ad mollitia laborum quam quisquam esse error unde. Tempora ex doloremque, labore, sunt repellat dolore, iste magni quos nihil ducimus libero ipsam.
                   </p>
                   <div class="btn-box">
                     <a href="" class="btn1">
                       Order Now
                     </a>
                   </div>
                 </div>
               </div>
             </div>
           </div>
         </div>
       </div>
       <div class="container">
         <ol class="carousel-indicators">
           <li data-target="#customCarousel1" data-slide-to="0" class="active"></li>
           <li data-target="#customCarousel1" data-slide-to="1"></li>
           <li data-target="#customCarousel1" data-slide-to="2"></li>
         </ol>
       </div>
     </div>
   </section>
   <!-- end slider section -->
 </div>
 <!-- offer section -->
 <section class="offer_section layout_padding-bottom">
   <div class="offer_container">
     <div class="container ">
       <div class="row">
         <div class="col-md-6  ">
           <div class="box ">
             <div class="img-box">
               <img src="${pageContext.request.contextPath}/assets/images/homepage_img/o1.jpg" alt="">
             </div>
             <div class="detail-box">
               <h5>
                 Tasty Thursdays
               </h5>
               <h6>
                 <span>20%</span> Off
               </h6>
               <a href="">
                 Order Now <svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 456.029 456.029" style="enable-background:new 0 0 456.029 456.029;" xml:space="preserve">
                   <g>
                     <g>
                       <path d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248
                    c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48
                    C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064
                    c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4
                    C457.728,97.71,450.56,86.958,439.296,84.91z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296
                    c1.024,28.16,24.064,50.688,52.224,50.688h1.024C193.536,443.31,216.576,418.734,215.04,389.55z" />
                     </g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                 </svg>
               </a>
             </div>
           </div>
         </div>
         <div class="col-md-6  ">
           <div class="box ">
             <div class="img-box">
               <img src="${pageContext.request.contextPath}/assets/images/homepage_img/o2.jpg" alt="">
             </div>
             <div class="detail-box">
               <h5>
                 Pizza Days
               </h5>
               <h6>
                 <span>15%</span> Off
               </h6>
               <a href="">
                 Order Now <svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 456.029 456.029" style="enable-background:new 0 0 456.029 456.029;" xml:space="preserve">
                   <g>
                     <g>
                       <path d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248
                    c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48
                    C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064
                    c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4
                    C457.728,97.71,450.56,86.958,439.296,84.91z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296
                    c1.024,28.16,24.064,50.688,52.224,50.688h1.024C193.536,443.31,216.576,418.734,215.04,389.55z" />
                     </g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                   <g>
                   </g>
                 </svg>
               </a>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </section>
 <!-- end offer section -->
 <!-- food section -->
 <section id="room-section" class="food_section layout_padding-bottom">
   <div class="container">
     <div class="heading_container heading_center">
       <h2>
         Our Menu
       </h2>
     </div>
     <ul class="filters_menu">
       <li class="active" data-filter="*">All</li>
       <li data-filter=".burger">Phòng đơn</li>
       <li data-filter=".pizza">Phòng đôi</li>
       <li data-filter=".pasta">Phòng cận cao cấp</li>
       <li data-filter=".fries">Phòng cao cấp </li>
     </ul>
     <div class="filters-content">
       <div class="row grid">
    <c:forEach items="${danhSachPhong}" var="phong" end="3">
        <div class="col-sm-6 col-lg-4 all">
            <div class="box">
                <div>
                    <div class="img-box">
                        <img src="${pageContext.request.contextPath}/assets/images/homepage_img/${phong.anhPhong}" alt="${phong.tenPhong}">
                    </div>
                    <div class="detail-box">
                        <h5>
                            ${phong.tenPhong}
                        </h5>
                        <p>
                            Loại: <span style="font-weight: bold; color: #ffbe33;">${phong.loaiPhong}</span><br/>
                            ${phong.moTa}
                        </p>
                        <div class="options">
                            <h6>
                                ${phong.gia} VNĐ
                            </h6>
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
     <div class="btn-box">
       <a href="${pageContext.request.contextPath}/rooms">
         View More
       </a>
     </div>
   </div>
 </section>
 <!-- end food section -->
 <!-- about section -->
 <section class="about_section layout_padding">
   <div class="container  ">
     <div class="row">
       <div class="col-md-6 ">
         <div class="img-box">
           <img src="${pageContext.request.contextPath}/assets/images/homepage_img/about-img.png" alt="">
         </div>
       </div>
       <div class="col-md-6">
         <div class="detail-box">
           <div class="heading_container">
             <h2>
               We Are Feane
             </h2>
           </div>
           <p>
             There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration
             in some form, by injected humour, or randomised words which don't look even slightly believable. If you
             are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in
             the middle of text. All
           </p>
           <a href="">
             Read More
           </a>
         </div>
       </div>
     </div>
   </div>
 </section>
 <!-- end about section -->
 <!-- book section -->
 <section class="book_section layout_padding">
   <div class="container">
     <div class="heading_container">
       <h2>
         Book A Room
       </h2>
     </div>
     <div class="row">
       <div class="col-md-6">
         <div class="form_container">
           <form action="">
             <div>
               <input type="text" class="form-control" placeholder="Your Name" />
             </div>
             <div>
               <input type="text" class="form-control" placeholder="Phone Number" />
             </div>
             <div>
               <input type="email" class="form-control" placeholder="Your Email" />
             </div>
             <div>
               <select class="form-control nice-select wide">
                 <option value="" disabled selected>
                   How many persons?
                 </option>
                 <option value="">
                   2
                 </option>
                 <option value="">
                   3
                 </option>
                 <option value="">
                   4
                 </option>
                 <option value="">
                   5
                 </option>
               </select>
             </div>
             <div>
               <input type="date" class="form-control">
             </div>
             <div class="btn_box">
               <button>
                 Book Now
               </button>
             </div>
           </form>
         </div>
       </div>
       <div class="col-md-6">
         <div class="map_container ">
           <div id="googleMap"></div>
         </div>
       </div>
     </div>
   </div>
 </section>
 <!-- end book section -->
 <!-- client section -->
 <section class="client_section layout_padding-bottom">
   <div class="container">
     <div class="heading_container heading_center psudo_white_primary mb_45">
       <h2>
         What Says Our Customers
       </h2>
     </div>
     <div class="carousel-wrap row ">
       <div class="owl-carousel client_owl-carousel">
         <div class="item">
           <div class="box">
             <div class="detail-box">
               <p>
                 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam
               </p>
               <h6>
                 Moana Michell
               </h6>
               <p>
                 magna aliqua
               </p>
             </div>
             <div class="img-box">
               <img src="${pageContext.request.contextPath}/assets/images/homepage_img/client1.jpg" alt="" class="box-img">
             </div>
           </div>
         </div>
         <div class="item">
           <div class="box">
             <div class="detail-box">
               <p>
                 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam
               </p>
               <h6>
                 Mike Hamell
               </h6>
               <p>
                 magna aliqua
               </p>
             </div>
             <div class="img-box">
               <img src="${pageContext.request.contextPath}/assets/images/homepage_img/client2.jpg" alt="" class="box-img">
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </section>
 <!-- end client section -->
 
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