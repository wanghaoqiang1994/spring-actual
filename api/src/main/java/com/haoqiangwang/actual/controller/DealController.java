package com.haoqiangwang.actual.controller;

import com.haoqiangwang.actual.constant.ApiConstant;
import com.haoqiangwang.service.deal.SendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/deal")
public class DealController {

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @Autowired
    private SendMsgService sendMsgService;

    @RequestMapping("/sendMsg")
    public String sendMsg(HttpServletRequest httpServletRequest){
        logger.info("调用发送消息方法...");
        String userId = httpServletRequest.getParameter("user_id");
        String userName = httpServletRequest.getParameter("user_name");
        String context = httpServletRequest.getParameter("send_context");
        logger.info("上送的用户id为：{}，用户名为：{}，信息为：{}",userId,userName,context);

        sendMsgService.sendMsg(ApiConstant.API_MQ_KEY,context);
        return "index";
    }

}
