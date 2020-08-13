package com.haoqiangwang.actual.config;

import com.haoqiangwang.actual.constant.ApiConstant;
import com.haoqiangwang.service.common.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
* @Description: 启动即执行
* @Author:      haoqiangwang3
* @CreateDate:  2020/8/6
*/
@Component
public class StartConfig implements ServletContextAware {
    private static final Logger logger = LoggerFactory.getLogger(StartConfig.class);

    @Value("${redisMq.flag}")
    private String flag;

    @Autowired
    private RedisService redisService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        logger.info("项目启动成功后执行...");
        logger.info("是否启动redisMq功能：{}", flag);
        if(ApiConstant.REDIS_MQ_START.equals(flag)) {
            //这需要另起一个线程，否则会阻碍主线程加载应用
            Thread start = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            logger.info("另起一个线程循环取redis中的值...");
                            while (true) {
                                Object msg = redisService.pop(ApiConstant.API_MQ_KEY);
                                if (msg == null) {
                                    try {
                                        Thread.sleep(10000);
                                        logger.debug("暂时没有信息，休息一下...");
                                    } catch (InterruptedException e) {
                                        logger.error("启动sleep时发生错误！", e);
                                    }
                                } else {
                                    logger.info("获取的内容为：{}", msg);
                                }
                            }
                        }
                    });
            start.start();
        }

    }
}
