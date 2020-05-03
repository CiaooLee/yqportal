package com.yaqiu.service.impl;

import com.yaqiu.mapper.UserMapper;
import com.yaqiu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


}
