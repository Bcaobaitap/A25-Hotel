package com.hotel.report;

import com.hotel.dto.DoanhThuDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.OutputStream;
import java.util.List;

public class RevenueReportGenerator {
    // 1. KẾT XUẤT EXCEL DOANH THU
    public static void exportExcel(List<DoanhThuDTO> list, OutputStream out) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Báo Cáo Doanh Thu");

        CellStyle headerStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        String[] headers = {"STT", "Ngày", "Doanh Thu (VNĐ)"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        double totalRevenue = 0;
        for (DoanhThuDTO dt : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum - 1);
            row.createCell(1).setCellValue(dt.getNgay());
            row.createCell(2).setCellValue(dt.getTongTien());
            totalRevenue += dt.getTongTien();
        }
        
        Row totalRow = sheet.createRow(rowNum);
        totalRow.createCell(1).setCellValue("TỔNG CỘNG:");
        totalRow.createCell(2).setCellValue(totalRevenue);

        for (int i = 0; i < headers.length; i++) { sheet.autoSizeColumn(i); }
        workbook.write(out);
        workbook.close();
    }

    // 2. KẾT XUẤT PDF DOANH THU
    public static void exportPdf(List<DoanhThuDTO> list, OutputStream out, String fontPath) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);
        document.open();

        BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 18, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD, BaseColor.WHITE);
        com.itextpdf.text.Font fontData = new com.itextpdf.text.Font(bf, 11, com.itextpdf.text.Font.NORMAL);

        Paragraph title = new Paragraph("BÁO CÁO DOANH THU KHÁCH SẠN", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(25);
        document.add(title);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f, 3f, 3f});

        String[] headers = {"STT", "Ngày", "Doanh Thu (VNĐ)"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
            cell.setBackgroundColor(new BaseColor(41, 128, 185));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }

        int stt = 1;
        double totalRevenue = 0;
        for (DoanhThuDTO dt : list) {
            table.addCell(new Phrase(String.valueOf(stt++), fontData));
            table.addCell(new Phrase(dt.getNgay(), fontData));
            table.addCell(new Phrase(String.format("%,.0f đ", dt.getTongTien()), fontData));
            totalRevenue += dt.getTongTien();
        }

        PdfPCell totalLabelCell = new PdfPCell(new Phrase("TỔNG CỘNG:", fontHeader));
        totalLabelCell.setColspan(2);
        totalLabelCell.setBackgroundColor(new BaseColor(41, 128, 185));
        totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(totalLabelCell);
        
        PdfPCell totalValueCell = new PdfPCell(new Phrase(String.format("%,.0f đ", totalRevenue), fontHeader));
        totalValueCell.setBackgroundColor(new BaseColor(41, 128, 185));
        table.addCell(totalValueCell);

        document.add(table);
        document.close();
    }
}