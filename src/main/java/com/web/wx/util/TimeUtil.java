package com.web.wx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/15
 */
public class TimeUtil {

    public final static String NORMALFORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间字符串转换时间
     * @param str
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str, String format) throws ParseException {
        if(format == null || format.isEmpty()) {
            format = NORMALFORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(str);
        return date;
    }

    /**
     * 将时间转换为时间字符串
     * @param date
     * @param format
     * @return
     */
    public static String DateToString(Date date, String format){
        String res;
        if(format == null || format.isEmpty()) {
            format = NORMALFORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        res = sdf.format(date);
        return res;
    }
}
