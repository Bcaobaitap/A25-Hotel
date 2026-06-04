const Validator = {
    isPhone: function(phone) {
        return /^(0)[0-9]{9}$/.test(phone.trim());
    },

    init: function() {
        console.log("Khởi động hệ thống Validate..."); // Dòng này để bạn ấn F12 kiểm tra xem JS đã chạy chưa

        const phoneInput = document.getElementById('phoneInputBooking');
        const fromDateInput = document.getElementById('ngayNhan');
        const toDateInput = document.getElementById('ngayTra');
        
        // Tìm form đang chứa input số điện thoại
        const form = phoneInput ? phoneInput.closest('form') : null;

        if (!phoneInput || !fromDateInput || !toDateInput || !form) {
            return; // Nếu không tìm thấy thẻ, dừng lại để không báo lỗi linh tinh
        }

        // ==================================================
        // 1. LOGIC SỐ ĐIỆN THOẠI (Giữ nguyên code gốc của bạn)
        // ==================================================
        const errorElement = document.createElement('span');
        errorElement.style.color = 'red';
        errorElement.style.fontSize = '12px';
        errorElement.style.display = 'none';
        errorElement.innerText = 'Số điện thoại không hợp lệ';
        phoneInput.parentNode.insertBefore(errorElement, phoneInput.nextSibling);

        phoneInput.addEventListener('input', function() {
            let isValid = Validator.isPhone(this.value);
            if (!isValid && this.value.length > 0) {
                this.style.border = '1px solid red';
                errorElement.style.display = 'block';
            } else {
                this.style.border = '1px solid #ccc';
                errorElement.style.display = 'none';
            }
        });

        // ==================================================
        // 2. LOGIC LỊCH (Giữ nguyên code gốc của bạn)
        // ==================================================
        const today = new Date().toISOString().split('T')[0];
        fromDateInput.setAttribute('min', today);

        fromDateInput.addEventListener('change', function() {
            const selectedFromDate = this.value;
            toDateInput.setAttribute('min', selectedFromDate);
            
            // Tự động reset nếu khách đã lỡ chọn sai
            if (toDateInput.value && toDateInput.value < selectedFromDate) {
                toDateInput.value = selectedFromDate;
            }
        });

        // ==================================================
        // 3. CHỐT CHẶN BẢO VỆ (Ngăn submit trắng trang)
        // ==================================================
        form.addEventListener('submit', function(e) {
            let isPhoneValid = Validator.isPhone(phoneInput.value);
            let isDateValid = true;

            // Kiểm tra lại ngày trước khi gửi
            if (fromDateInput.value && toDateInput.value) {
                if (toDateInput.value < fromDateInput.value) {
                    isDateValid = false;
                }
            }

            if (!isPhoneValid) {
                e.preventDefault(); // Lệnh này giúp chặn việc gửi dữ liệu lên Server
                alert('Vui lòng nhập đúng định dạng số điện thoại!');
                phoneInput.focus(); // Tự động trỏ chuột vào ô bị sai
            } else if (!isDateValid) {
                e.preventDefault();
                alert('Lỗi: Ngày trả phòng không được nhỏ hơn ngày nhận phòng!');
            }
        });
    }
};

// ==================================================
// HỆ THỐNG KÍCH HOẠT THÔNG MINH
// ==================================================
// Nếu trình duyệt đang load dở HTML thì đợi Load xong. 
// Nếu đã load xong rồi thì chạy luôn. Cách này loại bỏ hoàn toàn lỗi "chạy nhầm thời điểm".
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', Validator.init);
} else {
    Validator.init();
}