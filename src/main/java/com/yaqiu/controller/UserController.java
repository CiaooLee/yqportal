package com.yaqiu.controller;

import com.yaqiu.service.UserService;
import com.yaqiu.util.ObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.yaqiu.constant.GlobalConstant.ADMIN_PASSWORD;
import static com.yaqiu.constant.GlobalConstant.ADMIN_USERNAME;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * @Description 获取DomainColumns
     * @author CiaoLee
     */
    @PostMapping("adminLogin")
    public String adminLogin(String username, String password) {
        if(ObjectUtil.isEmpty(username) || ObjectUtil.isEmpty(password)) return "redirect:background";
        if(ADMIN_USERNAME.equals(username)) {
            if(!ADMIN_PASSWORD.equals(password)) return "redirect:background";
        }
        return "redirect:/backgroundIndex";
    }
}
