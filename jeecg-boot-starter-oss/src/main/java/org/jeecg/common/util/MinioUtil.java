package org.jeecg.common.util;

import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.util.filter.SsrfFileTypeFilter;
import org.jeecg.common.util.filter.StrAttackFilter;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLDecoder;

/**
 * MinIO文件上传工具类（向后兼容）
 * 
 * @deprecated 请使用 {@link org.jeecg.common.oss.OssService} 接口
 * @author jeecg-boot
 */
@Slf4j
@Deprecated
public class MinioUtil {
    
    private static String minioUrl;
    private static String minioName;
    private static String minioPass;
    private static String bucketName;
    private static MinioClient minioClient = null;

    public static void setMinioUrl(String minioUrl) {
        MinioUtil.minioUrl = minioUrl;
    }

    public static void setMinioName(String minioName) {
        MinioUtil.minioName = minioName;
    }

    public static void setMinioPass(String minioPass) {
        MinioUtil.minioPass = minioPass;
    }

    public static void setBucketName(String bucketName) {
        MinioUtil.bucketName = bucketName;
    }

    public static String getMinioUrl() {
        return minioUrl;
    }

    public static String getBucketName() {
        return bucketName;
    }

    /**
     * 上传文件
     * @deprecated 请使用 OssService.upload() 方法
     */
    @Deprecated
    public static String upload(MultipartFile file, String bizPath, String customBucket) throws Exception {
        String fileUrl = "";
        bizPath = StrAttackFilter.filter(bizPath);
        SsrfFileTypeFilter.checkUploadFileType(file, bizPath);

        String newBucket = bucketName;
        if(oConvertUtils.isNotEmpty(customBucket)){
            newBucket = customBucket;
        }
        
        try {
            initMinio(minioUrl, minioName, minioPass);
            
            if(minioClient.bucketExists(BucketExistsArgs.builder().bucket(newBucket).build())) {
                log.info("Bucket already exists.");
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(newBucket).build());
                log.info("create a new bucket.");
            }
            
            InputStream stream = file.getInputStream();
            String orgName = file.getOriginalFilename();
            if("".equals(orgName)){
                orgName = file.getName();
            }
            orgName = CommonUtils.getFileName(orgName);
            
            String objectName = bizPath + "/"
                    + (orgName.indexOf(".") == -1
                       ? orgName + "_" + System.currentTimeMillis()
                       : orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.lastIndexOf(".")));

            if(objectName.startsWith(SymbolConstant.SINGLE_SLASH)){
                objectName = objectName.substring(1);
            }
            
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .object(objectName)
                    .bucket(newBucket)
                    .contentType("application/octet-stream")
                    .stream(stream, stream.available(), -1)
                    .build();
            
            minioClient.putObject(objectArgs);
            stream.close();
            fileUrl = minioUrl + newBucket + "/" + objectName;
            
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return fileUrl;
    }

    /**
     * 文件上传
     * @deprecated 请使用 OssService.upload() 方法
     */
    @Deprecated
    public static String upload(MultipartFile file, String bizPath) throws Exception {
        return upload(file, bizPath, null);
    }

    /**
     * 获取文件流
     * @deprecated 请使用 OssService.getFile() 方法
     */
    @Deprecated
    public static InputStream getMinioFile(String bucketName, String objectName){
        InputStream inputStream = null;
        try {
            initMinio(minioUrl, minioName, minioPass);
            GetObjectArgs objectArgs = GetObjectArgs.builder()
                    .object(objectName)
                    .bucket(bucketName)
                    .build();
            inputStream = minioClient.getObject(objectArgs);
        } catch (Exception e) {
            log.info("文件获取失败" + e.getMessage());
        }
        return inputStream;
    }

    /**
     * 删除文件
     * @deprecated 请使用 OssService.delete() 方法
     */
    @Deprecated
    public static void removeObject(String bucketName, String objectName) {
        try {
            initMinio(minioUrl, minioName, minioPass);
            RemoveObjectArgs objectArgs = RemoveObjectArgs.builder()
                    .object(objectName)
                    .bucket(bucketName)
                    .build();
            minioClient.removeObject(objectArgs);
        } catch (Exception e){
            log.info("文件删除失败" + e.getMessage());
        }
    }

    /**
     * 获取文件外链
     * @deprecated 请使用 OssService.getObjectUrl() 方法
     */
    @Deprecated
    public static String getObjectUrl(String bucketName, String objectName, Integer expires) {
        initMinio(minioUrl, minioName, minioPass);
        try{
            GetPresignedObjectUrlArgs objectArgs = GetPresignedObjectUrlArgs.builder()
                    .object(objectName)
                    .bucket(bucketName)
                    .expiry(expires)
                    .method(Method.GET)
                    .build();
            String url = minioClient.getPresignedObjectUrl(objectArgs);
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e){
            log.info("文件路径获取失败" + e.getMessage());
        }
        return null;
    }

    /**
     * 上传文件到MinIO
     * @deprecated 请使用 OssService.upload() 方法
     */
    @Deprecated
    public static String upload(InputStream stream, String relativePath) throws Exception {
        initMinio(minioUrl, minioName, minioPass);
        
        if(minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            log.info("Bucket already exists.");
        } else {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            log.info("create a new bucket.");
        }
        
        PutObjectArgs objectArgs = PutObjectArgs.builder()
                .object(relativePath)
                .bucket(bucketName)
                .contentType("application/octet-stream")
                .stream(stream, stream.available(), -1)
                .build();
        
        minioClient.putObject(objectArgs);
        stream.close();
        return minioUrl + bucketName + "/" + relativePath;
    }

    /**
     * 初始化客户端
     */
    private static MinioClient initMinio(String minioUrl, String minioName, String minioPass) {
        if (minioClient == null) {
            try {
                minioClient = MinioClient.builder()
                        .endpoint(minioUrl)
                        .credentials(minioName, minioPass)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return minioClient;
    }
}