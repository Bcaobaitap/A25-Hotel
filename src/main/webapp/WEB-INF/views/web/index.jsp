<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<body>
	<div class="hero_area">
		<div class="bg-box">
			<img
				src="${pageContext.request.contextPath}/assets/images/homepage_img/hotel-banner.jpg"
				alt="">
		</div>
		<!-- header section strats -->
		<header class="header_section">
			<div class="container">
				<nav class="navbar navbar-expand-lg custom_nav-container ">
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/home"> <span>
							25Hotel </span>
					</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class=""> </span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav  mx-auto ">
							<li class="nav-item active"><a class="nav-link"
								href="${pageContext.request.contextPath}/home">Trang chủ <span
									class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/home#room-section">Phòng</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/home#contact-section">Liên
									hệ</a></li>
						</ul>
						<div class="user_option"
							style="display: flex; align-items: center;">

							<a class="cart_link"
								href="${pageContext.request.contextPath}/my-orders"
								title="Lịch sử đặt phòng" style="margin-right: 15px;"> <svg
									version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
									viewBox="0 0 456.029 456.029"
									style="enable-background: new 0 0 456.029 456.029;"
									xml:space="preserve">
            			<g>
									<g>
									<path
										d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248 c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z" /></g></g>
            			<g>
									<g>
									<path
										d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48 C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064 c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4 C457.728,97.71,450.56,86.958,439.296,84.91z" /></g></g>
            			<g>
									<g>
									<path
										d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296 c1.024,28.16,24.064,50.688,52.224,50.688h1.024C193.536,443.31,216.576,418.734,215.04,389.55z" /></g></g>
        			</svg>
							</a>

							<c:choose>
								<c:when test="${empty userSession}">
									<a href="${pageContext.request.contextPath}/login"
										class="order_online"> Đăng nhập </a>
									<a href="${pageContext.request.contextPath}/register"
										class="order_online"
										style="margin-left: 10px; background-color: #222831; color: white;">
										Đăng ký </a>
								</c:when>
								<c:otherwise>
									<span
										style="color: white; margin-right: 15px; font-weight: 500;">
										<i class="fa fa-user-circle-o" aria-hidden="true"
										style="margin-right: 5px; color: #ffbe33;"></i>
										${userSession.tenDN}
									</span>
									<a href="${pageContext.request.contextPath}/logout"
										class="order_online"
										style="background-color: #222831; padding: 8px 20px;">
										Đăng xuất </a>
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
										<h1>Khách sạn 5 sao hàng đầu Việt Nam</h1>
										<p>Chào mừng đến với 25Hotel — nơi mỗi kỳ nghỉ trở thành
											ký ức không thể quên. Đặt phòng ngay để hưởng ưu đãi đặc biệt
											và dịch vụ chăm sóc tận tâm 24/7.</p>

										<div class="btn-box">
											<!-- Điều hướng nút đặt phòng sang đăng nhập -->
											<!-- nếu đã đăng nhập, chuyển sang trang phòng -->
											<a href="javascript:void(0);" onclick="checkLoginAndBook();"
												class="btn1"> Đặt phòng ngay </a>
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
										<h1>Khách sạn 5 sao hàng đầu Việt Nam</h1>
										<p>Chào mừng đến với 25Hotel — nơi mỗi kỳ nghỉ trở thành
											ký ức không thể quên. Đặt phòng ngay để hưởng ưu đãi đặc biệt
											và dịch vụ chăm sóc tận tâm 24/7.</p>
										<div class="btn-box">
											<!-- Điều hướng nút đặt phòng sang đăng nhập -->
											<!-- nếu đã đăng nhập, chuyển sang trang phòng -->
											<a href="javascript:void(0);" onclick="checkLoginAndBook();"
												class="btn1"> Đặt phòng ngay </a>
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
										<h1>Khách sạn 5 sao hàng đầu Việt Nam</h1>
										<p>Chào mừng đến với 25Hotel — nơi mỗi kỳ nghỉ trở thành
											ký ức không thể quên. Đặt phòng ngay để hưởng ưu đãi đặc biệt
											và dịch vụ chăm sóc tận tâm 24/7.</p>
										<div class="btn-box">
											<!-- Điều hướng nút đặt phòng sang đăng nhập -->
											<!-- nếu đã đăng nhập, chuyển sang trang phòng -->
											<a href="javascript:void(0);" onclick="checkLoginAndBook();"
												class="btn1"> Đặt phòng ngay </a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<ol class="carousel-indicators">
						<li data-target="#customCarousel1" data-slide-to="0"
							class="active"></li>
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
								<img
									src="${pageContext.request.contextPath}/assets/images/homepage_img/of1.jpg"
									alt="">
							</div>
							<div class="detail-box">
								<h5>Ưu đãi Cuối Tuần</h5>
								<h6>
									<span>20%</span> Off
								</h6>
								<a href=""> Đặt ngay <svg version="1.1" id="Capa_1"
										xmlns="http://www.w3.org/2000/svg"
										xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
										viewBox="0 0 456.029 456.029"
										style="enable-background: new 0 0 456.029 456.029;"
										xml:space="preserve">
                   <g>
                     <g>
                       <path
											d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248
                    c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path
											d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48
                    C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064
                    c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4
                    C457.728,97.71,450.56,86.958,439.296,84.91z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path
											d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296
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
								<img
									src="${pageContext.request.contextPath}/assets/images/homepage_img/of2.jpg"
									alt="">
							</div>
							<div class="detail-box">
								<h5>Suite Tháng Hè</h5>
								<h6>
									<span>15%</span> Off
								</h6>
								<a href=""> Đặt ngay<svg version="1.1" id="Capa_1"
										xmlns="http://www.w3.org/2000/svg"
										xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
										viewBox="0 0 456.029 456.029"
										style="enable-background: new 0 0 456.029 456.029;"
										xml:space="preserve">
                   <g>
                     <g>
                       <path
											d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248
                    c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path
											d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48
                    C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064
                    c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4
                    C457.728,97.71,450.56,86.958,439.296,84.91z" />
                     </g>
                   </g>
                   <g>
                     <g>
                       <path
											d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296
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

	<!-- room section -->
	<section id="room-section" class="food_section layout_padding-bottom">
		<div class="container">
			<div class="heading_container heading_center">
				<div class="heading_container heading_center">
					<h2>Our Rooms</h2>
					<!-- Thanh tìm kiếm theo giá -->
					<div class="price_filter_input"
						style="margin: 20px 0; display: flex; justify-content: center; gap: 15px; align-items: center;">
						<div class="input-group" style="width: 200px;">
							<input type="number" id="minPrice" class="form-control"
								placeholder="VD: 500"
								style="border-radius: 20px; border: 1px solid #ffbe33;">
						</div>
						<span style="font-weight: bold; color: #777;">—</span>
						<div class="input-group" style="width: 200px;">
							<input type="number" id="maxPrice" class="form-control"
								placeholder="VD: 1500"
								style="border-radius: 20px; border: 1px solid #ffbe33;">
						</div>
						<button id="btnFilterPrice" class="btn"
							style="background-color: #ffbe33; color: white; border-radius: 20px; padding: 5px 20px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round" style="margin-right:5px; vertical-align:middle;"><circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/></svg> Lọc
						</button>
					</div>
				</div>
			</div>
			<!-- sửa lại data-filter -->
			<ul class="filters_menu">
				<li class="active" data-filter="*">All</li>
				<li data-filter=".phong-don">Phòng đơn</li>
				<li data-filter=".phong-doi">Phòng đôi</li>
				<li data-filter=".phong-can-cao-cap">Phòng cận cao cấp</li>
				<li data-filter=".phong-cao-cap">Phòng cao cấp</li>
			</ul>
			<div class="filters-content">
				<div class="row grid">
					<!-- sửa lại vòng lại để tìm kiếm theo loại phòng -->
					<c:forEach items="${danhSachPhong}" var="phong">
						<%-- Bước này giúp chuyển "Phòng đơn" -> "phong-don" để khớp với filter --%>
						<c:set var="loaiPhongClass"
							value="${phong.loaiPhong.toLowerCase().replace(' ', '-').replace('đ', 'd').replaceAll('[àáạảãâầấậẩẫăằắặẳẵ]', 'a').replaceAll('[èéẹẻẽêềếệểễ]', 'e').replaceAll('[ìíịỉĩ]', 'i').replaceAll('[òóọỏõôồốộổỗơờớợởỡ]', 'o').replaceAll('[ùúụủũưừứựửữ]', 'u').replaceAll('[ỳýỵỷỹ]', 'y')}" />

						<div class="col-sm-6 col-lg-4 all ${loaiPhongClass}" data-price="${phong.gia}">
							<div class="box">
								<div>
									<div class="img-box">
										<img
											src="${pageContext.request.contextPath}/assets/images/homepage_img/${phong.anhPhong}"
											alt="">
									</div>
									<div class="detail-box">
										<h5>${phong.tenPhong}</h5>
										<p>
											Loại: <span style="font-weight: bold; color: #ffbe33;">${phong.loaiPhong}</span><br />
											${phong.moTa}
										</p>
										<div class="options">
											<h6>${phong.gia}VNĐ</h6>
											<a
												href="${pageContext.request.contextPath}/room-detail?id=${phong.maPhong}">
												<div class="icon-container"
													style="background-color: #ffbe33; padding: 10px; border-radius: 50%; display: flex; align-items: center; justify-content: center; width: 40px; height: 40px;">
													<i class="fa fa-bed" aria-hidden="true"
														style="color: white; font-size: 18px;"></i>
												</div>
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
				<a href="${pageContext.request.contextPath}/rooms"> Hiển thị
					thêm </a>
			</div>
		</div>
	</section>
	<!-- end food section -->

	<!--  xoá abt sction -->
	<section class="book_section layout_padding">

		<div class="container">

			<div class="heading_container">

				<h2>Location</h2>

			</div>

			<div class="row">

				<div class="col-md-12">

					<div class="map_container">

						<div id="googleMap" style="width: 100%; height: 400px;"></div>

					</div>

				</div>

			</div>

		</div>

	</section>

	<!-- end map section -->

	<section class="client_section layout_padding-bottom">
		<div class="container">
			<div
				class="heading_container heading_center psudo_white_primary mb_45">
				<h2>Khách Hàng Nói Gì Về Chúng Tôi</h2>
			</div>
			<div class="carousel-wrap row ">
				<div class="owl-carousel client_owl-carousel">
					<div class="item">
						<div class="box">
							<div class="detail-box">
								<p>Khách sạn sạch sẽ, phòng rộng rãi và đầy đủ tiện nghi.
									Nhân viên phục vụ rất nhiệt tình và thân thiện. Tôi sẽ quay lại
									vào lần du lịch tiếp theo.</p>
								<h6>Moana Michell</h6>
								<p>Khách hàng thân thiết</p>
							</div>
							<div class="img-box">
								<img
									src="${pageContext.request.contextPath}/assets/images/homepage_img/client1.jpg"
									alt="" class="box-img">
							</div>
						</div>
					</div>
					<div class="item">
						<div class="box">
							<div class="detail-box">
								<p>Dịch vụ chuyên nghiệp, vị trí khách sạn thuận tiện di
									chuyển. Đồ ăn ngon và không gian rất thoải mái. Tôi rất hài
									lòng với trải nghiệm tại đây.</p>
								<h6>Mike Hamell</h6>
								<p>Du khách</p>
							</div>
							<div class="img-box">
								<img
									src="${pageContext.request.contextPath}/assets/images/homepage_img/client2.jpg"
									alt="" class="box-img">
							</div>
						</div>
					</div>
					<div class="item">
						<div class="box">
							<div class="detail-box">
								<p>Phòng nghỉ hiện đại, giá cả hợp lý và nhân viên hỗ trợ
									24/7. Quy trình đặt phòng nhanh chóng và tiện lợi. Rất đáng để
									trải nghiệm.</p>
								<h6>Donald Trump</h6>
								<p>Du khách</p>
							</div>
							<div class="img-box">
								<img
									src="${pageContext.request.contextPath}/assets/images/homepage_img/Donald_Trump.jpg"
									alt="" class="box-img">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- end client section -->
	<div id="contact-section">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	<!-- jQery -->
	<script
		src="${pageContext.request.contextPath}/assets/js/homepage_js/jquery-3.4.1.min.js"></script>
	<!-- popper js -->
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<!-- bootstrap js -->
	<script
		src="${pageContext.request.contextPath}/assets/js/homepage_js/bootstrap.js"></script>
	<!-- owl slider -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
	<!-- isotope js -->
	<script
		src="https://unpkg.com/isotope-layout@3.0.4/dist/isotope.pkgd.min.js"></script>
	<!-- nice select -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
	<!-- custom js -->
	<script
		src="${pageContext.request.contextPath}/assets/js/homepage_js/custom.js"></script>
	<!-- Google Map -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh39n5U-4IoWpsVGUHWdqB6puEkhRLdmI&callback=myMap"></script>
	<!-- End Google Map -->

	<!-- Hàm kiểm tra đã đăng nhập hay chưa -->
	>
	<script>
		function checkLoginAndBook() {
			<c:choose>
			<c:when test="${empty userSession}">
			// Chưa đăng nhập → redirect về login
			window.location.href = "${pageContext.request.contextPath}/login";
			</c:when>
			<c:otherwise>
			// Đã đăng nhập → redirect về rooms
			window.location.href = "${pageContext.request.contextPath}/rooms";
			</c:otherwise>
			</c:choose>
		}
	</script>
<!-- 	hàm tìm kiếm theo giá  -->
	<script>
$(window).on('load', function () {
    var $grid = $(".grid").isotope({
        itemSelector: ".all",
        layoutMode: "fitRows",
    });

    function performFilter() {
        var minInput = $("#minPrice").val().trim();
        var maxInput = $("#maxPrice").val().trim();
        var min = minInput !== "" ? parseFloat(minInput) : 0;
        var max = maxInput !== "" ? parseFloat(maxInput) : Infinity;
        var typeFilter = $(".filters_menu li.active").attr("data-filter");

        $grid.isotope({
            filter: function () {
                // data-price lấy trực tiếp từ ${phong.gia} - là số nguyên trong DB
                var priceStr = $(this).attr("data-price") || "0";
                var price = parseFloat(priceStr);

                var isPriceMatch = (price >= min && price <= max);
                var isTypeMatch = (typeFilter === "*") ? true : $(this).is(typeFilter);

                return isPriceMatch && isTypeMatch;
            }
        });
    }

    $("#btnFilterPrice").on('click', performFilter);
    $(".filters_menu li").on('click', function () {
        $(".filters_menu li").removeClass("active");
        $(this).addClass("active");
        performFilter();
    });
});
</script>

</body>
</html>