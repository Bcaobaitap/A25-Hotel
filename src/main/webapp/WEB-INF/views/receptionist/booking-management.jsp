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
<title>Quản Lý Đơn Đặt Phòng - Lễ Tân</title>

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

	<!-- Nhúng Sidebar -->
	<jsp:include page="/WEB-INF/views/common/dashboard_sidebar.jsp" />

	<main
		class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
		<!-- Nhúng Header -->
		<jsp:include page="/WEB-INF/views/common/dashboard_header.jsp" />

		<div class="container-fluid py-4">
			<!-- Thông báo kết quả thao tác -->
			<c:if test="${param.msg == 'success'}">
				<div class="alert alert-success text-white" role="alert">
					<strong>Thành công!</strong> Đã cập nhật trạng thái đơn đặt phòng.
				</div>
			</c:if>
			<c:if test="${param.msg == 'error'}">
				<div class="alert alert-danger text-white" role="alert">
					<strong>Lỗi!</strong> Đã có lỗi xảy ra, vui lòng thử lại.
				</div>
			</c:if>

			<div class="row">
				<div class="col-12">
					<div class="card my-4">
						<div
							class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
							<div
								class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
								<h6 class="text-white text-capitalize ps-3">Danh Sách Đơn
									Đặt Phòng</h6>
							</div>
						</div>
						<div class="card-body px-0 pb-2">
							<div class="table-responsive p-0">
								<table
									class="table align-items-center justify-content-center mb-0">
									<thead>
										<tr>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Mã
												Đơn</th>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Khách
												Hàng</th>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Phòng</th>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Thời
												Gian</th>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder text-center opacity-7 ps-2">Trạng
												Thái</th>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder text-center opacity-7">Hành
												Động</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty listDon}">
												<tr>
													<td colspan="6" class="text-center py-4">Chưa có đơn
														đặt phòng nào trên hệ thống.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${listDon}" var="don">
													<tr>
														<td>
															<div class="d-flex px-2">
																<div class="my-auto">
																	<h6 class="mb-0 text-sm">#${don.maDon}</h6>
																	<p class="text-xs text-secondary mb-0">
																		<fmt:formatDate value="${don.ngayTaoDon}"
																			pattern="dd/MM HH:mm" />
																	</p>
																</div>
															</div>
														</td>
														<td>
															<p class="text-sm font-weight-bold mb-0">${don.tenNguoiDat}</p>
															<p class="text-xs text-secondary mb-0">${don.thongTinLienHe}
																(${don.soNguoi} pax)</p>
														</td>
														<td><span class="text-xs font-weight-bold">ID:
																${don.maPhong}</span>
															<p class="text-xs text-secondary mb-0">
																<fmt:formatNumber value="${don.tongTien}" type="number" />
																đ
															</p></td>
														<td><span class="text-xs font-weight-bold"><fmt:formatDate
																	value="${don.ngayNhan}" pattern="dd/MM/yyyy" /></span>
															<p class="text-xs text-secondary mb-0">
																đến
																<fmt:formatDate value="${don.ngayTra}"
																	pattern="dd/MM/yyyy" />
															</p></td>
														<td class="align-middle text-center text-sm"><span
															class="badge badge-sm 
                                        ${don.trangThaiDon == 'CHỜ XÁC NHẬN' ? 'bg-gradient-warning' : 
                                          don.trangThaiDon == 'ĐÃ XÁC NHẬN' ? 'bg-gradient-success' : 
                                          don.trangThaiDon == 'ĐÃ TỪ CHỐI' ? 'bg-gradient-danger' : 
                                          don.trangThaiDon == 'ĐÃ HỦY' ? 'bg-gradient-secondary' : 'bg-gradient-info'}">
																${don.trangThaiDon} </span></td>
														<td class="align-middle text-center">
															<!-- Trạng thái CHỜ XÁC NHẬN -> Lễ tân duyệt hoặc từ chối -->
															<c:if test="${don.trangThaiDon == 'CHỜ XÁC NHẬN'}">
																<form
																	action="${pageContext.request.contextPath}/receptionist/manage-booking"
																	method="POST" style="display: inline;">
																	<input type="hidden" name="maDon" value="${don.maDon}">
																	<input type="hidden" name="maPhong"
																		value="${don.maPhong}"> <input type="hidden"
																		name="action" value="approve">
																	<button type="submit"
																		class="btn btn-sm btn-success mb-0"
																		title="Xác nhận đơn">Duyệt</button>
																</form>
																<form
																	action="${pageContext.request.contextPath}/receptionist/manage-booking"
																	method="POST" style="display: inline;">
																	<input type="hidden" name="maDon" value="${don.maDon}">
																	<input type="hidden" name="maPhong"
																		value="${don.maPhong}"> <input type="hidden"
																		name="action" value="reject">
																	<button type="submit"
																		class="btn btn-sm btn-outline-danger mb-0"
																		title="Từ chối đơn">Từ chối</button>
																</form>
															</c:if> <!-- Trạng thái ĐÃ XÁC NHẬN -> Chờ khách tới Check-in -->
															<c:if test="${don.trangThaiDon == 'ĐÃ XÁC NHẬN'}">
																<form
																	action="${pageContext.request.contextPath}/receptionist/manage-booking"
																	method="POST" style="display: inline;">
																	<input type="hidden" name="maDon" value="${don.maDon}">
																	<input type="hidden" name="maPhong"
																		value="${don.maPhong}"> <input type="hidden"
																		name="action" value="checkin">
																	<button type="submit" class="btn btn-sm btn-info mb-0"
																		title="Khách nhận phòng">Nhận phòng</button>
																</form>
															</c:if> <!-- Trạng thái ĐANG LƯU TRÚ -> Khách Check-out --> <c:if
																test="${don.trangThaiDon == 'ĐANG LƯU TRÚ'}">
																<form
																	action="${pageContext.request.contextPath}/receptionist/manage-booking"
																	method="POST" style="display: inline;"
																	onsubmit="return confirm('Xác nhận khách đã thanh toán và trả phòng?');">
																	<input type="hidden" name="maDon" value="${don.maDon}">
																	<input type="hidden" name="maPhong"
																		value="${don.maPhong}"> <input type="hidden"
																		name="action" value="checkout">
																	<button type="submit"
																		class="btn btn-sm btn-warning mb-0"
																		title="Khách trả phòng">Trả phòng</button>
																</form>
															</c:if> <!-- Các trạng thái đã kết thúc vòng đời --> <c:if
																test="${don.trangThaiDon == 'ĐÃ HOÀN THÀNH' || don.trangThaiDon == 'ĐÃ TỪ CHỐI' || don.trangThaiDon == 'ĐÃ HỦY'}">
																<span class="text-xs text-secondary">Đã xử lý
																	(NV: ${don.maNV != null ? don.maNV : 'Khách'})</span>
															</c:if>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
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
		src="${pageContext.request.contextPath}/assets/js/dashboard_js/material-dashboard.min.js?v=3.0.0"></script>
</body>
</html>