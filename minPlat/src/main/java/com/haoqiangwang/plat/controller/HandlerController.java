package com.haoqiangwang.plat.controller;

import com.haoqiangwang.plat.service.HandlerOneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class HandlerController {

    private static final Logger logger = LoggerFactory.getLogger(HandlerController.class);

    @Autowired
    private HandlerOneService handlerOneService;

    @RequestMapping(value = "/uploadHandlerOne")
    @ResponseBody
    public String handlerOne(@RequestParam("uploadFile") MultipartFile file, @RequestParam("saveFilePath") String saveFilePath){
        if(file.isEmpty()){
            return "file is empty";
        }

        if(StringUtils.isEmpty(saveFilePath)){
            return "saveFilePath is empty";
        }

        logger.info("保存文件的路径为：{}", saveFilePath);

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的文件名为：{}，后缀为：{}", fileName, suffixName);

        if(!suffixName.equals(".xlsx") && !suffixName.equals(".xls")){
            return "暂时只支持excel文件";
        }

        //设置保存新文件的路径地址
        String path = saveFilePath + fileName;
        try {
            File saveFile = new File(path);
            //检测是否存在目录
            if(saveFile.getParentFile().exists()){
                //创建新的文件夹
                saveFile.getParentFile().mkdir();
            }
            //文件写入
            file.transferTo(saveFile);
            handlerOneService.handlerOne(saveFile, suffixName);
        } catch (IOException e) {
            logger.error("文件保存失败，" + e);
            return "系统错误";
        }

        return "success";
    }
}
