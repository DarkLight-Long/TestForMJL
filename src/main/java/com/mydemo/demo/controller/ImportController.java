package com.mydemo.demo.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import com.mydemo.demo.domain.UploadRequest;
import com.mydemo.demo.service.ImportService;
import com.mydemo.utils.ImportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ImportService importService;

    @GetMapping("/import")
    @ResponseBody
//    public List importExcel(MultipartFile file) {
    public List importExcel() {
        try {
            File file = new File("/Users/xiaolan/Desktop/2021北美冬校&澳洲暑校华科课表.xlsx");
            InputStream inputStream = new FileInputStream(file);

            ImportParams importParams = new ImportParams();
//            importParams.setTitleRows(1);
            importParams.setLastOfInvalidRow(61);
            List<ImportUtils.Course> list = new ExcelImportService().importExcelByIs(inputStream, ImportUtils.Course.class, importParams, false).getList();
            System.out.println(list.toString());
            return list;
        } catch (ExcelImportException e) {
            throw new ExcelImportException(e.getType(), e);
        } catch (Exception e) {
            throw new ExcelImportException(e.getMessage(), e);
        }
    }

    @PostMapping("/upload")
    public void uploadFile(UploadRequest request, Integer size) {
        importService.uploadFile(request, size);
    }


}
