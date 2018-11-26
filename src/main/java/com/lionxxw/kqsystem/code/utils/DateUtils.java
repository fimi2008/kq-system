package com.lionxxw.kqsystem.code.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Description: 时间工具类 </p>
 *
 * @author lionxxw
 * @date 16/5/6 上午9:46
 * @version 1.0
 */
public abstract class DateUtils {
    public static final String DATE_FROMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_FROMAT_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FROMAT_MM = "yyyy-MM-dd HH:mm";
    public static final long DAY_TIME = 86400000L; // 一天的时间 单位 毫秒

    public static Date getDate(String s) {
        return getDate(s, null);
    }

    public static Date getJustDate(String s) {
        return getDate(s, DATE_FROMAT_DAY);
    }

    public static Date getDate(long date) {
        return getDate(date, null);
    }

    public static Date getJustDate(long date) {
        return getDate(date, DATE_FROMAT_DAY);
    }

    public static Date getDate(long date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATE_FROMAT_SS;
        }

        return getDate(formatDate(new Date(date), format), format);
    }

    public static Date getDate(String s, String format) {
        Date date;
        try {
            if (StringUtils.isEmpty(format)) {
                format = DATE_FROMAT_SS;
            }

            date = new SimpleDateFormat(format).parse(s);
        } catch (Exception e) {
            date = new Date(0L);
        }

        return date;
    }

    /**
     * 在该时间基础上加/减几天
     * @param s
     * @param format
     * @param n 在该时间基础上加(1)/减(-1)n天
     * @return
     */
    public static Date getDate(String s, String format, int n){
        Date date;
        try {
            if (StringUtils.isEmpty(format)) {
                format = DATE_FROMAT_SS;
            }

            date = new SimpleDateFormat(format).parse(s);
            date = new Date(date.getTime() + n*DAY_TIME);
        } catch (Exception e) {
            date = new Date(0L);
        }

        return date;
    }

    public static String formatDate(long date, String format) {
        return formatDate(new Date(date), format);
    }

    public static String formatDate(long date) {
        return formatDate(new Date(date), null);
    }

    public static String formatJustDate(long date) {
        return formatDate(new Date(date), DATE_FROMAT_DAY);
    }

    public static String formatDate(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATE_FROMAT_SS;
        }

        return new SimpleDateFormat(format).format(date);
    }
}