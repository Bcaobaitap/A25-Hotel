package com.hotel.report;

import com.hotel.model.DonDatPhong;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class BookingReportGenerator {

    // LOGIC XUẤT EXCEL (.xlsx)
    public static void exportExcel(List<DonDatPhong> list, OutputStream out) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Lịch sử đặt phòng");

        // Thiết kế phông chữ và nền cho Header chỉnh chu
        CellStyle headerStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        String[] headers = {"Mã Đơn", "Khách Hàng", "Số Điện Thoại", "Mã Phòng", "Ngày Nhận", "Ngày Trả", "Tổng Tiền", "Trạng Thái"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int rowNum = 1;
        for (DonDatPhong don : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("#" + don.getMaDon());
            row.createCell(1).setCellValue(don.getTenNguoiDat());
            row.createCell(2).setCellValue(don.getThongTinLienHe());
            row.createCell(3).setCellValue("P." + don.getMaPhong());
            row.createCell(4).setCellValue(don.getNgayNhan() != null ? sdf.format(don.getNgayNhan()) : "");
            row.createCell(5).setCellValue(don.getNgayTra() != null ? sdf.format(don.getNgayTra()) : "");
            row.createCell(6).setCellValue(don.getTongTien());
            row.createCell(7).setCellValue(don.getTrangThaiDon());
        }

        // Tự động căn chỉnh độ rộng cột vừa khít dữ liệu
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        workbook.write(out);
        workbook.close();
    }

    // LOGIC XUẤT PDF (Hỗ trợ tiếng Việt có dấu)
    public static void exportPdf(List<DonDatPhong> list, OutputStream out, String fontPath) throws Exception {
        // Sử dụng khổ ngang A4 (rotate) để bảng dữ liệu rộng rãi, không bị tràn dòng
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, out);
        document.open();

        // Cấu hình Font vật lý để đọc được tiếng Việt
        BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.BOLD, BaseColor.WHITE);
        com.itextpdf.text.Font fontData = new com.itextpdf.text.Font(bf, 10, com.itextpdf.text.Font.NORMAL);

        Paragraph title = new Paragraph("BÁO CÁO LỊCH SỬ ĐẶT PHÒNG KHÁCH SẠN", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f, 2.5f, 2f, 1f, 1.5f, 1.5f, 2f, 2f}); // Tỷ lệ độ rộng các cột

        String[] headers = {"Mã Đơn", "Khách Hàng", "Số ĐT", "Phòng", "Ngày Nhận", "Ngày Trả", "Tổng Tiền", "Trạng Thái"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
            cell.setBackgroundColor(new BaseColor(0, 51, 102)); // Màu nền xanh đậm lịch sự
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(6);
            table.addCell(cell);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (DonDatPhong don : list) {
            table.addCell(new Phrase("#" + don.getMaDon(), fontData));
            table.addCell(new Phrase(don.getTenNguoiDat(), fontData));
            table.addCell(new Phrase(don.getThongTinLienHe(), fontData));
            table.addCell(new Phrase("P." + don.getMaPhong(), fontData));
            table.addCell(new Phrase(don.getNgayNhan() != null ? sdf.format(don.getNgayNhan()) : "", fontData));
            table.addCell(new Phrase(don.getNgayTra() != null ? sdf.format(don.getNgayTra()) : "", fontData));
            table.addCell(new Phrase(String.format("%,.0f đ", don.getTongTien()), fontData));
            table.addCell(new Phrase(don.getTrangThaiDon(), fontData));
        }

        document.add(table);
        document.close();
    }
}