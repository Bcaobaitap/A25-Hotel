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
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/assets/images/dashboard_img/favicon.png">
<title>Tổng Quan - Hệ Thống Quản Lý Khách Sạn</title>

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
</head>

<body class="g-sidenav-show bg-gray-200">

	<jsp:include page="/WEB-INF/views/common/dashboard_sidebar.jsp" />

	<main
		class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">

		<jsp:include page="/WEB-INF/views/common/dashboard_header.jsp" />

		<div class="container-fluid py-4">

			<div class="row">
				<div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
					<div class="card">
						<div class="card-header p-3 pt-2">
							<div
								class="icon icon-lg icon-shape bg-gradient-dark shadow-dark text-center border-radius-xl mt-n4 position-absolute">
								<i class="material-icons opacity-10">payments</i>
							</div>
							<div class="text-end pt-1">
								<p class="text-sm mb-0 text-capitalize">Doanh thu hôm nay</p>
								<h4 class="mb-0">
									<fmt:formatNumber value="${currentOccupancyRevenue}"
										type="number" />
								</h4>
							</div>
						</div>
						<hr class="dark horizontal my-0">
						<div class="card-footer p-3">
							<p class="mb-0">
								<span class="text-success text-sm font-weight-bolder"> </span>Doanh
								thu
							</p>
						</div>
					</div>
				</div>

				<div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
					<div class="card">
						<div class="card-header p-3 pt-2">
							<div
								class="icon icon-lg icon-shape bg-gradient-primary shadow-primary text-center border-radius-xl mt-n4 position-absolute">
								<i class="material-icons opacity-10">person</i>
							</div>
							<div class="text-end pt-1">
								<p class="text-sm mb-0 text-capitalize">Khách đang lưu trú</p>
								<h4 class="mb-0">
									<fmt:formatNumber value="${currentTotalGuests}" type="number" />
								</h4>
							</div>
						</div>
						<hr class="dark horizontal my-0">
						<div class="card-footer p-3">
							<p class="mb-0">
								<span class="text-success text-sm font-weight-bolder"> </span>khách
								check-in
							</p>
						</div>
					</div>
				</div>

				<div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
					<div class="card">
						<div class="card-header p-3 pt-2">
							<div
								class="icon icon-lg icon-shape bg-gradient-success shadow-success text-center border-radius-xl mt-n4 position-absolute">
								<i class="material-icons opacity-10">book_online</i>
							</div>
							<div class="text-end pt-1">
								<p class="text-sm mb-0 text-capitalize">Đơn chờ xác nhận</p>
								<h4 class="mb-0">
									<fmt:formatNumber value="${currentConfirmApplication}"
										type="number" />
								</h4>
							</div>
						</div>
						<hr class="dark horizontal my-0">
						<div class="card-footer p-3">
							<p class="mb-0">
								<span class="text-danger text-sm font-weight-bolder">Cần
									xử lý ngay</span>
							</p>
						</div>
					</div>
				</div>

				<div class="col-xl-3 col-sm-6">
					<div class="card">
						<div class="card-header p-3 pt-2">
							<div
								class="icon icon-lg icon-shape bg-gradient-info shadow-info text-center border-radius-xl mt-n4 position-absolute">
								<i class="material-icons opacity-10">bed</i>
							</div>
							<div class="text-end pt-1">
								<p class="text-sm mb-0 text-capitalize">Phòng trống</p>
								<h4 class="mb-0">
									<fmt:formatNumber value="${currentEmptyRoom}" type="number" />
								</h4>
							</div>
						</div>
						<hr class="dark horizontal my-0">
						<div class="card-footer p-3">
							<p class="mb-0">
								<span class="text-success text-sm font-weight-bolder">Sẵn
									sàng </span>đón khách
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row mb-4">
			<div class="col-lg-8 col-md-6 mb-md-0 mb-4">
				<div class="card">
					<div class="card-header pb-0">
						<div class="row">
							<div class="col-lg-6 col-7">
								<h6>Đơn đặt phòng mới nhất</h6>
							</div>
						</div>
					</div>
					<div class="card-body px-0 pb-2">
						<div class="table-responsive">
							<table class="table align-items-center mb-0">
								<thead>
									<tr>
										<th
											class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Khách
											hàng</th>
										<th
											class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Phòng</th>
										<th
											class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tổng
											tiền</th>
										<th
											class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Trạng
											thái</th>
									</tr>
								</thead>
								<tbody>
								<!-- hiển thị danh sách đơn lên --> 
									<c:forEach var="don" items="${recentBookings}">
										<tr>
											<td>
												<div class="d-flex px-2 py-1 ">
													<%-- <div>
														<img
															src="${pageContext.request.contextPath}/assets/images/dashboard_img/team-2.jpg"
															class="avatar avatar-sm me-3" alt="user">
													</div> --%>
													<div class="d-flex flex-column justify-content-center">
														<h6 class="mb-0 text-sm">${don.tenNguoiDat}</h6>
													</div>
												</div>
											</td>
											
											<td>
												<p class="text-xs font-weight-bold mb-0">Phòng
													${don.maPhong}</p>
											</td>
											<td class="align-middle text-center text-sm"><span
												class="text-xs font-weight-bold"> <fmt:formatNumber
														value="${don.tongTien}" type="number" /> đ
											</span></td>
											<td class="align-middle text-center"><span
												class="badge badge-sm ${don.trangThaiDon == 'CHỜ XÁC NHẬN' ? 'bg-gradient-warning' : 'bg-gradient-success'}">
													${don.trangThaiDon} </span></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-4 col-md-6">
				<div class="card h-100">
					<div class="card-header pb-0">
						<h6>Hoạt động gần đây</h6>
					</div>
					<div class="card-body p-3">
						<div class="timeline timeline-one-side">

							<div class="timeline-block mb-3">
								<span class="timeline-step"> <i
									class="material-icons text-success text-gradient">login</i>
								</span>
								<div class="timeline-content">
									<h6 class="text-dark text-sm font-weight-bold mb-0">Khách
										Nguyễn Văn A nhận phòng 101</h6>
									<p class="text-secondary font-weight-bold text-xs mt-1 mb-0">Hôm
										nay 14:20</p>
								</div>
							</div>

							<div class="timeline-block mb-3">
								<span class="timeline-step"> <i
									class="material-icons text-info text-gradient">book_online</i>
								</span>
								<div class="timeline-content">
									<h6 class="text-dark text-sm font-weight-bold mb-0">Đơn
										đặt mới #1024 từ Web</h6>
									<p class="text-secondary font-weight-bold text-xs mt-1 mb-0">Hôm
										nay 11:00</p>
								</div>
							</div>

							<div class="timeline-block">
								<span class="timeline-step"> <i
									class="material-icons text-danger text-gradient">logout</i>
								</span>
								<div class="timeline-content">
									<h6 class="text-dark text-sm font-weight-bold mb-0">Khách
										Lê Văn C trả phòng 302</h6>
									<p class="text-secondary font-weight-bold text-xs mt-1 mb-0">Hôm
										nay 09:30</p>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
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
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/plugins/chartjs.min.js"></script>

	<script>
		var ctx = document.getElementById("chart-bars").getContext("2d");
		new Chart(ctx, {
			type : "bar",
			data : {
				labels : [ "T2", "T3", "T4", "T5", "T6", "T7", "CN" ],
				datasets : [ {
					label : "Lượt khách",
					tension : 0.4,
					borderWidth : 0,
					borderRadius : 4,
					borderSkipped : false,
					backgroundColor : "rgba(255, 255, 255, .8)",
					data : [ 12, 19, 15, 22, 30, 45, 40 ],
					maxBarThickness : 6
				}, ],
			},
			options : {
				responsive : true,
				maintainAspectRatio : false,
				plugins : {
					legend : {
						display : false
					}
				},
				scales : {
					y : {
						grid : {
							display : true,
							color : 'rgba(255, 255, 255, .2)'
						},
						ticks : {
							color : "#fff"
						}
					},
					x : {
						grid : {
							display : false
						},
						ticks : {
							color : '#f8f9fa'
						}
					}
				},
			},
		});

		var ctx2 = document.getElementById("chart-line").getContext("2d");
		new Chart(ctx2, {
			type : "line",
			data : {
				labels : [ "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4",
						"Tháng 5", "Tháng 6" ],
				datasets : [ {
					label : "Doanh thu",
					tension : 0,
					borderWidth : 4,
					pointRadius : 5,
					pointBackgroundColor : "rgba(255, 255, 255, .8)",
					borderColor : "rgba(255, 255, 255, .8)",
					backgroundColor : "transparent",
					fill : true,
					data : [ 120, 190, 150, 220, 300, 280 ],
					maxBarThickness : 6
				} ],
			},
			options : {
				responsive : true,
				maintainAspectRatio : false,
				plugins : {
					legend : {
						display : false
					}
				},
				scales : {
					y : {
						grid : {
							display : true,
							color : 'rgba(255, 255, 255, .2)'
						},
						ticks : {
							color : '#f8f9fa'
						}
					},
					x : {
						grid : {
							display : false
						},
						ticks : {
							color : '#f8f9fa'
						}
					}
				},
			},
		});
	</script>

	<script
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/material-dashboard.min.js?v=3.0.0"></script>
</body>
</html>