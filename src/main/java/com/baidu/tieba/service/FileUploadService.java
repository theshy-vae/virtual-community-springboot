package com.baidu.tieba.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService{
    //图片文件上传
    String uploadImg(MultipartFile uploadFile);

    //视频文件上传
    String uploadVideo(MultipartFile uploadFile);

    //文件删除
    void delete(String oldUrl);

}
