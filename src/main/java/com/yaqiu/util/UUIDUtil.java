package com.yaqiu.util;

import com.yaqiu.constant.GlobalConstant;

import java.util.UUID;

public class UUIDUtil {
    /**
     *@Description 生成随机8位UUID
     *@author CiaoLee
     */
    public static synchronized String getUUID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(GlobalConstant.ALPHABET[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     *@Description 生成随机游客UUID
     *@author CiaoLee
     */
    public static synchronized String getVisitorUUID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 6; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(GlobalConstant.ALPHABET[x % 0x3E]);
        }
        return "游客" + shortBuffer.toString();
    }
}
