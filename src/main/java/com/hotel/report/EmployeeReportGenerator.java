package com.hotel.report;

import com.hotel.model.TaiKhoan; // Đảm bảo import đúng model 5 tham số của bạn
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.OutputStream;
import java.util.List;

public class EmployeeReportGenerator {

    // 1. KẾT XUẤT EXCEL DANH SÁCH NHÂN SỰ
    public static void exportExcel(List<TaiKhoan> list, OutputStream out) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh Sách Nhân Sự");

        // Style cho dòng Header: Nền xanh, chữ trắng, in đậm
        CellStyle headerStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        // Khớp đúng 5 tham số của model
        String[] headers = {"Mã Tài Khoản", "Tên Đăng Nhập", "Mật Khẩu", "Vai Trò", "Họ Tên"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (TaiKhoan tk : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("TK" + tk.getMaTK()); 
            row.createCell(1).setCellValue(tk.getTenDN());
            row.createCell(2).setCellValue("********"); 
            row.createCell(3).setCellValue(tk.getLoaiTaiKhoan());
            row.createCell(4).setCellValue(tk.getHoTen());
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();
    }

    // 2. KẾT XUẤT PDF DANH SÁCH NHÂN SỰ
    public static void exportPdf(List<TaiKhoan> list, OutputStream out, String fontPath) throws Exception {
        // Dùng khổ A4 đứng là đủ không gian cho 5 cột
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);
        document.open();

        BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD, BaseColor.DARK_GRAY);
        com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD, BaseColor.WHITE);
        com.itextpdf.text.Font fontData = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.NORMAL);

        Paragraph title = new Paragraph("BÁO CÁO DANH SÁCH NHÂN SỰ", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(25);
        document.add(title);

        // Bảng gồm 5 cột
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        // Tỷ lệ độ rộng cho 5 cột
        table.setWidths(new float[]{1.5f, 2.5f, 2f, 2f, 2f});

        String[] headers = {"Mã TK", "Tên Đăng Nhập", "Mật Khẩu", "Vai Trò", "Họ Tên"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
            cell.setBackgroundColor(new BaseColor(41, 128, 185)); 
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }

        for (TaiKhoan tk : list) {
            table.addCell(new Phrase("TK" + tk.getMaTK(), fontData));
            table.addCell(new Phrase(tk.getTenDN(), fontData));
            table.addCell(new Phrase("********", fontData)); 
            table.addCell(new Phrase(tk.getLoaiTaiKhoan(), fontData));
            table.addCell(new Phrase(tk.getHoTen(), fontData));
        }

        document.add(table);
        document.close();
    }
}