package com.yaqiu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     *@Description 获取当日日期&&时间（yyyy-MM-dd HH:mm:ss）
     *@author CiaoLee
     */
    public static synchronized String getCurrentDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     *@Description 获取当日日期（yyyy-MM-dd）
     *@author CiaoLee
     */
    public static synchronized String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }
}
