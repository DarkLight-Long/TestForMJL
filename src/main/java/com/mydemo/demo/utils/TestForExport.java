package com.mydemo.demo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class TestForExport {

    public void importExcel() throws IOException {
        File file = new File("/Users/xiaolan/Desktop/2021北美冬校&澳洲暑校华科课表.xlsx");
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(cell.getStringCellValue());
    }

    public void exportExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("学生跟进");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("2333");
    }

}
