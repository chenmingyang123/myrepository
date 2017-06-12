package com.guangxunet.shop.base.util;


import java.util.Calendar;
import java.util.Date;

/**
 * 处理日期的工具
 * Created by Administrator on 2016/8/28.
 */
public class DateUtil {
    /**
     * 把传入的时间设置为起始时间，把时分秒设置为00:00
     *
     * @param current
     * @return
     */
    public static Date getBeginDate(Date current) {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        return c.getTime();
    }

    /**
     * 把传入的结束时间设置为结束时间59：59
     *
     * @return
     */
    public static Date getEndDate(Date current) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    /**
     * 得到两个时间的间隔数，参数不分顺序
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getBetweenSecond(Date date1, Date date2) {
        return Math.abs((date1.getTime() - date2.getTime()) / 1000);
    }

    public static void main(String[] args) {
        System.out.println(getBeginDate(new Date()).toLocaleString());
        System.out.println(getEndDate(new Date()).toLocaleString());
    }
}


