package com.icbc.zsyw.hope3.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName TimeUtil
 * @Description
 * @Author qinwankang
 * @Date 2020/7/28 16:29
 * @Version V1.0
 **/
public class TimeUtil {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.MONTH));//月需要加1
        System.out.println(calendar.get(Calendar.YEAR));

        System.out.println(calendar.get(Calendar.ALL_STYLES));
        System.out.println(calendar.get(Calendar.AM_PM));
        System.out.println(calendar.get(Calendar.AM));
        System.out.println(calendar.get(Calendar.APRIL));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));//星期要减一
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.DATE));//和day_of_month一回事
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));//？？
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("***********************datae*****");
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(date.getDate());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
        System.out.println(date.getTimezoneOffset());
        System.out.println(date.getDay());
        System.out.println(date.getHours());
        System.out.println(date.getMinutes());
        System.out.println(date.getSeconds());
        System.out.println(date.toGMTString());
        System.out.println("***********************skskdkjdsjdsddddd*****");
        Calendar calendar1 = Calendar.getInstance();
        Date date1 = new Date();
        System.out.println();
    }
}
