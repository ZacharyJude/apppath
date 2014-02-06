package com.wandoujia.hackday.apppath.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类，进行各种日期时间格式的转化以及格式化
 * 
 * @author jerry
 * @version v1.0.0
 */
public class DateTimeUtil {

    // /
    // 定义时间日期显示格式
    // /
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    private final static String DATE_FORMAT_CN = "yyyy年MM月dd日";

    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";

    private final static String MONTH_FORMAT = "yyyy-MM";

    private final static String DAY_FORMAT = "yyyyMMdd";

    public final static String TIME_HOURMINUTE_FORMAT = "HH:mm";

    public final static long DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;

    public final static long WEEK_IN_MILLISECONDS = 7 * 24 * 60 * 60 * 1000;

    // private final static String TIME_FORMAT_MILLI =
    // "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 取得当前系统时间，返回java.util.Date类型
     * 
     * @see java.util.Date
     * @return java.util.Date 返回服务器当前系统时间
     */
    public static Date getCurrDate() {
        return new Date();
    }

    /**
     * 取得当前系统时间戳
     * 
     * @see java.sql.Timestamp
     * @return java.sql.Timestamp 系统时间戳
     */
    public static java.sql.Timestamp getCurrTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(java.util.Date, String)
     * @return String 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
     */
    public static String getFormatDate(Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(java.util.Date)
     * @return Date 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
     */
    public static Date getFormatDateToDate(Date currDate) {
        return getFormatDate(getFormatDate(currDate));
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(java.util.Date, String)
     * @return String 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static String getFormatDate_CN(Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate_CN(String)
     * @return Date 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static Date getFormatDateToDate_CN(Date currDate) {
        return getFormatDate_CN(getFormatDate_CN(currDate));
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(String, String)
     * @return Date 返回格式化后的日期，默认格式为yyyy-MM-dd，如2006-02-15
     */
    public static Date getFormatDate(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(String, String)
     * @return 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static Date getFormatDate_CN(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    /**
     * 根据格式得到格式化后的日期
     * 
     * @param currDate
     *            要格式化的日期
     * @param format
     *            日期格式，如yyyy-MM-dd
     * @see java.text.SimpleDateFormat#format(java.util.Date)
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     *         定义，如yyyy-MM-dd，如2006-02-15
     */
    public static String getFormatDate(Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            return dtFormatdB.format(currDate);
        }
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * 
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(java.util.Date, String)
     * @return String 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     */
    public static String getFormatDateTime(Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * 
     * @param currDate
     *            要格式环的时间
     * @see #getFormatDateTime(String)
     * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     */
    public static Date getFormatDateTimeToTime(Date currDate) {
        return getFormatDateTime(getFormatDateTime(currDate));
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * 
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(String, String)
     * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     */
    public static Date getFormatDateTime(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     * 
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(java.util.Date, String)
     * @return String 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     */
    public static String getFormatDateTime_CN(Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     * 
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime_CN(String)
     * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     */
    public static Date getFormatDateTimeToTime_CN(Date currDate) {
        return getFormatDateTime_CN(getFormatDateTime_CN(currDate));
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     * 
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(String, String)
     * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     */
    public static Date getFormatDateTime_CN(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    /**
     * 根据格式得到格式化后的时间
     * 
     * @param currDate
     *            要格式化的时间
     * @param format
     *            时间格式，如yyyy-MM-dd HH:mm:ss
     * @see java.text.SimpleDateFormat#format(java.util.Date)
     * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatDateTime(Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
            return dtFormatdB.format(currDate);
        }
    }

    /**
     * 根据格式得到格式化后的日期
     * 
     * @param currDate
     *            要格式化的日期
     * @param format
     *            日期格式，如yyyy-MM-dd
     * @see java.text.SimpleDateFormat#parse(String)
     * @return Date 返回格式化后的日期，格式由参数<code>format</code>定义，如yyyy-MM-dd，如2006-02-15
     */
    public static Date getFormatDate(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {}
        }
        return null;
    }

    /**
     * 根据格式得到格式化后的时间
     * 
     * @param currDate
     *            要格式化的时间
     * @param format
     *            时间格式，如yyyy-MM-dd HH:mm:ss
     * @see java.text.SimpleDateFormat#parse(String)
     * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
     */
    public static Date getFormatDateTime(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {}
        }
        return null;
    }

    /**
     * 得到格式化后的当前系统日期，格式为yyyy-MM-dd，如2006-02-15
     * 
     * @see #getFormatDate(java.util.Date)
     * @return String 返回格式化后的当前服务器系统日期，格式为yyyy-MM-dd，如2006-02-15
     */
    public static String getCurrDateStr() {
        return getFormatDate(getCurrDate());
    }

    /**
     * 得到格式化后的当前系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * 
     * @see #getFormatDateTime(java.util.Date)
     * @return String 返回格式化后的当前服务器系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15
     *         15:23:45
     */
    public static String getCurrDateTimeStr() {
        return getFormatDateTime(getCurrDate());
    }

    /**
     * 得到格式化后的当前系统日期，格式为yyyy年MM月dd日，如2006年02月15日
     * 
     * @see #getFormatDate(java.util.Date, String)
     * @return String 返回当前服务器系统日期，格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static String getCurrDateStr_CN() {
        return getFormatDate(getCurrDate(), DATE_FORMAT_CN);
    }

    /**
     * 得到格式化后的当前系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     * 
     * @see #getFormatDateTime(java.util.Date, String)
     * @return String 返回格式化后的当前服务器系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日
     *         15:23:45
     */
    public static String getCurrDateTimeStr_CN() {
        return getFormatDateTime(getCurrDate(), TIME_FORMAT_CN);
    }

    /**
     * 得到系统当前日期的前或者后几天
     * 
     * @param iDate
     *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @see java.util.Calendar#add(int, int)
     * @return Date 返回系统当前日期的前或者后几天
     */
    public static Date getDateBeforeOrAfter(int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后几天
     * 
     * @param iDate
     *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @see java.util.Calendar#add(int, int)
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几天
     */
    public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 得到格式化后的月份，格式为yyyy-MM，如2006-02
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(java.util.Date, String)
     * @return String 返回格式化后的月份，格式为yyyy-MM，如2006-02
     */
    public static String getFormatMonth(Date currDate) {
        return getFormatDate(currDate, MONTH_FORMAT);
    }

    /**
     * 得到格式化后的日，格式为yyyyMMdd，如20060210
     * 
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(java.util.Date, String)
     * @return String 返回格式化后的日，格式为yyyyMMdd，如20060210
     */
    public static String getFormatDay(Date currDate) {
        return getFormatDate(currDate, DAY_FORMAT);
    }

    /**
     * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     * 
     * @param currDate
     *            要格式化的日期
     * @see java.util.Calendar#getMinimum(int)
     * @see #getFormatDate(java.util.Date, String)
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     */
    public static String getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     * 
     * @param currDate
     *            要格式化的日期
     * @see java.util.Calendar#getMinimum(int)
     * @see #getFormatDate(java.util.Date, String)
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     */
    public static String getFirstDayOfMonth(Date currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到日期的前或者后几小时
     * 
     * @param iHour
     *            如果要获得前几小时日期，该参数为负数； 如果要获得后几小时日期，该参数为正数
     * @see java.util.Calendar#add(int, int)
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几小时
     */
    public static Date getDateBeforeOrAfterHours(Date curDate, int iHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.HOUR_OF_DAY, iHour);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后若干分钟
     * 
     * @param iHour
     *            如果要获得前若干分钟日期，该参数为负数； 如果要获得后若干分钟日期，该参数为正数
     * @see java.util.Calendar#add(int, int)
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后若干分钟
     */
    public static Date getDateBeforeOrAfterMinutes(Date curDate, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    // 根据日期得到格式为yyyyMMdd的日期字符串
    public static String getFormateDayTime(Date currDate) {
        return getFormatDateTime(currDate, DAY_FORMAT);
    }

    // 根据日期字符串获取Date对象
    public static Date getDayFormateDayTime(String date) {
        return getFormatDate(date, DAY_FORMAT);
    }

    // 计算两个日期的时间间隔
    public static int getDaysBetweenDate(Date d1, Date d2) {
        int days;
        days = (int) ((d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000));
        return days;
    }

    // 计算两个时间戳的时间间隔
    public static long getNaturalDaysBetweenTimestamp(long t1, long t2) {
        long d1 = t1 / DAY_IN_MILLISECONDS;
        long d2 = t2 / DAY_IN_MILLISECONDS;
        return d2 - d1;
    }
}
