package com.baidu.tieba.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.baidu.tieba.config.AliyunConfig;
import com.baidu.tieba.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    //设置允许的上传格式
    private static final String[] IMAGE_TYPE=new String[]{".bmp",".jpg","jpeg",".gif",".png"};
    @Autowired
    private AliyunConfig aliyunConfig;

    @Override
    public String uploadImg(MultipartFile uploadFile) {
        //检验图片格式
        boolean isLegal=false;
        //文件后缀
        String ext = null;
        //文件访问路径
        String url=null;
        for(String type:IMAGE_TYPE){
            //检查是否文件名是以指定的后缀结尾
            if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),type)){
                isLegal=true;
                ext=type;
                break;
            }
        }
        if(!isLegal) return null;
        //文件路径
        String fileName=uploadFile.getOriginalFilename();
        String filePath=getImgFilePath(ext);
        //上传到阿里云
        OSS ossClient = aliyunConfig.getOSSClient();
        try {
            ossClient.putObject(aliyunConfig.getBucketName(),filePath,new
                    ByteArrayInputStream(uploadFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        url=aliyunConfig.getUrlPrefix()+filePath;
        return url;
    }

    /**
     * 上传视频文件
     */
    @Override
    public String uploadVideo(MultipartFile uploadFile) {
        //检验视频格式
        boolean isLegal=false;
        //文件后缀
        String ext = null;
        //文件访问路径
        String url=null;
        if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),".mp4")){
            isLegal=true;
            ext=".mp4";
        }
        if(!isLegal) return null;
        //文件路径
        String fileName=uploadFile.getOriginalFilename();
        String filePath=getVideoFilePath(ext);
        //上传到阿里云
        OSS ossClient = aliyunConfig.getOSSClient();
        try {
            ossClient.putObject(aliyunConfig.getBucketName(),filePath,new
                    ByteArrayInputStream(uploadFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        url=aliyunConfig.getUrlPrefix()+filePath;
        return url;
    }



    /**
     * 生成图片路径及文件名
     */
    private String getImgFilePath(String ext){
        DateTime dateTime = new DateTime();
        return "images/"+dateTime.toString("yyyy")+"/"+dateTime.toString("MM")
                +"/"+dateTime.toString("dd")+"/"+ UUID.randomUUID().toString().replace("-","")
                +ext;
    }

    /**
     * 生成视频路径及文件名
     */
    private String getVideoFilePath(String ext){
        DateTime dateTime = new DateTime();
        return "videos/"+dateTime.toString("yyyy")+"/"+dateTime.toString("MM")
                +"/"+dateTime.toString("dd")+"/"+ UUID.randomUUID().toString().replace("-","")
                +ext;
    }

    /**
     * 删除文件
     * @param oldUrl
     */
    @Override
    public void delete(String oldUrl) {
        if(!("".equals(oldUrl)||oldUrl==null)){
            for(int i = 0; i < 3; i++){
                oldUrl = oldUrl.substring(oldUrl.indexOf("/")+1 );
            }
            String name = oldUrl;
            OSS ossClient = aliyunConfig.getOSSClient();
            ossClient.deleteObject(aliyunConfig.getBucketName(),name);
        }
    }
}
