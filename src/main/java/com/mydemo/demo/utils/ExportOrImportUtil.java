package com.mydemo.demo.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public  class ExportOrImportUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExportOrImportUtil.class);

    /**
     * 导出通用方法
     * @param response
     * @param list
     * @param title
     * @param clazz
     */
    public static void export(HttpServletResponse response,List<?> list,String title,Class<?> clazz) {
        try {
            ExportParams exportParams = new ExportParams(title,title);
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");

            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title,"UTF-8") + ".xls");
            //编码
            response.setCharacterEncoding("UTF-8");
            exportExcel(exportParams,clazz,list).write(response.getOutputStream());
        }catch (Exception e){
            logger.error(e.toString(),"导出失败");
        }
    }


    /**
     * 通用导入方法
     * @param file
     * @param clazz
     * @return
     */
    public static List Import(MultipartFile file , Class<?> clazz) {
        try {
            if (file.isEmpty()){
                return Collections.EMPTY_LIST;
            }
            return importExcel(file.getInputStream(),clazz);
        }catch (Exception e){
            logger.error(e.toString(),"导入失败");
            return Collections.EMPTY_LIST;
        }
    }


    /**
     * Excel 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param file
     * @param pojoClass
     * @return
     */
    public static <T> List<T> importExcel(File file, Class<?> pojoClass) {
        ImportParams importParams = new ImportParams();
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return new ExcelImportService().importExcelByIs(in, pojoClass, importParams, false).getList();
        } catch (ExcelImportException e) {
            throw new ExcelImportException(e.getType(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ExcelImportException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Excel 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param inputstream
     * @param pojoClass
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcel(InputStream inputstream, Class<?> pojoClass) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        return new ExcelImportService().importExcelByIs(inputstream, pojoClass, importParams, false).getList();
    }

    /**
     * @param entity    表格标题属性
     * @param pojoClass Excel对象Class
     * @param dataSet   Excel对象数据List
     */
    public static Workbook exportExcel(ExportParams entity, Class<?> pojoClass,
                                       Collection<?> dataSet) {
        Workbook workbook = ExcelExportUtil.exportExcel(entity, pojoClass, dataSet);
        return workbook;
    }

}
