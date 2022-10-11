package com.mydemo.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 待完善 -> 多数据源/数据库切换有关
 */
//@Configuration
public class DynamicDateSourceRouteConfig extends AbstractRoutingDataSource {

//    {
//        dailySliceList.forEach(item -> {
//            Map<Date, Double> dateHours;
//            if (projectDateHours.containsKey(item.getProjectControlValue())) {
//                dateHours = projectDateHours.get(item.getProjectControlValue());
//            } else {
//                dateHours = new HashMap<>();
//                projectDateHours.put(item.getProjectControlValue(), dateHours);
//            }
//            dateHours.merge(item.getDateControlValue(), Math.min(Double.parseDouble(item.getHoursControlValue()), 8.0),
//                    (oldVal, newVal) -> Math.min(Double.sum(oldVal, newVal), 8));
//
//        });
//    }

    @Override
    protected Object determineCurrentLookupKey() {
        return new Object();
    }

}
