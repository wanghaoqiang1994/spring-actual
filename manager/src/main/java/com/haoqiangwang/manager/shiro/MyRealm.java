package com.haoqiangwang.manager.shiro;

import com.haoqiangwang.manager.constant.ManagerConstant;
import com.haoqiangwang.service.common.RedisService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private RedisService redisService;

    /**
     * 权限控制
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户输入的账号
        String userNo = (String) getAvailablePrincipal(principalCollection);
        logger.info("权限控制获取的输入账号为：{}", userNo);

        //根据账号查找所拥有的的权限
        Set<Object> authorities =  redisService.sGet(ManagerConstant.USER_AUTHORITY + userNo);
        if(null == authorities){
            logger.info("没有查询到拥有的权限");
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Iterator<Object> it = authorities.iterator();
        while(it.hasNext()){
            String authority = (String) it.next();
            logger.info(authority);
            authorizationInfo.addStringPermission(authority);
        }
        return authorizationInfo;
    }

    /**
     * 身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户输入的账号
        String userName = authenticationToken.getPrincipal().toString();
        logger.info("身份验证获取的输入账号为：{}", userName);
        //可以通过输入的账号进行数据查询
        String userPassword =  redisService.hget(ManagerConstant.LOGIN_USER,userName).toString();
        if(null == userPassword){
            logger.info("没有此用户，认证失败！");
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, userPassword, getName());
        return authenticationInfo;
    }
}
