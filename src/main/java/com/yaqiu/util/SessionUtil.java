package com.yaqiu.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static ThreadLocal<HttpSession> threadLocal = new ThreadLocal<>();;

    /**
     * @Description 从本地线程池获取session
     * @author CiaoLee
     */
    public static HttpSession get() {
        return threadLocal.get();
    }

    /**
     * @Description 将session存入本地线程池
     * @param session 会话对象
     * @author CiaoLee
     */
    public static void set(HttpSession session) {
        threadLocal.set(session);
    }

    /**
     * @Description 销毁session
     * @author CiaoLee
     */
    public static void remove() {
        threadLocal.remove();
    }
}
