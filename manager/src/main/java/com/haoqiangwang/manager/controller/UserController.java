package com.haoqiangwang.manager.controller;

import com.haoqiangwang.domain.page.PageInfo;
import com.haoqiangwang.domain.user.UserDomain;
import com.haoqiangwang.service.manage.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @Description: 用户相关
* @Author:      haoqiangwang3
* @CreateDate:  2020/8/10
*/
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    public String getUserList(){
        return "user_list";
    }


    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<UserDomain> uerList(UserDomain userDomain){
        logger.info("接收到的limit为：{}，page为：{}，offset为：{}", userDomain.getLimit(),userDomain.getPage(),userDomain.getOffset());
        return userService.getUserList();
    }
}
