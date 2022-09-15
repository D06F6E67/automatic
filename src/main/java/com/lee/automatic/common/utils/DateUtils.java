package com.lee.automatic.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 日前工具类
 *
 * @author Lee
 */
public class DateUtils {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
	 */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String todayDateTime() {
        return format(new Date(), DATE_TIME_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (Objects.nonNull(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
        return "";
    }
}
