package com.flamexander.cloud.client;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class WriteExcel {
    public void write(String fileName) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        // создаем файл
        XSSFSheet sheet = workbook.createSheet("Java Books");

        // создаем шрифт
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.GREY_80_PERCENT.getIndex());

        // делаем залоговки
        String[] columns = {"Name", "Surname", "Salary"};
        // заполняем 1 строку (заголовки)
        Row headerRow = sheet.createRow(0);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // заполняем поля ручной режим
        Row rowCustom = sheet.createRow(1);
        Cell cellCustom1 = rowCustom.createCell(0);
        cellCustom1.setCellValue("Sudorov");
        Cell cellCustom2 = rowCustom.createCell(1);
        cellCustom2.setCellValue("Sergei");
        Cell cellCustom3 = rowCustom.createCell(2);
        cellCustom3.setCellValue(42000);

        // автоматически
        Object[][] bookData = {
                {"Ivan", "Ivanov", 40000},
                {"Pert", "Petrov", 36000}
        };

        int rowCount = 1;

        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }

        }

        try (FileOutputStream outputStream = new FileOutputStream(String.format("%s.xlsx", fileName))) {
            workbook.write(outputStream);
        }
    }
}
