package com.mydemo.demo.utils;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import com.mydemo.demo.domain.CourseData;
import com.mydemo.utils.ImportUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestForExport {

    /**
     * 自己读取导入
     */
    public void importExcel() throws IOException {
        File file = new File("/Users/xiaolan/Desktop/2021北美冬校&澳洲暑校华科课表.xlsx");
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(cell.getStringCellValue());
    }

    /**
     * 借助easypoi导入
     */
    public void importExcel2() throws Exception {
        File file = new File("/Users/xiaolan/Desktop/2021北美冬校&澳洲暑校华科课表.xlsx");
        InputStream inputStream = new FileInputStream(file);
        ImportParams importParams = new ImportParams();
//        importParams.setTitleRows(1);
        List<CourseData> list = new ExcelImportService().importExcelByIs(inputStream, ImportUtils.Course.class, importParams, false).getList();
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 导出excel 单文件
     */
    public void exportExcel(HttpServletResponse response) throws IOException {
        Workbook workbook = createExcel();
//        HttpServletResponse response;//懂得都懂
        workbook.write(response.getOutputStream());
    }

    /**
     * 导出为多文件压缩包
     * description 基本思路：服务器上导出为文件，然后压缩为压缩包导出
     */
    public void export2ManyFiles(HttpServletResponse response) throws IOException {
        File zip = new File("./dir.zip");
        List<File> files = new ArrayList<>();
        for (int i=0; i<3;i++) {
            File file = new File("./导出文件" + i + ".xls");
            Workbook workbook = createExcel();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            files.add(file);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(zip);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        for (File file : files) {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            // 通过这里疑似可以和前一个for结合
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            try {
                while (fileInputStream.read(bytes) > -1) {
                    fileOutputStream.write(bytes);
                }
            } finally {
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
        }
        zipOutputStream.flush();
        zipOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(zip);
//        HttpServletResponse response;
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        try {
            while (fileInputStream.read(bytes) > -1) {
                outputStream.write(bytes);
            }
        } finally {
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
        }

    }

    /**
     * 自己写入excel
     */
    private Workbook createExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("学生跟进");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("2333");
        return workbook;
    }

}
