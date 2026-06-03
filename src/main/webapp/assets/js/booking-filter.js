// src/main/webapp/assets/js/booking-filter.js
document.addEventListener("DOMContentLoaded", function () {
    const fromDateInput = document.getElementById("fromDate");
    const toDateInput = document.getElementById("toDate");

    // Hàm đồng bộ hóa ràng buộc min/max
    function syncDateConstraints() {
        if (fromDateInput.value) {
            // Ngày kết thúc (toDate) không được nhỏ hơn ngày bắt đầu (fromDate)
            toDateInput.min = fromDateInput.value;
        }
        if (toDateInput.value) {
            // Ngày bắt đầu (fromDate) không được lớn hơn ngày kết thúc (toDate)
            fromDateInput.max = toDateInput.value;
        }
    }

    // Lắng nghe sự kiện thay đổi giá trị của 2 ô input
    fromDateInput.addEventListener("change", function () {
        syncDateConstraints();
        if (toDateInput.value && fromDateInput.value > toDateInput.value) {
            toDateInput.value = "";
        }
    });

    toDateInput.addEventListener("change", function () {
        syncDateConstraints();
        if (fromDateInput.value && toDateInput.value < fromDateInput.value) {
            fromDateInput.value = "";
        }
    });

    // Chạy khởi tạo ban đầu khi trang vừa load
    syncDateConstraints();
    
    // Bảo vệ vòng cuối: Chặn submit form cố tình nếu dữ liệu không hợp lệ
    document.getElementById("filterForm").addEventListener("submit", function (e) {
        if (fromDateInput.value && toDateInput.value && fromDateInput.value > toDateInput.value) {
            e.preventDefault();
            alert("Lỗi: Ngày bắt đầu không thể lớn hơn ngày kết thúc!");
        }
    });
});