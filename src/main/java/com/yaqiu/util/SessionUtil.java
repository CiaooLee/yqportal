package com.yaqiu.util;

import org.springframework.boot.web.servlet.server.Session;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static ThreadLocal<HttpSession> mySession = new ThreadLocal<>();;

    /**
     * @Description 从本地线程池获取session
     * @author CiaoLee
     */
    /*public static <T> T get() {
        return (T) threadLocal.get();
    }*/
    public static HttpSession get() {
        return mySession.get();
    }

    /**
     * @Description 将session存入本地线程池
     * @param
     * @author CiaoLee
     */
    /*public static <T extends Session> void put(T t) {
        threadLocal.set(t);
    }*/
    public static void set(HttpSession session) {
        mySession.set(session);
    }

    /**
     * @Description 销毁session
     * @author CiaoLee
     */
    public static void remove() {
        mySession.remove();
    }
}
