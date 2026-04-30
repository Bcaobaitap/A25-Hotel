<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

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
              <span>Lịch Sử Đặt Phòng</span>
            </a>
         </nav>
      </div>
    </header>
  </div>

  <section class="food_section layout_padding">
    <div class="container">
      <div class="heading_container heading_center">
        <h2>Đơn Đặt Phòng Của Tôi</h2>
      </div>

      <c:if test="${not empty message}">
          <div class="alert alert-success text-center" style="margin-top: 20px;">
              ${message}
          </div>
      </c:if>
      
      <c:if test="${param.cancel == 'success'}">
    		<div class="alert alert-success text-center" style="margin-top: 20px;">
        		Đã hủy đơn đặt phòng thành công!
    		</div>
	  </c:if>
	  <c:if test="${param.cancel == 'failed'}">
    		<div class="alert alert-danger text-center" style="margin-top: 20px;">
        		Hủy đơn thất bại. Đơn hàng không tồn tại hoặc đã được xử lý.
    		</div>
	  </c:if>

      <div class="row" style="margin-top: 40px;">
        <div class="col-12">
            <c:choose>
                <c:when test="${empty listDon}">
                    <div class="text-center" style="color: white; font-size: 18px;">
                        Bạn chưa có đơn đặt phòng nào. <br><br>
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-warning" style="color: white; border-radius: 45px; padding: 10px 30px;">Khám Phá Phòng Ngay</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive" style="background: white; border-radius: 10px; padding: 15px;">
                        <table class="table table-hover table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Mã Đơn</th>
                                    <th>Ngày Tạo Đơn</th>
                                    <th>Thông Tin Lưu Trú</th>
                                    <th>Thời Gian Đặt</th>
                                    <th>Tổng Tiền</th>
                                    <th>Trạng Thái</th>
                                    <th class="text-center">Hành Động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listDon}" var="don">
                                    <tr>
                                        <td style="font-weight: bold;">#${don.maDon}</td>
                                        <td>
                                            <fmt:formatDate value="${don.ngayTaoDon}" pattern="dd/MM/yyyy HH:mm" />
                                        </td>
                                        <td>
                                            Phòng ID: ${don.maPhong}<br>
                                            <small class="text-muted">Khách: ${don.tenNguoiDat} (${don.soNguoi} người)</small>
                                        </td>
                                        <td>
                                            Từ: <fmt:formatDate value="${don.ngayNhan}" pattern="dd/MM/yyyy" /><br>
                                            Đến: <fmt:formatDate value="${don.ngayTra}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td style="color: #ffbe33; font-weight: bold;">
                                            <fmt:formatNumber value="${don.tongTien}" type="number" /> VNĐ
                                        </td>
                                        <td>
                                            <span class="badge 
                                                ${don.trangThaiDon == 'CHỜ XÁC NHẬN' ? 'badge-warning' : 
                                                  don.trangThaiDon == 'ĐÃ XÁC NHẬN' ? 'badge-success' : 
                                                  don.trangThaiDon == 'ĐÃ HỦY' ? 'badge-danger' : 'badge-secondary'}" 
                                                style="padding: 8px 12px;">
                                                ${don.trangThaiDon}
                                            </span>
                                        </td>
                                        
                                        <td class="text-center">
            								<c:if test="${don.trangThaiDon == 'CHỜ XÁC NHẬN'}">
                								<form action="${pageContext.request.contextPath}/cancel-order" method="POST" onsubmit="return confirm('Bạn có chắc chắn muốn hủy đơn đặt phòng này không?');">
                    								<input type="hidden" name="maDon" value="${don.maDon}">
                    								<button type="submit" class="btn btn-sm btn-outline-danger" style="border-radius: 20px; font-weight: bold;">
                        								<i class="fa fa-times-circle" aria-hidden="true"></i> Hủy đơn
                    								</button>
                								</form>
            								</c:if>
        								</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
      </div>
    </div>
  </section>

  <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>