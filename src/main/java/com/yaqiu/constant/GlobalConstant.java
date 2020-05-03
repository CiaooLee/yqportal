package com.yaqiu.constant;

/**
 * @Description 全局变量-常量类
 * @author CiaoLee
 */
public class GlobalConstant {
    /* [OPERATION_LOG表]的[TYPE]字段 */
    public static final Byte PAGE_VISIT_OPERATION_LOG_TYPE = 0; //"页面访问"类型
    public static final Byte SEND_REQUEST_OPERATION_LOG_TYPE = 1; //"发送请求"类型

    /* 后台管理员账号密码 */
    public static final String ADMIN_USERNAME = "adminyxn";
    public static final String ADMIN_PASSWORD = "996815yt";

    /* ajax返回信息 */
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    /* Domain页面分页信息 */
    public static final Integer PAGE_SIZE = 12;

    /* status状态 */
    public static final Byte ACTIVE_STATUS = 1;
    public static final Byte INACTIVE_STATUS = 0;
}