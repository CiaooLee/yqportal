package com.yaqiu.controller;

import com.yaqiu.constant.GlobalConstant;
import com.yaqiu.util.DateUtil;
import com.yaqiu.util.RedisUtil;
import com.yaqiu.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ThymeleafController {
    @Resource
    private RedisUtil redisUtil;

    /**
     * @Description 前台-首页跳转映射
     * @author CiaoLee
     */
    @RequestMapping("/")
    public String toPortal() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中获取deviceType */
        //String deviceType = (String)session.getAttribute("deviceType");
        /* 将[OPERATION_LOG]日志信息存入session 交给[VisitorLogInterceptor]类的afterComplement方法处理 */
        session.setAttribute("operationLogType", GlobalConstant.PAGE_VISIT_OPERATION_LOG_TYPE);
        session.setAttribute("operationLogContent", "访问了[首页]");
        session.setAttribute("operationLogCreateTime", DateUtil.getCurrentDateTime());
        /* 如果访问者使用手机访问 则跳至手机主页 */
        //if("Mobile".equals(deviceType)) return "foreground/mobile/index.html";
        /* 如果访问者使用电脑 或者不明类型设备访问 则跳至通用主页 */
        return "foreground/general/index.html";
    }

    /**
     * @Description 前台-各面板跳转映射
     * @param type 从页面传过来的类型
     * @param map 向页面传送数据
     * @author CiaoLee
     */
    @RequestMapping("domain")
    public String toDomain(String type, ModelMap map) {
        /* 初始化返回参数 */
        map.addAttribute("identifier", type);
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中获取deviceType */
        //String deviceType = (String)session.getAttribute("deviceType");
        /* 将[OPERATION_LOG]日志信息存入session 交给[VisitorLogInterceptor]类的afterComplement方法处理 */
        session.setAttribute("operationLogType", GlobalConstant.PAGE_VISIT_OPERATION_LOG_TYPE);
        session.setAttribute("operationLogContent", "访问了板块["+type+"]");
        session.setAttribute("operationLogCreateTime", DateUtil.getCurrentDateTime());
        //if("Mobile".equals(deviceType)) return "foreground/mobile/domain/index.html";
        /* 如果访问者使用电脑 或者不明类型设备访问 则跳至通用主页 */
        return "foreground/general/domain/index.html";
    }

    /**
     * @Description 前台-“帖子详情”跳转映射
     * @author CiaoLee
     */
    @RequestMapping("content")
    public String toContent(String id, ModelMap map) {
        /* 初始化返回参数 */
        map.addAttribute("contentId", id);
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中获取deviceType */
        //String deviceType = (String)session.getAttribute("deviceType");
        /* 组成LogContent */
        String title = id;
        if(redisUtil.hHasKey("contents", id)) {
            Map content = (Map)redisUtil.hget("contents", id);
            title = (String)content.get("title");
        }
        /* 将[OPERATION_LOG]日志信息存入session 交给[VisitorLogInterceptor]类的afterComplement方法处理 */
        session.setAttribute("operationLogType", GlobalConstant.PAGE_VISIT_OPERATION_LOG_TYPE);
        session.setAttribute("operationLogContent", "查看了文章["+ title +"]");
        session.setAttribute("operationLogCreateTime", DateUtil.getCurrentDateTime());
        //if("Mobile".equals(deviceType)) return "foreground/mobile/domain/content.html";
        /* 如果访问者使用电脑 或者不明类型设备访问 则跳至通用主页 */
        return "foreground/general/domain/content.html";
    }

    /**
     * @Description 前台-“联系我们”跳转映射
     * @author CiaoLee
     */
    @RequestMapping("contactUs")
    public String toContactUs() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中获取deviceType */
        //String deviceType = (String)session.getAttribute("deviceType");
        /* 将[OPERATION_LOG]日志信息存入session 交给[VisitorLogInterceptor]类的afterComplement方法处理 */
        session.setAttribute("operationLogType", GlobalConstant.PAGE_VISIT_OPERATION_LOG_TYPE);
        session.setAttribute("operationLogContent", "访问了[联系我们]");
        session.setAttribute("operationLogCreateTime", DateUtil.getCurrentDateTime());
        //if("Mobile".equals(deviceType)) return "foreground/mobile/document/contact-us.html";
        /* 如果访问者使用电脑 或者不明类型设备访问 则跳至通用主页 */
        return "foreground/general/document/contact-us.html";
    }

    /**
     * @Description 后台-登录
     * @author CiaoLee
     */
    @RequestMapping("background")
    public String toBackground() {
        return "background/login.html";
    }

    /**
     * @Description 后台-主页
     * @author CiaoLee
     */
    @RequestMapping("backgroundIndex")
    public String toBackgroundIndex() {
        return "background/main/index.html";
    }

    /**
     * @Description 后台-表格
     * @author CiaoLee
     */
    @RequestMapping("backgroundForm")
    public String toBackgroundForm() {
        return "background/main/form.html";
    }
}
