package org.jeecg.common.oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

/**
 * 对象存储服务统一接口
 * <p>
 * 提供文件上传、下载、删除等基础操作，支持多种存储实现：
 * <ul>
 *     <li>MinIO - 开源对象存储</li>
 *     <li>阿里云OSS - 阿里云对象存储服务</li>
 *     <li>Local - 本地文件存储</li>
 * </ul>
 *
 * @author jeecg-boot
 * @since 4.0.0
 */
public interface OssService {

    /**
     * 上传文件（使用默认桶）
     *
     * @param file 文件对象
     * @param customPath 自定义路径（相对路径，如：images/avatar.jpg）
     * @return 文件访问URL
     */
    String upload(MultipartFile file, String customPath);

    /**
     * 上传文件（指定桶名称）
     *
     * @param file 文件对象
     * @param bucketName 桶名称
     * @param customPath 自定义路径
     * @return 文件访问URL
     */
    String upload(MultipartFile file, String bucketName, String customPath);

    /**
     * 上传文件流
     *
     * @param inputStream 文件输入流
     * @param fileName 文件名（包含扩展名）
     * @return 文件访问URL
     */
    String upload(InputStream inputStream, String fileName);

    /**
     * 上传文件流（指定桶名称）
     *
     * @param inputStream 文件输入流
     * @param bucketName 桶名称
     * @param fileName 文件名
     * @return 文件访问URL
     */
    String upload(InputStream inputStream, String bucketName, String fileName);

    /**
     * 获取文件流（下载文件）
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称（文件路径）
     * @return 文件输入流
     */
    InputStream getFile(String bucketName, String objectName);

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称（文件路径）
     */
    void delete(String bucketName, String objectName);

    /**
     * 删除文件（根据URL）
     *
     * @param url 文件完整URL
     */
    void deleteByUrl(String url);

    /**
     * 获取文件访问URL（临时URL）
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param expires 过期时间（秒）
     * @return 临时访问URL
     */
    String getObjectUrl(String bucketName, String objectName, Integer expires);

    /**
     * 获取文件访问URL（指定过期时间）
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param expiration 过期时间点
     * @return 临时访问URL
     */
    String getObjectUrl(String bucketName, String objectName, Date expiration);

    /**
     * 获取永久访问URL（公开访问）
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @return 永久访问URL
     */
    String getPublicObjectUrl(String bucketName, String objectName);

    /**
     * 检查桶是否存在
     *
     * @param bucketName 桶名称
     * @return true-存在，false-不存在
     */
    boolean bucketExists(String bucketName);

    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     */
    void createBucket(String bucketName);

    /**
     * 删除桶
     *
     * @param bucketName 桶名称
     */
    void deleteBucket(String bucketName);

    /**
     * 获取存储类型
     *
     * @return 存储类型（minio, aliyun, local）
     */
    String getStorageType();
}