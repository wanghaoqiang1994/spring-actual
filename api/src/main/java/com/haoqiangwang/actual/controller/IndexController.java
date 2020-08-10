package com.haoqiangwang.actual.controller;

import com.haoqiangwang.service.deal.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private DealService dealService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/test")
    public @ResponseBody String test(){
        logger.info("访问...");
        return dealService.getMsg();
    }


    /** 获取页面地址展示 **/
    @RequestMapping("/getSendMsg")
    public String sendMsg(){
        return "sendMsg";
    }

}
