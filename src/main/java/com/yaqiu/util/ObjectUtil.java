package com.yaqiu.util;

import java.util.List;
import java.util.Map;

public class ObjectUtil {
    /**
     *@Description 判断对象是否存在/为空
     *@author CiaoLee
     */
    public static boolean isEmpty(Object object) {
        /* 如果为object为null 直接为空 */
        if(object == null) return true;
        /* 如果对象存在 则获取对象的数据类型 进行不同种类的判空 */
        try {
            String className = object.getClass().getName();
            //若是字符串 则需要判断是否为空字符串
            if(className.contains("String")) {
                String realObject = (String)object;
                if("".equals(realObject)) return true;
            }
            //若是Map 则需要判断size是否为0
            if(className.contains("Map")) {
                Map realObject = (Map)object;
                if(realObject.size() == 0) return true;
            }
            //若是List 则需要判断size是否为0
            if(className.contains("List")) {
                List realObject = (List)object;
                if(realObject.size() == 0) return true;
            }
        } catch (Exception e) {
            System.err.println(object.toString() + "|对象判空出错");
        }

        return false;
    }

    /**
     *@Description 判断对象是否存在/为空
     *@author CiaoLee
     */
    public static boolean isNotEmpty(Object object) {
        /* 如果为object为null 直接为空 */
        if(object != null) return true;
        /* 如果对象存在 则获取对象的数据类型 进行不同种类的判空 */
        try{
            String className = object.getClass().getName();
            //若是字符串 则需要判断是否为空字符串
            if(className.contains("String")) {
                String realObject = (String)object;
                if(realObject.length() >= 1) return true;
            }
            //若是Map 则需要判断size是否为0
            if(className.contains("Map")) {
                Map realObject = (Map)object;
                if(realObject.size() >=1) return true;
            }
            //若是List 则需要判断size是否为0
            if(className.contains("List")) {
                List realObject = (List)object;
                if(realObject.size() >= 1) return true;
            }
        } catch(Exception e) {
            System.err.println(object.toString() + "|对象判不空出错");
        }
        return false;
    }

    /**
     *@Description 判断对象数值是否等于0
     *@author CiaoLee
     */
    public static boolean isZero(Object object) {
        object = object.toString();
        return "0".equals(object) || "0.0".equals(object);
    }

    /**
     *@Description 判断对象是否不等于0
     *@author CiaoLee
     */
    public static boolean isNotZero(Object object) {
        object = object.toString();
        return !"0".equals(object) || !"0.0".equals(object);
    }
}
