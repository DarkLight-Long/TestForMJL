package com.mydemo.utils;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import com.mydemo.demo.domain.CourseData;
import lombok.Data;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ImportUtils {

    @Data
    public static class Course {
        @Excel(name = "时间")
        private String time;
        @Excel(name = "课程类型")
        private String courseType;
        @Excel(name = "课程代码")
        private String courseCode;
        @Excel(name = "课程名称")
        private String courseName;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/xiaolan/Desktop/2021北美冬校&澳洲暑校华科课表.xlsx");
        InputStream inputStream = new FileInputStream(file);
        ImportParams importParams = new ImportParams();
//        importParams.setTitleRows(1);
        List<CourseData> list = new ExcelImportService().importExcelByIs(inputStream, Course.class, importParams, false).getList();
        System.out.println(Arrays.toString(list.toArray()));
    }


}
