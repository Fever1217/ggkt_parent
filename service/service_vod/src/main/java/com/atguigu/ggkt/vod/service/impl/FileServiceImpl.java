package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.vod.service.FileService;
import com.atguigu.ggkt.vod.utils.ConstantPropertiesUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    //文件上传
    @Override
    public String upload(MultipartFile file) {
        // 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(ConstantPropertiesUtil.END_POINT);
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
// 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
// 对象键(Key)是对象在存储桶中的唯一标识。
        //文件名称前加上uuid值
        String key = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();
        //对上传文件分组，根据当前日期
        String dateTime = new DateTime().toString("yyyy/MM/dd");
        key = dateTime+"/"+key;


        try {
            // 高级接口会返回一个异步结果Upload
            // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
            InputStream inputStream = file.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName
                    , key,
                    inputStream,
                    objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            String url = "https://"+bucketName+"."+"cos"+"."+ConstantPropertiesUtil.END_POINT+".myqcloud.com"+"/"+key;
            return url;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
