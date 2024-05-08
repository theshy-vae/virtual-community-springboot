package com.baidu.tieba.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;

    /**
     * 上传图片（头像）
     */
    @RequestMapping("/imgUpload")
    public ApiResult<String> imgUpload(@RequestParam("file") MultipartFile uploadFile){
        String url = fileUploadService.uploadImg(uploadFile);
        if(url==null) return ApiResult.failed("上传失败,请检查格式");
        return ApiResult.success(url);
    }

    /**
     * 上传视频
     */
    @RequestMapping("/videoUpload")
    public ApiResult<String> videoUpload(@RequestParam("file") MultipartFile uploadFile){
        String url = fileUploadService.uploadVideo(uploadFile);
        if(url==null) return ApiResult.failed("上传失败,请检查格式");
        return ApiResult.success(url);
    }

    /**
     * 上传图片（帖子中的图片）
     */
    @RequestMapping("/editor/upload")
    public JSON editorUpload(MultipartFile file){
        String url = fileUploadService.uploadImg(file);
        JSONObject json=new JSONObject();
        json.set("errno",0);
        JSONArray arr=new JSONArray();
        JSONObject data=new JSONObject();
        data.set("url",url);
        arr.add(data);
        json.set("data",arr);
        return json;
    }


}
