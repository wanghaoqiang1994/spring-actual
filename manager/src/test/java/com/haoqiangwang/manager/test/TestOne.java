package com.haoqiangwang.manager.test;

import com.haoqiangwang.manager.ManagerApplication;
import com.haoqiangwang.manager.constant.ManagerConstant;
import com.haoqiangwang.service.common.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
public class TestOne {

    @Autowired
    private RedisService redisService;

    @Test
    public void add(){
        redisService.hset(ManagerConstant.LOGIN_ADMIN,"admin","admin");
        System.out.println("插入成功");
    }

    @Test
    public void permission(){
        redisService.sSet(ManagerConstant.USER_AUTHORITY + "15531257696","authority:register","authority:login","test:button");
        System.out.println("插入成功");
    }
}
