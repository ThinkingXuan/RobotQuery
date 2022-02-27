package com.njcit.robotquery.util;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by youxuan on 2017/3/25 0025.
 */

public class DateUtil {

    public static final String TAG = "DateUtil";
    //String -> Date
    public static Date getDateTime(String time) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return s.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //String 类型  获取当前时间
    public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }

    public static String getCurrentTime_day(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);
    }

    public static String getDateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);

    }
    //String类型的时间转换为整数时间

    public static long  getDateStrToLong(String str){

        Date date = null; // String类型转成date类型
        try {

            date = stringToDate(str, "yyyy-MM-dd HH:mm:ss");
            if (date == null) {
                return 0;
            } else {
                long currentTime = dateToLong(date); // date类型转成long类型
                Log.d(TAG, "getDateStrToLong: "+str+":"+currentTime);
                return currentTime;
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    //String类型的GMT时间转换成String类型的格式化时间
    public static String GMTToStringDate(String time){

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT' yyyy", Locale.US);
            Date date = sdf.parse(time);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

            return sdf2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getLongToStr_Sencond(long millseconds){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millseconds);

        return sdf.format(date);
    }

    public static String getLongToStr_Day(long millseconds){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(millseconds);
        return sdf.format(date);
    }
}
