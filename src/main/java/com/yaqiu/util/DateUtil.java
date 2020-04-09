package com.yaqiu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     *@Description 获取当前日期&&时间（yyyy-MM-dd HH:mm:ss）
     *@author CiaoLee
     */
    public static synchronized String getCurrentDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
