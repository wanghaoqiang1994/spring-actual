package com.haoqiangwang.actual.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Description: 错误页面
* @Author:      haoqiangwang3
* @CreateDate:  2020/8/6
*/
@Controller
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping({"/404","/405","/500"})
    public String errorPage(HttpServletResponse response, HttpServletRequest request){
        int status = response.getStatus();
        String msg="";
        if (status==404) {
            msg="Not Found! 未找到，服务器找不到请求的网页";
        }
        if (status==405) {
            msg="METHOD_NOT_ALLOWED! 方法禁用，禁用请求中指定的方法";
        }
        if (status==500) {
            msg="Internal Server Error! 服务器内部错误，服务器遇到错误，无法完成请求";
        }

        request.setAttribute("status", status);
        request.setAttribute("message", msg);
        logger.info("统一错误页面跳转...");
        return "404";
    }

}
