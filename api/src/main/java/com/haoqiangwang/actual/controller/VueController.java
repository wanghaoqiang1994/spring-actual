package com.haoqiangwang.actual.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vue")
public class VueController {
    private static final Logger logger = LoggerFactory.getLogger(VueController.class);

    @RequestMapping("/hello")
    public String hello(){
        logger.info("hello vue...");
        return "vue/hello";
    }
}
