package com.haoqiangwang.service.deal;

import com.haoqiangwang.service.common.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMsgService {
    private static final Logger logger = LoggerFactory.getLogger(SendMsgService.class);

    @Autowired
    private RedisService redisService;

    public void sendMsg(String key,Object value){
        logger.info("向redis中存入信息...");

        if(redisService.push(key,value)){
            logger.info("存入信息成功...");
        }else{
            logger.error("存入信息失败！");
        }
    }
}
