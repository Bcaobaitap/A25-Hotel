/**
 * File xử lý khởi tạo và cấu hình biểu đồ doanh thu độc lập
 */
document.addEventListener("DOMContentLoaded", function () {
    var canvasElement = document.getElementById("chart-bar-revenue");
    if (!canvasElement) return;

    // Đọc mã JSON an toàn từ thuộc tính data của thẻ HTML
    var chuoiLabels = JSON.parse(canvasElement.getAttribute("data-labels") || "[]");
    var chuoiData = JSON.parse(canvasElement.getAttribute("data-data") || "[]");

    var ctx = canvasElement.getContext("2d");

    // Tự động chuyển đổi sang cấu hình tối ưu dựa trên số lượng điểm dữ liệu
    var totalPoints = chuoiLabels.length;
    var chartType = "bar"; 
    var backgroundConfig = "rgba(255, 255, 255, .8)";
    var borderColorConfig = "transparent";
    var fillConfig = false;

    // Logic tối ưu trực quan: Nếu dữ liệu hiển thị lớn hơn 15 ngày, chuyển sang biểu đồ đường
    if (totalPoints > 15) {
        chartType = "line";
        backgroundConfig = "rgba(255, 255, 255, 0.2)";
        borderColorConfig = "rgba(255, 255, 255, 1)";
        fillConfig = true;
    }

    new Chart(ctx, {
        type: chartType,
        data: {
            labels: chuoiLabels,
            datasets: [{
                label: "Doanh thu (VNĐ)",
                tension: 0.4,
                borderWidth: chartType === "line" ? 3 : 0,
                borderRadius: 4,
                borderSkipped: false,
                backgroundColor: backgroundConfig,
                borderColor: borderColorConfig,
                fill: fillConfig,
                data: chuoiData,
                maxBarThickness: 35
            }],
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    display: false,
                }
            },
            scales: {
                y: {
                    grid: {
                        drawBorder: false,
                        display: true,
                        drawOnChartArea: true,
                        drawTicks: false,
                        borderDash: [5, 5],
                        color: 'rgba(255, 255, 255, .2)'
                    },
                    ticks: {
                        suggestedMin: 0,
                        beginAtZero: true,
                        padding: 10,
                        font: {
                            size: 11,
                            weight: 300,
                            family: "Roboto",
                            style: 'normal',
                            lineHeight: 2
                        },
                        color: "#fff"
                    },
                },
                x: {
                    grid: {
                        drawBorder: false,
                        display: false,
                        drawOnChartArea: false,
                        drawTicks: false
                    },
                    ticks: {
                        display: true,
                        color: '#f8f9fa',
                        padding: 10,
                        maxRotation: totalPoints > 10 ? 45 : 0, // Tự động xoay nghiêng chữ nếu quá nhiều ngày để tránh đè nhau
                        minRotation: totalPoints > 10 ? 45 : 0,
                        font: {
                            size: 11,
                            weight: 300,
                            family: "Roboto",
                            style: 'normal',
                            lineHeight: 1.5
                        },
                    }
                },
            },
        },
    });
});