package com.haoqiangwang.manager.controller;

import com.haoqiangwang.manager.constant.ManagerConstant;
import com.haoqiangwang.service.manage.IndexService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
* @Description: 通用入口
* @Author:      haoqiangwang3
* @CreateDate:  2020/8/7
*/

@Controller
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private IndexService indexService;

    /**
     * 首页，默认登陆页
     * @return
     */
    @RequestMapping({"/","index"})
    public String index(){
        logger.info("进入到请求...");
        return "success";
    }

    /**
     * 成功页面
     * @return
     */
    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    /**
     * 失败页面
     * @return
     */
    @RequestMapping("/fail")
    public String fail(){
        return "fail";
    }

    @RequestMapping("/unAuthorized")
    public String unAuthorized(){
        return "unAuthorized";
    }

    /**
     * 进入注册页面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        logger.info("进入到注册页面...");
        return "register";
    }

    /**
     * 确认注册
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping("/registerSure")
    @ResponseBody
    public String registerSure(@RequestParam String userId, @RequestParam String password){
        logger.info("传进来的用户名为：{}，密码为：{}",userId, password);
        //注册成功
        if(indexService.register(ManagerConstant.LOGIN_USER,userId, password)){
            return "{\"flag\":\"success\"}";
        }else{
            //注册失败
            return "{\"flag\":\"fail\"}";
        }
    }

    @GetMapping("/login")
    public String login(){
        logger.info("登录页面...");
        return "login";
    }

    /**
     * 登陆
     * @param
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                        @RequestParam(value = "kind") String kind, Model model){
        logger.info("登陆的用户名为：{}，密码为：{}，类型为{}", username, password, kind);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            //shiro帮我们匹配密码什么的，我们只需要把东西传给它，它会根据我们在Realm里认证方法设置的来验证
            subject.login(token);
            return "redirect:/success";
        }catch (Exception e){
            logger.info("出现错误，",e);
        }

        /*if(type.equals("admin")){
            logger.info("管理员登陆...");
            indexService.login(ManagerConstant.LOGIN_ADMIN,username,password);
        }else{
            logger.info("普通用户登陆...");
            indexService.login(ManagerConstant.LOGIN_USER,username,password);
        }*/
        return "login";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        logger.info("退出登录");
        SecurityUtils.getSubject().logout();
        return "login";
    }
}
