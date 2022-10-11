package com.mydemo.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 计算每月工作日天数
 * temporary demo
 * @author 1
 */
public class StaticTest {

    public static void main(String[] args) {
        Date date = new Date();
        date.setMonth(0);
        int year = date.getYear();
        while (date.getYear() <= year) {
            calculateDayNums(date);
            date.setMonth(date.getMonth() + 1);
        }
    }

    public static void calculateDayNums(Date date) {
        Date firstDayOfMonth = new Date(date.getYear(), date.getMonth(), 1);
        Date lastDayOfMonth = new Date(date.getYear(), date.getMonth() + 1, 0);

        int dayNums = lastDayOfMonth.getDate() - firstDayOfMonth.getDate() + 1;
        int weekNums = dayNums / 7;
        int otherDays = dayNums % 7;

        int weekendDayNums = weekNums * 2;

        int firstDayWeek = firstDayOfMonth.getDay();
        int lastDayWeek = lastDayOfMonth.getDay();

//        // 本月第一天为周几
//        int dayOfWeek = lastDayOfMonth.getDay() - otherDays + 1;
//        // 本月最后一天为周日，且 本月周数余几天 为1或2
//        if (lastDayOfMonth.getDay() == 0 && (otherDays == 1 || otherDays == 2)) {
//            weekendDayNums += otherDays;
//            // 本月最后一天为周六 或 第一天为周日
//        } else if (lastDayOfMonth.getDay() == 6 || dayOfWeek == 0) {
//            weekendDayNums += 1;
//            // 整数周后第一天为周六或周六之前，因为用最后一日计算的时间，则必有周六，周日，
//        } else if (dayOfWeek <= -1){
//            weekendDayNums += 2;
//        }
//        System.out.println(date.getMonth() + 1 + "月工作日" + (dayNums - weekendDayNums) + "天");

        if (otherDays != 0) {
            if (firstDayWeek < lastDayWeek) {
                if (firstDayWeek == 0) {
                    weekendDayNums += 1;
                } else if (lastDayWeek == 6) {
                    weekendDayNums += 1;
                }
            } else if (firstDayWeek > lastDayWeek) {
                weekendDayNums += 2;
            }
        }
        System.out.println(date.getMonth() + 1 + "月工作日" + (dayNums - weekendDayNums) + "天");
    }

}
