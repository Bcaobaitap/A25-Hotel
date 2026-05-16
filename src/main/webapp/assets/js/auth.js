/**
 * Module xử lý xác thực và điều hướng người dùng (Authentication & Redirect)
 */
function checkLoginAndBook() {
    // Kiểm tra xem đối tượng cấu hình hệ thống đã tồn tại hay chưa
    if (!window.AppConfig) {
        console.error("Lỗi hệ thống: Cấu hình AppConfig toàn cục chưa được thiết lập!");
        return;
    }

    // Thực hiện kiểm tra trạng thái đăng nhập từ bộ nhớ trình duyệt
    if (!window.AppConfig.isLoggedIn) {
        window.location.href = window.AppConfig.contextPath + "/login";
    } else {
        // Đã đăng nhập -> Cho phép đi tiếp vào danh sách phòng
        window.location.href = window.AppConfig.contextPath + "/rooms";
    }
}