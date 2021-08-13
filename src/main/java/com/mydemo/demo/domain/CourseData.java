package com.mydemo.demo.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class CourseData {

    @Excel(name = "序号")
    private int id;
    @Excel(name = "课程名称")
    private String courseName;
    @Excel(name = "课程中文名")
    private String courseNameCn;
//    @Excel(name = "教授职称")
//    private String professorShip;
//    @Excel(name = "教授姓名")
//    private String professorName;
//    @Excel(name = "课题重点")
//    private String content;
//    @Excel(name = "开课时间")
//    private String startMonth;
//    @Excel(name = "项目类型")
//    private String projectType;
//    @Excel(name = "学科类型")
//    private String courseType;
//    @Excel(name = "标签（用来专业检索）")
//    private String topic;
//    @Excel(name = "详情链接")
//    private String url;


}
