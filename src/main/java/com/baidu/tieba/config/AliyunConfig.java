package com.baidu.tieba.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.yaml"})
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {
    private String endpoint;        //地域节点
    private String accessKeyId;     //访问id
    private String accessKeySecret; //访问秘钥
    private String bucketName;      //仓库名称
    private String urlPrefix;       //外网域名

    /**
     * 获取oss连接
     * @return
     */
    @Bean
    public OSS getOSSClient() {
        //创建一个ossclient对象
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        if(ossClient.doesBucketExist(bucketName)){
            System.out.println("bucket存在");
        }else {
            System.out.println("bucket不存在，创建新的bucket："+bucketName);
            CreateBucketRequest bucketRequest = new CreateBucketRequest(null);
            bucketRequest.setBucketName(bucketName);
            bucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(bucketRequest);
            ossClient.shutdown();
        }
        return ossClient;
    }
}
