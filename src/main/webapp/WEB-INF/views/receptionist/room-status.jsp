<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Sơ Đồ Phòng - Lễ Tân</title>

<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
<link
	href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-icons.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/dashboard_css/nucleo-svg.css"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/42d5adcbca.js"
	crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Round"
	rel="stylesheet">
<link id="pagestyle"
	href="${pageContext.request.contextPath}/assets/css/dashboard_css/material-dashboard.css?v=3.0.0"
	rel="stylesheet" />
<style>
/* Class tạo hiệu ứng hover dành riêng cho phòng trống */
.room-hover-effect {
	transition: transform 0.2s ease, border 0.2s ease;
	border: 1px solid transparent;
}

.room-hover-effect:hover {
	transform: scale(1.03);
	border: 1px solid #e91e63 !important;
}
</style>
</head>
</head>

<body class="g-sidenav-show bg-gray-200">

	<!--  Sidebar -->
	<jsp:include page="/WEB-INF/views/common/dashboard_sidebar.jsp" />

	<main
		class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
		<!--  Header -->
		<jsp:include page="/WEB-INF/views/common/dashboard_header.jsp" />

		<div class="row mb-4 align-items-center">
			<div class="col-md-6">
				<h5 class="mb-0">Sơ Đồ Khách Sạn</h5>
				<p class="text-sm mb-0">Theo dõi trạng thái phòng theo thời gian
					thực</p>
			</div>

			<!-- BỘ CHỌN NGÀY -->
			<div class="col-md-6 d-flex justify-content-md-end mt-3 mt-md-0">
				<form
					action="${pageContext.request.contextPath}/receptionist/room-status"
					method="GET" class="d-flex align-items-center m-0">
					<label class="text-sm font-weight-bold mb-0 me-2 text-dark">Kiểm
						tra sơ đồ ngày:</label>
					<!-- onchange="this.form.submit()" giúp tự động tải lại trang khi đổi ngày -->
					<input type="date" name="checkDate" value="${currentDate}"
						class="form-control border px-2 py-1 bg-white"
						style="width: 180px; height: 35px;" onchange="this.form.submit()">
				</form>
			</div>
		</div>

		<!-- Chú thích màu sắc -->
		<div class="row mb-4">
			<div class="col-12 d-flex align-items-center">
				<span class="badge bg-gradient-success me-2">TRỐNG</span> Sẵn sàng
				đón khách <span class="badge bg-gradient-danger mx-2">CÓ
					KHÁCH</span> Đang được sử dụng <span
					class="badge bg-gradient-warning mx-2">BẢO TRÌ</span> Không thể đặt
			</div>
		</div>
        <!-- chỉnh lại icon- duy anh -->
		<div class="row">
			<c:forEach items="${listPhong}" var="phong">
				<c:set var="bgClass" value="bg-white" />
				<c:set var="textClass" value="text-dark" />
				<c:set var="iconClass" value="text-success" />

				<c:if test="${phong.trangThaiPhong == 'CÓ KHÁCH'}">
					<c:set var="bgClass" value="bg-gradient-danger" />
					<c:set var="textClass" value="text-white" />
					<c:set var="iconClass" value="text-danger" />
				</c:if>
				<c:if test="${phong.trangThaiPhong == 'BẢO TRÌ'}">
					<c:set var="bgClass" value="bg-gradient-warning" />
					<c:set var="textClass" value="text-white" />
					<c:set var="iconClass" value="text-warning" />
				</c:if>

				<div class="col-xl-3 col-sm-6 mb-4">
					<c:choose>
						<c:when test="${phong.trangThaiPhong == 'TRỐNG'}">
							<a
								href="${pageContext.request.contextPath}/receptionist/book-room?maPhong=${phong.maPhong}"
								style="text-decoration: none; display: block;">
						</c:when>
						<c:otherwise>
							<div style="cursor: not-allowed; opacity: 0.9;">
						</c:otherwise>
					</c:choose>

					<div
						class="card ${bgClass} ${phong.trangThaiPhong == 'TRỐNG' ? 'room-hover-effect' : ''}">
						<div class="card-body p-3">
							<div class="row">
								<div class="col-8">
									<div class="numbers">
										<p
											class="text-sm mb-0 text-capitalize font-weight-bold ${textClass}">
											Tầng ${phong.soTang} - ${phong.loaiPhong}</p>
										<h5 class="font-weight-bolder mb-0 ${textClass}">
											${phong.tenPhong}</h5>
										<p class="text-xs mb-0 font-weight-bold mt-2 ${textClass}">
											<fmt:formatNumber value="${phong.gia}" type="number" />
											đ/đêm
										</p>
									</div>
								</div>
								<div class="col-4 text-end">
								<!-- chỉnh lại icon - duyanh -->
									<div
										class="icon icon-shape bg-white shadow text-center border-radius-md d-flex align-items-center justify-content-center">
										<i class="material-icons opacity-10 ${iconClass} text-lg"
											aria-hidden="true"> <c:choose>
												<c:when test="${phong.trangThaiPhong == 'TRỐNG'}">meeting_room</c:when>
												<c:when test="${phong.trangThaiPhong == 'CÓ KHÁCH'}">person_pin</c:when>
												<c:otherwise>construction</c:otherwise>
											</c:choose>
										</i>
									</div>
								</div>
							</div>
						</div>
					</div>

					<c:choose>
						<c:when test="${phong.trangThaiPhong == 'TRỐNG'}">
							</a>
						</c:when>
						<c:otherwise>
				</div>
				</c:otherwise>
				</c:choose>
		</div>
		</c:forEach>
		</div>
	</main>

	<script
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/core/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/plugins/perfect-scrollbar.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/plugins/smooth-scrollbar.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/material-dashboard.min.js?v=3.0.0"></script>
</body>
</html>