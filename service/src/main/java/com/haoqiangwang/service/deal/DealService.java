package com.haoqiangwang.service.deal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    public String getMsg(){
        logger.info("进入到service层处理...");
        return "hello";
    }

}
