package com.osyangxin.moji.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

    public static String sub(Date thisTime, Date endTime) {
        long l1 = thisTime.getTime() / 1000;
        long l2 = endTime.getTime() / 1000;
        long ll = l2 - l1;
        long dd = ll / 24 / 60 / 60;
        long hh = (ll - dd * 24 * 60 * 60) / (60 * 60);
        long mm = (ll - dd * 24 * 60 * 60 - hh * 60 * 60) / 60;
        long ss = (ll - dd * 24 * 60 * 60 - hh * 60 * 60 - mm * 60);
        String ddStr = dd == 0 ? "" : dd + "天 ";
        String hhStr = hh == 0 ? "" : hh + "小时 ";
        String mmStr = mm == 0 ? "" : mm + "分钟 ";
        String ssStr = ss == 0 ? "" : ss + "秒";
        return ddStr + hhStr + mmStr + ssStr;
    }

    public static int getProcess(Date beginTime, Date endTime) {
        long l1 = beginTime.getTime() / 1000;
        long l2 = endTime.getTime() / 1000;
        long now = (DateUtils.now()).getTime() / 1000;
        long d1 = l2 - now;
        long d2 = l2 - l1;
        Double d = d1 * 100.0 / d2;
        int dd = 100 - d.intValue();
        if (dd > 100) {
            dd = 100;
        }
        if (dd < 0) {
            dd = 0;
        }
        return dd;
    }

    public static String getStr(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
    }

    public static String getStr(long delay) {
        long now = (DateUtils.now()).getTime();
        Date d = new Date(now + delay);
        return getStr(d);
    }

    public static String nowStr() {
        return getStr(DateUtils.now());
    }

    public static String nowStrYYYYMMddHHmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(DateUtils.now());
    }

    public static String nowStrYYYYMMddHHmmssSSS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(DateUtils.now());
    }

    public static String nowStrYYYYMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(DateUtils.now());
    }

    public static String nowStr2() {
        return getStr(new Date());
    }

    public static long nowTime() {
        Date d1 = DateUtils.now();
        return d1.getTime();
    }

    public static Date now() {
        return new Date();
    }

    public static Date getDate(String date) {
        Date d1 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d1 = sdf.parse(date);
        } catch (Exception ex) {
            log.error("错误信息为：", ex);
        }
        return d1;
    }

    public static Date getDateWithoutTIme(String date){
        Date d1 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            d1 = sdf.parse(date);
        } catch (Exception ex) {
            log.error("错误信息为：", ex);
        }
        return d1;
    }

    public static int getInt(double d) {
        return (new Double(d)).intValue();
    }

    public static boolean isNull(String in) {
        if (in == null || "".equals(in) || in.equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }

    // 如果为null返回""
    public static String nullToEmpty(String in) {
        if (in == null || "".equals(in) || in.equalsIgnoreCase("null")) {
            return "";
        } else {
            return in;
        }
    }

    // 如果为null返回null
    public static String emptyToNull(String in) {
        if (in == null || "".equals(in) || in.equalsIgnoreCase("null")) {
            return null;
        } else {
            return in;
        }
    }

    /**
     * 校正为本机时间
     *
     * @param dbDate 数据库时间
     * @return
     */
    public static Date adjust(Date dbDate) {
        long dbTime = nowTime();
        long before = dbDate.getTime();
        //
        long localTime = (new Date()).getTime();
        long after = before + (localTime - dbTime);
        //
        Date d1 = new Date(after);
        return d1;
    }

    public static String nowLocal() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String t = sdf.format(new Date());
        return t;
    }


    /**
     * 秒转化为天小时分秒字符串
     *
     * @param seconds
     * @return String
     */
    public static String formatSeconds(long seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分" + second + "秒";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时" + min + "分" + second + "秒";
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天" + hour + "小时" + min + "分" + second + "秒";
                }
            }
        }
        return timeStr;
    }

    //转化一天中的秒数
    public static int getSecondOfDay(String day) {
        String hour;
        String minute;
        String second;
        if (day.contains(":")) {
            hour = day.substring(0, 2);
            minute = day.substring(3, 5);
            second = "0";
        } else {
            hour = day.substring(6, 8);
            minute = day.substring(8, 10);
            second = day.substring(10, 12);
        }
        return Integer.parseInt(hour) * 60 * 60 + Integer.parseInt(minute) * 60 + Integer.parseInt(second);
    }

    /**
     * 将时间转成类似 2008-08-01 12:00:00 形式的时间格式.
     *
     * @author alexzhou
     */
    public static String translateMySQLTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }


    /**
     * 秒级时间戳转date字符串
     */
    public static String timestamp2DateStr(Long timestamp, SimpleDateFormat dateFormat) {
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    /**
     * 秒级date转时间戳
     */
    public static Long dateStr2Timestamp(String dateStr) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse(dateStr);
        return date.getTime() / 1000;
    }


    /**
     * 获取日期的时间戳
     */
    public static Timestamp getTimestamp(Date date) {
        return date == null ? null : new Timestamp(date.getTime());
    }

    /**
     * 获取指定时间的前几分钟
     */
    public static Date getDateBeforeMin(Date d, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - minute);
        return cal.getTime();
    }

    /**
     * 获取指定时间的后几分钟
     */
    public static Date getDateAfterMin(Date d, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minute);
        return cal.getTime();
    }

    /**
     * 获取指定时间的后几秒钟
     */
    public static Date getDateAfterSecond(Date d, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 获取指定时间的后几分钟
     */
    public static Date getDateAfterMin(Date d, double minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + (int) (minute * 60));
        return cal.getTime();
    }

    /**
     * 判断2个日期是否在同一天
      */

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     *  获取指定日期的指定时间
     */
    public static Date getGivenTimeOfGivenDate(Date date,Integer hour,Integer minute,Integer second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     *  Date类型日期转 LocalDate类型
     * */
    public static LocalDateTime date2LocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     *  LocalDateTime 类型日期转 Date类型
     * */
    public static Date localDateTime2Date(LocalDateTime dateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = dateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static String getOneHourLaterTimeFromNow(){
        final long ONE_HOUR_IN_MILLI_SECCONDS = 60*60*1000L;
        Long oneHourLaterFromCurrentTimeInMilliSeconds = System.currentTimeMillis()+ ONE_HOUR_IN_MILLI_SECCONDS;
        Date expireTime = new Date(oneHourLaterFromCurrentTimeInMilliSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(expireTime);
    }


}
