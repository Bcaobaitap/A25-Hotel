/**
 * Module xử lý bộ lọc phòng nâng cao kết hợp (Isotope Filter Engine)
 */
$(window).on('load', function () {
    // Tước quyền kiểm soát ngầm của các sự kiện click cũ trong file custom.js
    $('.filters_menu li').off('click');

    // Khởi tạo thực thể Isotope độc lập điều khiển lưới hiển thị phòng
    var $grid = $(".grid").isotope({
        itemSelector: ".all",
        layoutMode: "fitRows",
    });

    // Hàm lõi tính toán điều kiện lọc 
    function performFilter() {
        var minInput = $("#minPrice").val().trim();
        var maxInput = $("#maxPrice").val().trim();
        
        // Chuẩn hóa dữ liệu đầu vào: Chuyển chuỗi rỗng thành các biên số logic
        var min = minInput !== "" ? parseFloat(minInput) : 0;
        var max = maxInput !== "" ? parseFloat(maxInput) : Infinity;
        
        // Truy vết Tab danh mục nào đang giữ trạng thái kích hoạt active
        var typeFilter = $(".filters_menu li.active").attr("data-filter") || "*";

        // Thực thi hàm quét sâu vào từng phần tử DOM của lưới phòng
        $grid.isotope({
            filter: function () {
                var priceStr = $(this).attr("data-price") || "0";
                var price = parseFloat(priceStr);

                var isPriceMatch = (price >= min && price <= max);
                var isTypeMatch = (typeFilter === "*") ? true : $(this).is(typeFilter);
                
                return isPriceMatch && isTypeMatch;
            }
        });
    }

    // Đăng ký bộ lắng nghe sự kiện Click cho nút Lọc Giá
    $(document).on('click', '#btnFilterPrice', function (e) {
        e.preventDefault();
        performFilter();
    });

    // Đăng ký lại hành vi Click cho các Tab Menu phân loại phòng
    $('.filters_menu li').on('click', function () {
        $(".filters_menu li").removeClass("active").css({"color": "black", "font-weight": "normal"});
        $(this).addClass("active").css({"color": "#ffbe33", "font-weight": "bold"});

        performFilter();
    });
});