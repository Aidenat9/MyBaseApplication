package com.youtu.myapplication;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/11 9:52
 *         包名：com.youtu.myapplication
 *         <p>description:            </p>
 */

public class TimeUtil {

    /**
     *
     * 描述:获取下一个月.
     *
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String preMonth = dft.format(cal.getTime());
        return preMonth;
    }
    /**
     * 获取任意时间的月的最后一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getMaxMonthDate(String repeatDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dftmm = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        try {
            if(!TextUtils.isEmpty(repeatDate) && !"null".equals(repeatDate)){
                calendar.setTime(dftmm.parse(repeatDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 获取当前时间
     *
     */
//    public static String getNowTime() {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
//        String lastMonth = dft.format(cal.getTime());
//        return lastMonth;
//    }


    /**
     * 获取当前月份最后一天
     */
//    public static String getNowMaxMonthDate() {
//        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        // calendar.add(Calendar.MONTH, -1);
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        return dft.format(calendar.getTime());
//    }

}
