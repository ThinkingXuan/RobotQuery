package com.jialin.chat.util;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by youxuan on 2017/3/19 0019.
 */

public class DateUtil {

    public static String friendlyTime(Date time) {
        String dateString = DateFormat.format("yyyy-MM-dd h:mmaa", time).toString();
        String[] split = dateString.split(" ");
        //获取time距离当前的秒数
        int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);
        if (ct == 0) {
            return "刚刚";
        } else if (ct > 0 && ct < 60) {
            return ct + "秒前";
        } else if (ct >= 60) {
            return split[1];
        }
//        } else if (ct >= 60 && ct < 3600) {
//            return Math.max(ct / 60, 1) + "分钟前";
//        } else if (ct >= 3600 && ct < 86400)
//            return ct / 3600 + "小时前";
//        else if (ct >= 86400 && ct < 2592000) { //86400 * 30
//            int day = ct / 86400;
//            return day + "天前";
//        } else if (ct >= 2592000 && ct < 31104000) { //86400 * 30
//            return ct / 2592000 + "月前";
//        }
        return ct / 31104000 + "年前";
    }

    public static boolean inSameDay(Date date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(Date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);
        if ((year1 == year2) && (month1 == month2) && (day1 == day2)) {
            return true;
        }
        return false;
    }

}
