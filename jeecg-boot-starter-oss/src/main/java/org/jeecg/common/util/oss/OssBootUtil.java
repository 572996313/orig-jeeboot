package org.jeecg.common.util.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemStream;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.filter.SsrfFileTypeFilter;
import org.jeecg.common.util.filter.StrAttackFilter;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云OSS上传工具类（向后兼容）
 * 
 * @deprecated 请使用 {@link org.jeecg.common.oss.OssService} 接口
 * @author jeecg-boot
 */
@Slf4j
@Deprecated
public class OssBootUtil {

    private static String endPoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    private static String staticDomain;
    private static OSSClient ossClient = null;

    public static void setEndPoint(String endPoint) {
        OssBootUtil.endPoint = endPoint;
    }

    public static void setAccessKeyId(String accessKeyId) {
        OssBootUtil.accessKeyId = accessKeyId;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        OssBootUtil.accessKeySecret = accessKeySecret;
    }

    public static void setBucketName(String bucketName) {
        OssBootUtil.bucketName = bucketName;
    }

    public static void setStaticDomain(String staticDomain) {
        OssBootUtil.staticDomain = staticDomain;
    }

    public static String getStaticDomain() {
        return staticDomain;
    }

    public static String getEndPoint() {
        return endPoint;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static String getBucketName() {
        return bucketName;
    }

    public static OSSClient getOssClient() {
        return ossClient;
    }

    /**
     * 上传文件至阿里云OSS
     * @deprecated 请使用 OssService.upload() 方法
     */
    @Deprecated
    public static String upload(MultipartFile file, String fileDir, String customBucket) throws Exception {
        SsrfFileTypeFilter.checkUploadFileType(file);

        String filePath = null;
        initOss(endPoint, accessKeyId, accessKeySecret);
        StringBuilder fileUrl = new StringBuilder();
        String newBucket = bucketName;
        if(oConvertUtils.isNotEmpty(customBucket)){
            newBucket = customBucket;
        }
        
        try {
            if(!ossClient.doesBucketExist(newBucket)){
                ossClient.createBucket(newBucket);
            }
            
            String orgName = file.getOriginalFilename();
            if("".equals(orgName)){
              orgName = file.getName();
            }
            orgName = CommonUtils.getFileName(orgName);
            
            String fileName = orgName.indexOf(".") == -1
                              ? orgName + "_" + System.currentTimeMillis()
                              : orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.lastIndexOf("."));
            
            if (!fileDir.endsWith(SymbolConstant.SINGLE_SLASH)) {
                fileDir = fileDir.concat(SymbolConstant.SINGLE_SLASH);
            }
            fileDir = StrAttackFilter.filter(fileDir);
            fileUrl = fileUrl.append(fileDir + fileName);

            if (oConvertUtils.isNotEmpty(staticDomain) && staticDomain.toLowerCase().startsWith(CommonConstant.STR_HTTP)) {
                filePath = staticDomain + SymbolConstant.SINGLE_SLASH + fileUrl;
            } else {
                filePath = "https://" + newBucket + "." + endPoint + SymbolConstant.SINGLE_SLASH + fileUrl;
            }
            
            PutObjectResult result = ossClient.putObject(newBucket, fileUrl.toString(), file.getInputStream());
            
            if (result != null) {
                log.info("------OSS文件上传成功------" + fileUrl);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return filePath;
    }

    /**
     * 获取原始URL
     * @deprecated 请使用 OssService 接口
     */
    @Deprecated
    public static String getOriginalUrl(String url) {
        String originalDomain = "https://" + bucketName + "." + endPoint;
        if(oConvertUtils.isNotEmpty(staticDomain) && url.indexOf(staticDomain) != -1){
            url = url.replace(staticDomain, originalDomain);
        }
        return url;
    }

    /**
     * 文件上传
     * @deprecated 请使用 OssService.upload() 方法
     */
    @Deprecated
    public static String upload(MultipartFile file, String fileDir) throws Exception {
        return upload(file, fileDir, null);
    }

    /**
     * 上传文件至阿里云OSS
     * @deprecated 请使用 OssService.upload() 方法
     */
    @Deprecated
    public static String upload(FileItemStream file, String fileDir) {
        String filePath = null;
        initOss(endPoint, accessKeyId, accessKeySecret);
        StringBuilder fileUrl = new StringBuilder();
        
        try {
            String suffix = file.getName().substring(file.getName().lastIndexOf('.'));
            String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
            
            if (!fileDir.endsWith(SymbolConstant.SINGLE_SLASH)) {
                fileDir = fileDir.concat(SymbolConstant.SINGLE_SLASH);
            }
            fileDir = StrAttackFilter.filter(fileDir);
            fileUrl = fileUrl.append(fileDir + fileName);
            
            if (oConvertUtils.isNotEmpty(staticDomain) && staticDomain.toLowerCase().startsWith(CommonConstant.STR_HTTP)) {
                filePath = staticDomain + SymbolConstant.SINGLE_SLASH + fileUrl;
            } else {
                filePath = "https://" + bucketName + "." + endPoint + SymbolConstant.SINGLE_SLASH + fileUrl;
            }
            
            PutObjectResult result = ossClient.putObject(bucketName, fileUrl.toString(), file.openStream());
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            
            if (result != null) {
                log.info("------OSS文件上传成功------" + fileUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePath;
    }

    /**
     * 删除文件
     * @deprecated 请使用 OssService.delete() 方法
     */
    @Deprecated
    public static void deleteUrl(String url) {
        deleteUrl(url, null);
    }

    /**
     * 删除文件
     * @deprecated 请使用 OssService.delete() 方法
     */
    @Deprecated
    public static void deleteUrl(String url, String bucket) {
        String newBucket = bucketName;
        if(oConvertUtils.isNotEmpty(bucket)){
            newBucket = bucket;
        }
        
        String bucketUrl = "";
        if (oConvertUtils.isNotEmpty(staticDomain) && staticDomain.toLowerCase().startsWith(CommonConstant.STR_HTTP)) {
            bucketUrl = staticDomain + SymbolConstant.SINGLE_SLASH;
        } else {
            bucketUrl = "https://" + newBucket + "." + endPoint + SymbolConstant.SINGLE_SLASH;
        }
        
        url = url.replace(bucketUrl, "");
        ossClient.deleteObject(newBucket, url);
    }

    /**
     * 删除文件
     * @deprecated 请使用 OssService.delete() 方法
     */
    @Deprecated
    public static void delete(String fileName) {
        ossClient.deleteObject(bucketName, fileName);
    }

    /**
     * 获取文件流
     * @deprecated 请使用 OssService.getFile() 方法
     */
    @Deprecated
    public static InputStream getOssFile(String objectName, String bucket){
        InputStream inputStream = null;
        try{
            String newBucket = bucketName;
            if(oConvertUtils.isNotEmpty(bucket)){
                newBucket = bucket;
            }
            initOss(endPoint, accessKeyId, accessKeySecret);
            objectName = replacePrefix(objectName, bucket);
            OSSObject ossObject = ossClient.getObject(newBucket, objectName);
            inputStream = new BufferedInputStream(ossObject.getObjectContent());
        } catch (Exception e){
            log.info("文件获取失败" + e.getMessage());
        }
        return inputStream;
    }

    /**
     * 获取文件外链
     * @deprecated 请使用 OssService.getObjectUrl() 方法
     */
    @Deprecated
    public static String getObjectUrl(String bucketName, String objectName, Date expires) {
        initOss(endPoint, accessKeyId, accessKeySecret);
        try{
            objectName = replacePrefix(objectName, bucketName);
            if(ossClient.doesObjectExist(bucketName, objectName)){
                URL url = ossClient.generatePresignedUrl(bucketName, objectName, expires);
                return url.toString();
            }
        } catch (Exception e){
            log.info("文件路径获取失败" + e.getMessage()); 
        }
        return null;
    }

    /**
     * 上传文件到OSS
     * @deprecated 请使用 OssService.upload() 方法
     */
    @Deprecated
    public static String upload(InputStream stream, String relativePath) {
        String filePath = null;
        String fileUrl = relativePath;
        initOss(endPoint, accessKeyId, accessKeySecret);
        
        if (oConvertUtils.isNotEmpty(staticDomain) && staticDomain.toLowerCase().startsWith(CommonConstant.STR_HTTP)) {
            filePath = staticDomain + SymbolConstant.SINGLE_SLASH + relativePath;
        } else {
            filePath = "https://" + bucketName + "." + endPoint + SymbolConstant.SINGLE_SLASH + fileUrl;
        }
        
        PutObjectResult result = ossClient.putObject(bucketName, fileUrl.toString(), stream);
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        
        if (result != null) {
            log.info("------OSS文件上传成功------" + fileUrl);
        }
        return filePath;
    }

    /**
     * 初始化OSS客户端
     */
    private static OSSClient initOss(String endpoint, String accessKeyId, String accessKeySecret) {
        if (ossClient == null) {
            ossClient = new OSSClient(endpoint,
                    new DefaultCredentialProvider(accessKeyId, accessKeySecret),
                    new ClientConfiguration());
        }
        return ossClient;
    }

    /**
     * 替换前缀，防止key不一致导致获取不到文件
     */
    private static String replacePrefix(String objectName, String customBucket){
        log.info("------replacePrefix---替换前---objectName: {}", objectName);
        if(oConvertUtils.isNotEmpty(staticDomain)){
            objectName = objectName.replace(staticDomain + SymbolConstant.SINGLE_SLASH, "");
        } else {
            String newBucket = bucketName;
            if(oConvertUtils.isNotEmpty(customBucket)){
                newBucket = customBucket;
            }
            String path = "https://" + newBucket + "." + endPoint + SymbolConstant.SINGLE_SLASH;
            objectName = objectName.replace(path, "");
        }
        log.info("------replacePrefix---替换后---objectName: {}", objectName);
        return objectName;
    }
}