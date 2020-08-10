package com.haoqiangwang.service.manage;

import com.haoqiangwang.service.common.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    private static final Logger logger = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    private RedisService redisService;

    /**
     * 登陆
     * @param key
     * @param userId
     * @param password
     */
    public void login(String key, String userId, String password){

        Object pass = redisService.hget(key, userId);
        if(null == pass){
            logger.info("该用户不存在...");
            return;
        }

        if(!pass.toString().equals(password)){
            logger.info("该用户密码错误...");
            return;
        }
    }

    /**
     * 注册
     * @param key
     * @param userId
     * @param password
     * @return
     */
    public boolean register(String key,String userId, String password){
        if(redisService.hHasKey(key, userId)){
            logger.info("该用户已存在...");
            return false;
        }
        return redisService.hset(key,userId,password);
    }
}
