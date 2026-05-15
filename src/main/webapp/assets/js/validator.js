const Validator = {
    // Biểu thức Regex tương đồng với Java Server
    isEmail: function(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email.trim());
    },
    
    isPhone: function(phone) {
        const re = /^(0)[0-9]{9}$/;
        return re.test(phone.trim());
    },

    // Hàm gắn sự kiện kiểm tra trực tiếp vào thẻ input
    applyValidation: function(inputId, type, errorMessage) {
        const inputElement = document.getElementById(inputId);
		if (!inputElement) return;
		
        const errorElement = document.createElement('span');
        
        // Setup CSS cho thông báo lỗi
        errorElement.style.color = 'red';
        errorElement.style.fontSize = '12px';
        errorElement.style.display = 'none';
        errorElement.innerText = errorMessage;
        inputElement.parentNode.insertBefore(errorElement, inputElement.nextSibling);

        // Bắt sự kiện khi người dùng gõ phím hoặc rời khỏi ô nhập
        inputElement.addEventListener('input', function() {
            let isValid = false;
            if (type === 'email') isValid = Validator.isEmail(this.value);
            if (type === 'phone') isValid = Validator.isPhone(this.value);
            
            if (!isValid && this.value.length > 0) {
                inputElement.style.border = '1px solid red';
                errorElement.style.display = 'block';
            } else {
                inputElement.style.border = '1px solid #ccc';
                errorElement.style.display = 'none';
            }
        });
    },
	
	// Hàm ràng buộc logic Ngày nhận và Ngày trả
	    applyDateValidation: function(fromDateId, toDateId) {
	        const fromDateInput = document.getElementById(fromDateId);
	        const toDateInput = document.getElementById(toDateId);

	        // Đảm bảo cả 2 thẻ input đều tồn tại trên trang
	        if (!fromDateInput || !toDateInput) return;

	        // Tùy chọn: Không cho phép chọn ngày trong quá khứ
	        const today = new Date().toISOString().split('T')[0];
	        fromDateInput.setAttribute('min', today);

	        // Bắt sự kiện khi Ngày nhận thay đổi
	        fromDateInput.addEventListener('change', function() {
	            const selectedFromDate = this.value;
	            
	            // Ép Ngày trả không được nhỏ hơn Ngày nhận
	            toDateInput.setAttribute('min', selectedFromDate);
	            
	            // Nếu khách đã lỡ chọn Ngày trả sai trước đó, tự động reset
	            if (toDateInput.value && toDateInput.value < selectedFromDate) {
	                toDateInput.value = selectedFromDate;
	            }
	        });
	    }
};