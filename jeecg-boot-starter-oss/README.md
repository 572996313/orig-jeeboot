# jeecg-boot-starter-oss

JeecgBoot 对象存储 Starter，提供统一的对象存储访问接口，支持 MinIO、阿里云OSS、本地文件系统三种存储方式。

## 版本信息

- **当前版本**: 4.0.0
- **JDK版本**: 1.8+
- **Spring Boot版本**: 2.7.x

## 功能特性

- ✅ 统一的对象存储接口
- ✅ 支持 MinIO 对象存储
- ✅ 支持阿里云 OSS
- ✅ 支持本地文件系统存储
- ✅ 自动配置，开箱即用
- ✅ 支持 CDN 静态域名
- ✅ 自动创建存储桶
- ✅ 文件安全过滤（SSRF、XSS防护）
- ✅ 向后兼容旧版本配置

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-oss</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 2. 配置文件

#### 使用 MinIO

```yaml
jeecg:
  oss:
    enabled: true
    type: minio
    endpoint: http://localhost:9000
    access-key: minioadmin
    secret-key: minioadmin
    bucket-name: jeecg-bucket
    static-domain: https://cdn.example.com  # 可选
    auto-create-bucket: true
```

#### 使用阿里云 OSS

```yaml
jeecg:
  oss:
    enabled: true
    type: aliyun
    endpoint: oss-cn-hangzhou.aliyuncs.com
    access-key: YOUR_ACCESS_KEY_ID
    secret-key: YOUR_ACCESS_KEY_SECRET
    bucket-name: jeecg-bucket
    static-domain: https://cdn.example.com  # 可选
```

#### 使用本地文件系统

```yaml
jeecg:
  oss:
    enabled: true
    type: local
    local-path: ./upload
    bucket-name: default
```

### 3. 使用示例

#### 注入服务

```java
import org.jeecg.common.oss.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    
    @Autowired
    private OssService ossService;
    
    // 文件上传
    public String uploadFile(MultipartFile file) {
        return ossService.upload(file, "images/");
    }
    
    // 文件下载
    public InputStream downloadFile(String objectName) {
        return ossService.getFile("jeecg-bucket", objectName);
    }
    
    // 删除文件
    public void deleteFile(String url) {
        ossService.deleteByUrl(url);
    }
    
    // 获取临时访问URL（7天有效）
    public String getFileUrl(String objectName) {
        return ossService.getObjectUrl("jeecg-bucket", objectName, 7 * 24 * 3600);
    }
}
```

#### Controller示例

```java
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.oss.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    private OssService ossService;
    
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = ossService.upload(file, "uploads/");
            return Result.ok(url);
        } catch (Exception e) {
            return Result.error("上传失败：" + e.getMessage());
        }
    }
    
    @DeleteMapping("/delete")
    public Result<Void> delete(@RequestParam("url") String url) {
        try {
            ossService.deleteByUrl(url);
            return Result.ok();
        } catch (Exception e) {
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}
```

## API文档

### OssService 接口

#### 文件上传

```java
// 上传文件到默认路径
String upload(MultipartFile file, String customPath);

// 上传文件到指定桶
String upload(MultipartFile file, String bucketName, String customPath);

// 上传输入流
String upload(InputStream inputStream, String fileName);
```

#### 文件下载

```java
// 获取文件流
InputStream getFile(String bucketName, String objectName);
```

#### 文件删除

```java
// 删除指定桶的文件
void delete(String bucketName, String objectName);

// 通过URL删除文件
void deleteByUrl(String url);
```

#### URL获取

```java
// 获取临时访问URL
String getObjectUrl(String bucketName, String objectName, Integer expires);

// 获取公开访问URL
String getPublicObjectUrl(String bucketName, String objectName);
```

#### 桶管理

```java
// 检查桶是否存在
boolean bucketExists(String bucketName);

// 创建桶
void createBucket(String bucketName);

// 获取存储类型
String getStorageType();
```

## 配置说明

### 通用配置

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.oss.enabled` | Boolean | true | 是否启用OSS功能 |
| `jeecg.oss.type` | String | minio | 存储类型：minio/aliyun/local |
| `jeecg.oss.endpoint` | String | - | 服务端点 |
| `jeecg.oss.access-key` | String | - | 访问密钥 |
| `jeecg.oss.secret-key` | String | - | 访问密钥Secret |
| `jeecg.oss.bucket-name` | String | - | 默认存储桶名称 |
| `jeecg.oss.static-domain` | String | - | 静态资源域名（CDN） |
| `jeecg.oss.auto-create-bucket` | Boolean | true | 是否自动创建桶 |

### MinIO 特定配置

```yaml
jeecg:
  oss:
    minio:
      endpoint: http://localhost:9000
      access-key: minioadmin
      secret-key: minioadmin
      bucket-name: jeecg-bucket
```

### 阿里云 OSS 特定配置

```yaml
jeecg:
  oss:
    aliyun:
      endpoint: oss-cn-hangzhou.aliyuncs.com
      access-key-id: YOUR_KEY
      access-key-secret: YOUR_SECRET
      bucket-name: jeecg-bucket
```

### 本地存储配置

```yaml
jeecg:
  oss:
    local-path: ./upload  # 本地存储路径
```

## 向后兼容

### 旧版本配置（已过时）

如果您使用的是旧版本配置，模块会自动兼容：

```yaml
# MinIO 旧配置（已过时）
jeecg:
  minio:
    minio_url: http://localhost:9000
    minio_name: minioadmin
    minio_pass: minioadmin
    bucket_name: jeecg-bucket
```

**建议**：请尽快迁移到新配置格式。

### 旧版本工具类

旧版本的静态工具类仍然可用，但已标记为 `@Deprecated`：

```java
// 已过时，不推荐使用
import org.jeecg.common.util.MinioUtil;
import org.jeecg.common.util.oss.OssBootUtil;

// 推荐使用新的服务接口
import org.jeecg.common.oss.OssService;
```

## 高级特性

### CDN 静态域名

配置静态域名后，上传的文件URL会自动替换为CDN域名：

```yaml
jeecg:
  oss:
    static-domain: https://cdn.example.com
```

上传结果：
- 不配置：`https://jeecg-bucket.oss-cn-hangzhou.aliyuncs.com/images/file.jpg`
- 配置后：`https://cdn.example.com/images/file.jpg`

### 多存储桶

支持在运行时指定不同的存储桶：

```java
// 上传到指定桶
String url = ossService.upload(file, "custom-bucket", "images/");

// 从指定桶下载
InputStream stream = ossService.getFile("custom-bucket", "images/file.jpg");
```

### 自动创建桶

启用后，如果桶不存在会自动创建：

```yaml
jeecg:
  oss:
    auto-create-bucket: true
```

### 文件安全

模块内置文件安全过滤：
- SSRF 攻击防护
- XSS 攻击防护
- 文件类型校验
- 路径遍历防护

## 存储类型对比

| 特性 | MinIO | 阿里云OSS | 本地存储 |
|-----|-------|----------|---------|
| 私有化部署 | ✅ | ❌ | ✅ |
| 成本 | 免费 | 按量付费 | 免费 |
| 高可用 | 需自建 | ✅ | ❌ |
| CDN加速 | 需配置 | ✅ | ❌ |
| 对象存储API | ✅ | ✅ | ❌ |
| 临时URL | ✅ | ✅ | ❌ |
| 适用场景 | 私有云 | 公有云 | 开发测试 |

## 常见问题

### 1. 无法连接到 MinIO

**问题**：启动报错 `Unable to execute HTTP request`

**解决**：
- 检查 `endpoint` 配置是否正确
- 确认 MinIO 服务已启动
- 检查防火墙和网络配置

### 2. 阿里云 OSS 403 错误

**问题**：上传文件返回 403 Forbidden

**解决**：
- 检查 `access-key` 和 `secret-key` 是否正确
- 确认 RAM 权限配置
- 检查 Bucket 的访问权限

### 3. 文件无法访问

**问题**：文件上传成功但无法访问

**解决**：
- 检查 Bucket 是否设置为公开读
- 确认 `static-domain` 配置正确
- 检查文件路径是否正确

### 4. 配置不生效

**问题**：修改配置后不生效

**解决**：
- 确认配置项路径正确（`jeecg.oss.*`）
- 检查 `type` 配置是否正确
- 清理缓存并重启应用

## 迁移指南

### 从旧版本迁移

1. **更新依赖**

```xml
<!-- 移除旧依赖 -->
<!-- <dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
</dependency> -->

<!-- 添加新依赖 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-oss</artifactId>
    <version>4.0.0</version>
</dependency>
```

2. **更新配置**

```yaml
# 旧配置
jeecg:
  minio:
    minio_url: http://localhost:9000
    minio_name: minioadmin
    minio_pass: minioadmin

# 新配置
jeecg:
  oss:
    type: minio
    endpoint: http://localhost:9000
    access-key: minioadmin
    secret-key: minioadmin
```

3. **更新代码**

```java
// 旧代码
import org.jeecg.common.util.MinioUtil;
String url = MinioUtil.upload(file, "images/", bucketName);

// 新代码
@Autowired
private OssService ossService;
String url = ossService.upload(file, bucketName, "images/");
```

## 依赖说明

### 必选依赖

- `jeecg-boot-base-constants`
- `jeecg-boot-base-api`
- `jeecg-boot-base-utils`
- `spring-boot-starter`
- `commons-fileupload`
- `hutool-all`

### 可选依赖

- `minio` (仅MinIO模式需要)
- `aliyun-sdk-oss` (仅阿里云模式需要)

## 开发调试

### 本地测试

```yaml
jeecg:
  oss:
    type: local
    local-path: ./test-upload
```

### Docker 启动 MinIO

```bash
docker run -d \
  -p 9000:9000 \
  -p 9001:9001 \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin" \
  minio/minio server /data --console-address ":9001"
```

访问控制台：http://localhost:9001

## 性能优化

1. **使用 CDN**：配置 `static-domain` 使用 CDN 加速
2. **连接池**：MinIO 和 OSS 客户端自动管理连接池
3. **异步上传**：大文件建议使用异步方式上传
4. **分片上传**：超大文件使用分片上传（需自行实现）

## 安全建议

1. ✅ 不要在代码中硬编码密钥
2. ✅ 使用环境变量或配置中心管理密钥
3. ✅ 定期轮换访问密钥
4. ✅ 设置合理的 Bucket 访问权限
5. ✅ 启用 HTTPS
6. ✅ 限制上传文件类型和大小

## 更新日志

### v4.0.0 (2025-11-08)

- 🎉 初始版本发布
- ✨ 支持 MinIO、阿里云OSS、本地存储
- ✨ 统一的对象存储接口
- ✨ 自动配置功能
- ✨ 向后兼容旧版本

## 联系与支持

- 📧 邮箱：linuxdo_llllxf@outlook.com
- 🐛 问题反馈：提交 Issue
- 📖 文档：查看在线文档

## 许可证

本模块遵循 Apache License 2.0 开源协议。

---

**作者**: JeecgBoot Team  
**维护**: llllxf  
**版本**: 4.0.0  
**更新时间**: 2025-11-08