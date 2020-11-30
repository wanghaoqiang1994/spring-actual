package com.haoqiangwang.plat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping(value = "/plat")
public class PlatController {
    private static final Logger logger = LoggerFactory.getLogger(PlatController.class);

    @RequestMapping({"/","/index"})
    public String index(Model model){
        logger.info("进入到index页面...");
        model.addAttribute("time", new Date());
        model.addAttribute("message", "欢迎");
        return "index";
    }

    @RequestMapping("/handlerOne")
    public String login(){
        return "handle/handlerOne";
    }
}
