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
<title>Quản Lý Phòng - Admin</title>

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

			<!-- Thông báo kết quả -->
			<c:if test="${param.msg == 'create_success'}">
				<div class="alert alert-success text-white">Thêm phòng mới
					thành công!</div>
			</c:if>
			<c:if test="${param.msg == 'delete_success'}">
				<div class="alert alert-warning text-white">Đã xóa phòng khỏi
					hệ thống!</div>
			</c:if>
			<c:if test="${param.msg == 'error'}">
				<div class="alert alert-danger text-white">Có lỗi xảy ra trong
					quá trình thao tác!</div>
			</c:if>

			<div class="row">
				<div class="col-12">
					<div class="card my-4">
						<div
							class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
							<div
								class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3 d-flex justify-content-between align-items-center">
								<h6 class="text-white text-capitalize ps-3 mb-0">Danh Mục
									Phòng Khách Sạn</h6>
								<button type="button" class="btn btn-sm btn-light me-3 mb-0"
									data-bs-toggle="modal" data-bs-target="#addRoomModal">
									<i class="material-icons text-sm">add</i> Thêm Phòng
								</button>
							</div>
						</div>

						<div class="card-body px-0 pb-2">
							<div class="table-responsive p-0">
								<table class="table align-items-center mb-0">
									<thead>
										<tr>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Phòng</th>
											<th
												class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Thông
												Số</th>
											<th
												class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Giá
												(VNĐ)</th>
											<th
												class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Trạng
												Thái</th>
											<th class="text-secondary opacity-7"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listPhong}" var="phong">
											<tr>
												<td>
													<div class="d-flex px-2 py-1">
														<div>
															<img
																src="${pageContext.request.contextPath}/assets/images/homepage_img/${phong.anhPhong}"
																class="avatar avatar-sm me-3 border-radius-lg"
																alt="${phong.tenPhong}"
																onerror="this.src='${pageContext.request.contextPath}/assets/images/homepage_img/f1.png'">
														</div>
														<div class="d-flex flex-column justify-content-center">
															<h6 class="mb-0 text-sm">${phong.tenPhong}</h6>
															<p class="text-xs text-secondary mb-0">ID:
																${phong.maPhong}</p>
														</div>
													</div>
												</td>
												<td>
													<p class="text-xs font-weight-bold mb-0">${phong.loaiPhong}</p>
													<p class="text-xs text-secondary mb-0">Tầng
														${phong.soTang} - ${phong.dienTich} m2</p>
												</td>
												<td class="align-middle text-center text-sm"><span
													class="text-dark font-weight-bold"><fmt:formatNumber
															value="${phong.gia}" type="number" /></span></td>
												<td class="align-middle text-center text-sm"><span
													class="badge badge-sm 
                                ${phong.trangThaiPhong == 'TRỐNG' ? 'bg-gradient-success' : 
                                  phong.trangThaiPhong == 'CÓ KHÁCH' ? 'bg-gradient-danger' : 'bg-gradient-warning'}">
														${phong.trangThaiPhong} </span></td>
												<td class="align-middle text-center">
													<!-- Nút Sửa -->
													<button type="button"
														class="btn btn-link text-dark px-3 mb-0"
														data-bs-toggle="modal"
														data-bs-target="#editRoomModal${phong.maPhong}">
														<i class="material-icons text-sm me-2">edit</i>Sửa
													</button> <!-- Nút Xóa-->
													<form
														action="${pageContext.request.contextPath}/admin/rooms"
														method="POST" style="display: inline;"
														onsubmit="return confirm('Bạn có chắc chắn muốn xóa phòng này? Mọi dữ liệu liên quan sẽ bị ảnh hưởng!');">
														<input type="hidden" name="action" value="delete">
														<input type="hidden" name="maPhong"
															value="${phong.maPhong}">
														<button type="submit"
															class="btn btn-link text-danger text-gradient px-3 mb-0"
															style="padding: 0;">
															<i class="material-icons text-sm me-2">delete</i>Xóa
														</button>
													</form> <!-- Modal Sửa Phòng (Nằm ngay trong vòng lặp để lấy data của từng phòng) -->
													<div class="modal fade" id="editRoomModal${phong.maPhong}"
														tabindex="-1" role="dialog" aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered modal-lg"
															role="document">
															<div class="modal-content text-start">
																<div class="modal-header bg-gradient-info">
																	<h5 class="modal-title text-white">Cập Nhật Phòng:
																		${phong.tenPhong}</h5>
																	<button type="button" class="btn-close text-white"
																		data-bs-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>

																<form
																	action="${pageContext.request.contextPath}/admin/rooms"
																	method="POST">
																	<div class="modal-body">
																		<input type="hidden" name="action" value="update">
																		<input type="hidden" name="maPhong"
																			value="${phong.maPhong}">
																		<!-- Giữ lại ID phòng -->

																		<div class="row">
																			<div class="col-md-6">
																				<div class="input-group input-group-static mb-3">
																					<label>Tên Phòng</label> <input type="text"
																						name="tenPhong" class="form-control"
																						value="${phong.tenPhong}" required>
																				</div>
																			</div>
																			<div class="col-md-6">
																				<div class="input-group input-group-static mb-3">
																					<label>Loại Phòng</label> <select name="loaiPhong"
																						class="form-control" required>
																						<option value="Phòng đơn"
																							${phong.loaiPhong == 'Phòng đơn' ? 'selected' : ''}>Phòng
																							đơn</option>
																						<option value="Phòng đôi"
																							${phong.loaiPhong == 'Phòng đôi' ? 'selected' : ''}>Phòng
																							đôi</option>
																						<option value="Phòng cận cao cấp"
																							${phong.loaiPhong == 'Phòng cận cao cấp' ? 'selected' : ''}>Phòng
																							cận cao cấp</option>
																						<option value="Phòng cao cấp"
																							${phong.loaiPhong == 'Phòng cao cấp' ? 'selected' : ''}>Phòng
																							cao cấp</option>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-md-4">
																				<div class="input-group input-group-static mb-3">
																					<label>Tầng</label> <input type="number"
																						name="soTang" class="form-control"
																						value="${phong.soTang}" required>
																				</div>
																			</div>
																			<div class="col-md-4">
																				<div class="input-group input-group-static mb-3">
																					<label>Diện tích (m2)</label> <input type="number"
																						step="0.1" name="dienTich" class="form-control"
																						value="${phong.dienTich}" required>
																				</div>
																			</div>
																			<div class="col-md-4">
																				<div class="input-group input-group-static mb-3">
																					<label>Giá (VNĐ/đêm)</label> <input type="number"
																						name="gia" class="form-control"
																						value="<fmt:formatNumber value='${phong.gia}' groupingUsed='false' />"
																						required>
																				</div>
																			</div>
																		</div>
																		<div class="input-group input-group-static mb-3">
																			<label>Tên file ảnh</label> <input type="text"
																				name="anhPhong" class="form-control"
																				value="${phong.anhPhong}" required>
																		</div>
																		<div class="input-group input-group-static mb-3">
																			<label>Mô tả chi tiết</label>
																			<textarea name="moTa" class="form-control" rows="3"
																				required>${phong.moTa}</textarea>
																		</div>
																		
																		<!-- thêm sửa trạng thái phòng -->
																		<div class="input-group input-group-static mb-3">
																					<label>Trạng thái phòng</label> <select name="trangThaiPhong"
																						class="form-control" required>
																						<option value="TRỐNG"
																							${phong.trangThaiPhong == 'TRỐNG' ? 'selected' : ''}>TRỐNG</option>
																						<option value="CÓ KHÁCH"
																							${phong.trangThaiPhong == 'CÓ KHÁCH' ? 'selected' : ''}>CÓ KHÁCH</option>
																						<option value="BẢO TRÌ"
																							${phong.trangThaiPhong == 'BẢO TRÌ' ? 'selected' : ''}>Bảo trì</option>
																					</select>
																				</div>
																	</div>
																	<div class="modal-footer">
																		<button type="button"
																			class="btn btn-outline-secondary"
																			data-bs-dismiss="modal">Hủy</button>
																		<button type="submit" class="btn bg-gradient-info">Cập
																			Nhật</button>
																	</div>
																</form>
															</div>
														</div>
													</div>
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

	<!-- Thêm Phòng -->
	<div class="modal fade" id="addRoomModal" tabindex="-1" role="dialog"
		aria-labelledby="addRoomModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header bg-gradient-primary">
					<h5 class="modal-title text-white" id="addRoomModalLabel">Tạo
						Phòng Mới</h5>
					<button type="button" class="btn-close text-white"
						data-bs-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<form action="${pageContext.request.contextPath}/admin/rooms"
					method="POST">
					<div class="modal-body">
						<input type="hidden" name="action" value="create">

						<div class="row">
							<div class="col-md-6">
								<div class="input-group input-group-outline mb-3">
									<label class="form-label">Tên Phòng (VD: 101, VIP-01)</label> <input
										type="text" name="tenPhong" class="form-control" required>
								</div>
							</div>
							<div class="col-md-6">
								<div class="input-group input-group-static mb-3">
									<label for="loaiPhong" class="ms-0">Loại Phòng</label> <select
										name="loaiPhong" id="loaiPhong" class="form-control" required>
										<option value="Phòng đơn">Phòng đơn</option>
										<option value="Phòng đôi">Phòng đôi</option>
										<option value="Phòng cận cao cấp">Phòng cận cao cấp</option>
										<option value="Phòng cao cấp">Phòng cao cấp</option>
									</select>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<div class="input-group input-group-outline mb-3">
									<label class="form-label">Tầng</label> <input type="number"
										name="soTang" class="form-control" required>
								</div>
							</div>
							<div class="col-md-4">
								<div class="input-group input-group-outline mb-3">
									<label class="form-label">Diện tích (m2)</label> <input
										type="number" step="0.1" name="dienTich" class="form-control"
										required>
								</div>
							</div>
							<div class="col-md-4">
								<div class="input-group input-group-outline mb-3">
									<label class="form-label">Giá (VNĐ/đêm)</label> <input
										type="number" name="gia" class="form-control" required>
								</div>
							</div>
						</div>

						<div class="input-group input-group-outline mb-3">
							<label class="form-label">Tên file ảnh (VD: f1.png)</label> <input
								type="text" name="anhPhong" class="form-control" required>
						</div>

						<div class="input-group input-group-outline mb-3">
							<textarea name="moTa" class="form-control" rows="3"
								placeholder="Mô tả chi tiết phòng..." required></textarea>
						</div>

					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-outline-secondary"
							data-bs-dismiss="modal">Hủy</button>
						<button type="submit" class="btn bg-gradient-primary">Lưu
							Dữ Liệu</button>
					</div>
				</form>
			</div>
		</div>
	</div>

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