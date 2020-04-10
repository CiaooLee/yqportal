package com.yaqiu.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static ThreadLocal mySession = new ThreadLocal();

    /**
     *@Description 从本地线程池获取session
     *@author CiaoLee
     */
    public static HttpSession getMySession() {
        HttpSession session = (HttpSession)mySession.get();
        return session;
    }

    /**
     *@Description 将session存入本地线程池
     *@author CiaoLee
     */
    public static void setMySession(HttpSession session) {
        mySession.set(session);
    }
}
