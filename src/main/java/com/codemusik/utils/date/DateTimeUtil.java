package com.codemusik.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程安全
 *
 * @author: thinkit
 * @date: 2020/5/14 14:56
 * @description: 日期时间工具类
 */
public class DateTimeUtil {
    private DateTimeUtil(){}

    /** 线程安全的日期格式对象 */
    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> {
        // 完整日期
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    });

    /** 线程安全的日期格式对象 */
    private static final ThreadLocal<DateFormat> YMD = ThreadLocal.withInitial(() -> {
        // 年月日
        return new SimpleDateFormat("yyyy-MM-dd");
    });

    /** 线程安全的日期格式对象 */
    private static final ThreadLocal<DateFormat> YMDHMS = ThreadLocal.withInitial(() -> {
        // 完整日期
        return new SimpleDateFormat("yyyyMMddHHmmss");
    });

    /**
     * 格式化完整日期
     *
     * @param date 需要格式化的日期
     * @return yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.get().format(date);
    }

    /**
     * 格式化年月日
     *
     * @param date 需要格式化的日期
     * @return yyyy-MM-dd格式的字符串
     */
    public static String formatYMD(Date date) {
        return YMD.get().format(date);
    }

    /**
     * 格式化年月日
     *
     * @param date 需要格式化的日期
     * @return yyyyMMddHHmmss格式的字符串
     */
    public static String formatYMDHMS(Date date) {
        return YMDHMS.get().format(date);
    }

}
