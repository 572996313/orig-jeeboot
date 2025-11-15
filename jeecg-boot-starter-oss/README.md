# Jeecg Boot Starter OSS

> 文件存储 Spring Boot Starter，支持阿里云 OSS、MinIO 和本地存储

## 功能特性

- ✅ 支持阿里云 OSS 对象存储
- ✅ 支持 MinIO 对象存储
- ✅ 支持本地文件存储
- ✅ 文件上传安全校验（文件类型白名单、路径安全检查）
- ✅ 文件下载工具（单文件、批量ZIP下载）
- ✅ 自动配置，开箱即用

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-oss</artifactId>
    <version>${jeecg.boot.version}</version>
</dependency>
```

### 2. 配置文件

#### 阿里云 OSS 配置

```yaml
jeecg:
  oss:
    endpoint: oss-cn-beijing.aliyuncs.com
    accessKey: your-access-key
    secretKey: your-secret-key
    bucketName: your-bucket
    staticDomain: https://your-domain.com  # 可选，自定义访问域名
```

#### MinIO 配置

```yaml
jeecg:
  minio:
    minio_url: 127.0.0.1:9000
    minio_name: admin
    minio_pass: admin123
    bucketName: jeecgboot
```

## 使用示例

### 文件上传

#### 1. 使用 CommonUtils 统一上传

```java
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.OSSCommonUtils;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        // 上传到 MinIO
        String url = OSSCommonUtils.upload(file, "demo", CommonConstant.UPLOAD_TYPE_MINIO);

        // 上传到阿里云 OSS
        // String url = CommonUtils.upload(file, "demo", CommonConstant.UPLOAD_TYPE_OSS);

        // 本地文件上传
        // String url = CommonUtils.uploadLocal(file, "demo", "/path/to/upload");

        return url;
    }

    @PostMapping("/upload/custom-bucket")
    public String uploadCustomBucket(MultipartFile file) {
        // 上传到自定义桶
        String url = OSSCommonUtils.upload(file, "demo",
                CommonConstant.UPLOAD_TYPE_MINIO, "custom-bucket");
        return url;
    }
}
```

#### 2. 直接使用 OssBootUtil (阿里云 OSS)

```java
import org.jeecg.common.util.oss.OssBootUtil;

String url = OssBootUtil.upload(file, "bizPath");
String url2 = OssBootUtil.upload(file, "bizPath", "customBucket");
```

#### 3. 直接使用 MinioUtil

```java
import org.jeecg.common.util.MinioUtil;

String url = MinioUtil.upload(file, "bizPath");
String url2 = MinioUtil.upload(file, "bizPath", "customBucket");
```

### 文件下载

#### 1. 单文件下载

```java
import org.jeecg.common.util.FileDownloadUtils;
import jakarta.servlet.http.HttpServletResponse;

@GetMapping("/download")
public void download(HttpServletResponse response) {
    String storePath = "/path/to/file.pdf";
    String fileName = "document.pdf";
    FileDownloadUtils.downloadFile(response, storePath, fileName);
}
```

#### 2. 批量ZIP下载

```java
@GetMapping("/download/batch")
public void downloadBatch(HttpServletResponse response) throws IOException {
    List<String> filesPath = Arrays.asList(
        "/path/to/file1.pdf",
        "/path/to/file2.docx",
        "/path/to/file3.jpg"
    );
    FileDownloadUtils.downloadFileMulti(response, filesPath, "documents");
}
```

#### 3. ZIP流式下载

```java
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

@GetMapping("/download/stream")
public void downloadStream(HttpServletResponse response) throws IOException {
    response.setContentType("application/zip");
    response.setHeader("Content-Disposition", "attachment; filename=files.zip");
    
    try (ZipArchiveOutputStream zous = new ZipArchiveOutputStream(response.getOutputStream())) {
        FileDownloadUtils.downLoadSingleFile("http://example.com/file1.pdf", 
            "file1.pdf", "/upload", zous);
        FileDownloadUtils.downLoadSingleFile("http://example.com/file2.jpg", 
            "file2.jpg", "/upload", zous);
    }
}
```

## 文件安全

### 文件类型白名单

默认支持的文件类型：
- 图片：jpg, jpeg, png, gif, bmp, svg, ico, heic
- 文档：txt, doc, docx, pdf, csv, md
- 视频：mp4, avi, mov, wmv, mp3, wav
- 表格：xls, xlsx
- 压缩：zip, rar, 7z, tar
- 其他：ppt, pptx, apk, wgt

### 路径安全校验

自动检查：
- 防止路径遍历攻击（`..`、`~`）
- 限制路径深度（最多5层）
- 限制字符集（仅允许字母、数字、下划线、横线、斜杠）

## 配置说明

### 阿里云 OSS 配置项

| 配置项 | 说明 | 必填 | 默认值 |
|--------|------|------|--------|
| jeecg.oss.endpoint | OSS访问域名 | 是 | - |
| jeecg.oss.accessKey | Access Key ID | 是 | - |
| jeecg.oss.secretKey | Access Key Secret | 是 | - |
| jeecg.oss.bucketName | 存储桶名称 | 是 | - |
| jeecg.oss.staticDomain | 自定义域名 | 否 | - |

### MinIO 配置项

| 配置项 | 说明 | 必填 | 默认值 |
|--------|------|------|--------|
| jeecg.minio.minio_url | MinIO服务地址 | 是 | - |
| jeecg.minio.minio_name | MinIO用户名 | 是 | - |
| jeecg.minio.minio_pass | MinIO密码 | 是 | - |
| jeecg.minio.bucketName | 存储桶名称 | 是 | - |

## 依赖说明

本模块已集成以下依赖，使用时无需重复引入：
- MinIO SDK (io.minio:minio:8.5.2)
- 阿里云 OSS SDK (com.aliyun.oss:aliyun-sdk-oss:3.15.1，optional)
- Commons IO (commons-io:commons-io)
- Commons Compress (org.apache.commons:commons-compress)

## 迁移说明

本模块从 `jeecg-boot-base-core` 中拆分而来，保持了完全的向后兼容性：
- 所有类的包路径保持不变
- 所有公共 API 方法签名保持不变
- 配置项保持不变

旧代码无需修改，只需：
1. 在 `jeecg-boot-base-core` 的 pom.xml 中添加本模块依赖
2. （可选）从 `jeecg-boot-base-core` 中移除 OSS 相关代码

## 注意事项

1. **配置条件加载**：
   - 阿里云 OSS 配置需要 `jeecg.oss.endpoint` 存在才会启用
   - MinIO 配置需要 `jeecg.minio.minio_url` 存在才会启用
   - 两者可以同时配置，通过 `uploadType` 参数选择使用哪个

2. **文件安全**：
   - 所有文件上传都会进行类型和路径安全校验
   - 如需自定义白名单，请参考 `SsrfFileTypeFilter` 类

3. **自定义域名**：
   - 阿里云 OSS 支持通过 `staticDomain` 配置自定义访问域名
   - MinIO 的访问URL由 `minio_url` + `bucketName` + 文件路径组成

## 版本历史

- **3.7.1**: 首次发布，从 jeecg-boot-base-core 拆分

## 技术支持

- 官方网站：http://www.jeecg.com
- 问题反馈：https://github.com/jeecgboot/jeecg-boot