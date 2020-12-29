package com.osyangxin.moji.common.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

    /**
     * 计算两个时间相差多少个年
     */
    public static int yearsBetween(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
    }

    /**
     * 计算两个时间相差多少个月
     *
     * @return int
     */
    public static int monthsBetween(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);
        int result = yearsBetween(start, end) * 12 + endDate.get(Calendar.MONTH) - startDate
            .get(Calendar.MONTH);
        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 计算两个时间相差多少个天
     */
    public static int daysBetween(Date start, Date end) {
        // 得到两个日期相差多少天
        return hoursBetween(start, end) / 24;
    }

    /**
     * 计算两个时间相差多少小时
     */
    public static int hoursBetween(Date start, Date end) {
        // 得到两个日期相差多少小时
        return minutesBetween(start, end) / 60;
    }

    /**
     * 计算两个时间相差多少分
     */
    public static int minutesBetween(Date start, Date end) {
        // 得到两个日期相差多少分
        return secondsBetween(start, end) / 60;
    }

    /**
     * 计算两个时间相差多少秒
     */
    public static int secondsBetween(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);

        return ((int) (endDate.getTime().getTime() / 1000) - (int) (startDate.getTime().getTime()
            / 1000));
    }

    /**
     * 计算两个时间相差多少秒
     */
    public static long milliSecondsBetween(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);

        return (endDate.getTime().getTime() - startDate.getTime().getTime());
    }

    public static Date addYear(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date beginOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取当日凌晨
     */
    public static Calendar beginOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    /**
     *
     */
    public static Calendar beginOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }


    public static void main(String[] args) {
        System.out.println(addYear(new Date(), 1));
    }

}
