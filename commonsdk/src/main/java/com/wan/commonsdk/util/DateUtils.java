package com.wan.commonsdk.util;


import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by hubert on 2018/6/22.
 */
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String CDL_DATE_FORMAT = "dd MMM yyyy, h:mm a";
    public static final String CDL_TIME_FORMAT = "h:mm a";
    public static final String CDL_TIME_FORMAT1 = "hh:mm a";
    public static final String CDL_SINGLE_DATE_FORMAT = "dd MMM yyyy";
    public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd";
    public static final String HH_MM = "HH:mm";
    private static final String TZ_ID_BJ = "Asia/Shanghai";

    public static final String DEFAULT_DATE_FORMATA = "HH:mm:ss";


    public static String date2String(Date date, String format, Locale locale) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format, locale).format(date);
    }

    public static String date2String(Date date, String format) {
        return date2String(date, format, Locale.getDefault());
    }

    public static String date2DefaultString(Date date) {
        return date2String(date, DEFAULT_DATE_FORMAT);
    }

    public static Date defaultString2Date(String time) {
        return string2Date(time, DEFAULT_DATE_FORMAT);
    }

    public static Date string2Date(String time, String format) {
        return string2Date(time, format, null);
    }

    /**
     * @param time     string of time
     * @param format   such as yyyy-MM-dd
     * @param timeZone timeZone
     * @return date may be null
     */
    public static Date string2Date(String time, String format, @Nullable TimeZone timeZone) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            if (timeZone != null) {
                dateFormat.setTimeZone(timeZone);
            }
            return dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将本地Date格式化成北京时间
     *
     * @param date   本地Date
     * @param format 格式
     * @return 北京时间
     */
    public static String formatDateToBJString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone(TZ_ID_BJ));
        return dateFormat.format(date);
    }

    /**
     * 将北京时间转换成本地时间
     *
     * @param bjTime 北京时间
     * @param format 输入输出格式
     * @return 本地时间
     */
    public static String BJTimeToLocal(String bjTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date date = dateFormat.parse(bjTime);
            dateFormat.setTimeZone(TimeZone.getDefault());
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isSameYear(Calendar c1, Calendar c2) {
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        return year1 == year2;
    }

    public static boolean isSameMonth(Calendar c1, Calendar c2) {
        if (!isSameYear(c1, c2)) {
            return false;
        }
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        return month1 == month2;
    }

    public static boolean isSameDay(Calendar c1, Calendar c2) {
        if (!isSameMonth(c1, c2)) {
            return false;
        }
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return day1 == day2;
    }

    public static boolean isNextDay(Calendar c1, Calendar c2) {
        if (!isSameMonth(c1, c2)) {
            return false;
        }
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return day2 - day1 == 1;
    }

    public static int compare(Calendar c1, Calendar c2) {
        long c1TimeInMillis = c1.getTimeInMillis();
        long c2TimeInMillis = c2.getTimeInMillis();
        if (c1TimeInMillis < c2TimeInMillis) {
            return 1;
        } else if (c1TimeInMillis == c2TimeInMillis) {
            return 0;
        } else {
            return -1;
        }
    }

    public static String getWeekStr(Calendar c) {
        String week = null;
        int i = c.get(Calendar.DAY_OF_WEEK);
        if (i == Calendar.SUNDAY) {
            week = "Sun";
        } else if (i == Calendar.MONDAY) {
            week = "Mon";
        } else if (i == Calendar.TUESDAY) {
            week = "Tues";
        } else if (i == Calendar.WEDNESDAY) {
            week = "Wed";
        } else if (i == Calendar.THURSDAY) {
            week = "Thur";
        } else if (i == Calendar.FRIDAY) {
            week = "Fri";
        } else if (i == Calendar.SATURDAY) {
            week = "Sat";
        }
        return week;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    public static String getMonthEn(int month) {
        String result = null;
        switch (month) {
            case 0:
                result = "Jan";
                break;
            case 1:
                result = "Feb";
                break;
            case 2:
                result = "Mar";
                break;
            case 3:
                result = "Apr";
                break;
            case 4:
                result = "May";
                break;
            case 5:
                result = "Jun";
                break;
            case 6:
                result = "Jul";
                break;
            case 7:
                result = "Aug";
                break;
            case 8:
                result = "Sep";
                break;
            case 9:
                result = "Oct";
                break;
            case 10:
                result = "Nov";
                break;
            case 11:
                result = "Dec";
                break;
        }
        return result;
    }

    public static String getTimeString(int hourOfDay, int minute) {
        StringBuilder sb = new StringBuilder();
        if (hourOfDay >= 12) {
            sb.append(hourOfDay - 12);
        } else {
            sb.append(hourOfDay);
        }
        sb.append(":");
        if (String.valueOf(minute).length() < 2) {
            sb.append("0");
        }
        sb.append(minute);
        if (hourOfDay >= 12) {
            sb.append("  PM");
        } else {
            sb.append("  AM");
        }
        return sb.toString();
    }

    public static String getDurationInDay(long ms) {
        long seconds = ms / 1000;
        if (seconds < 60) {
            return "";
        }
        long minutes = seconds / 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        if (hours <= 0) {
            return "0.5 hour(s)";
        } else if (minutes == 0) {
            return hours + " hour(s)";
        } else {
            return hours + ".5 hour(s)";
        }
    }

    public static double getHour(long ms) {
        long seconds = ms / 1000;
        if (seconds < 60) {
            return 0;
        }
        long minutes = seconds / 60;
        return minutes / 60d;
    }

    public static String getMessageTime(String original) {
        Date targetDate = defaultString2Date(original);
        Calendar today = Calendar.getInstance();
        Calendar targetC = Calendar.getInstance();
        targetC.setTime(targetDate);
        if (isSameDay(today, targetC)) {
            return date2String(targetDate, CDL_TIME_FORMAT);
        }
        if (isSameYear(today, targetC)) {
            return date2String(targetDate, "dd MMM");
        }
        return date2String(targetDate, "dd MMM yyyy");
    }

    public static Date getAfterTime(Date date, long afterTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Date(calendar.getTimeInMillis() + afterTime);
    }
}
