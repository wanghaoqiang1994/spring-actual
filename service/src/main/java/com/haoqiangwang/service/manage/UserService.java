package com.haoqiangwang.service.manage;

import com.alibaba.fastjson.JSONObject;
import com.haoqiangwang.domain.page.PageInfo;
import com.haoqiangwang.domain.user.UserDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public PageInfo<UserDomain> getUserList(){
        UserDomain user1 = new UserDomain();
        user1.setId("1");
        user1.setAge("22");
        user1.setName("张三");

        UserDomain user2 = new UserDomain();
        user2.setId("2");
        user2.setAge("25");
        user2.setName("李四");

        List<UserDomain> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal( new Integer(2));
        pageInfo.setRows(list);

        logger.info("获取到的用户信息为{}", JSONObject.toJSON(pageInfo));
        return pageInfo;
    }
}
