# JeecgBoot Communication Starter

JeecgBoot 通信模块 - 提供邮件、短信、WebSocket通信能力

## 模块说明

本模块是 `jeecg-boot-base-core` 拆分后的独立通信服务模块，提供以下功能：

- **邮件服务**：发送文本/HTML邮件、附件邮件、模板邮件
- **短信服务**：验证码、通知、营销短信，支持阿里云/腾讯云
- **WebSocket服务**：实时消息推送、心跳保活、广播/单播

## 功能特性

### 1. 邮件服务

- ✅ 发送文本邮件
- ✅ 发送HTML邮件
- ✅ 发送带附件邮件
- ✅ 使用Freemarker模板发送邮件
- ✅ 发送验证码邮件
- ✅ 发送系统通知邮件
- ✅ 批量发送支持

### 2. 短信服务

- ✅ 阿里云短信支持
- ✅ 多维度限流保护（每日、每小时、发送间隔、IP）
- ✅ 验证码短信
- ✅ 登录通知短信
- ✅ 注册通知短信
- ✅ 订单通知短信
- ✅ 系统通知短信
- ✅ 自定义模板短信
- ✅ 批量发送支持
- ⏳ 腾讯云短信支持（待实现）

### 3. WebSocket服务

- ✅ 连接管理
- ✅ 消息收发
- ✅ 心跳保活机制
- ✅ 连接超时检测
- ✅ 单播消息
- ✅ 广播消息
- ✅ 在线用户管理

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-communication</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 2. 配置文件

#### 邮件服务配置

```yaml
spring:
  mail:
    host: smtp.qq.com
    port: 465
    username: your-email@qq.com
    password: your-password
    protocol: smtp
    default-encoding: UTF-8
    properties:
      mail:
        smtp:
          auth: true
          ssl:
            enable: true

jeecg:
  communication:
    email:
      enabled: true
      from: your-email@qq.com
      from-name: JeecgBoot
      template-path: /templates/email
      html: true
      encoding: UTF-8
```

#### 短信服务配置（阿里云）

```yaml
jeecg:
  communication:
    sms:
      enabled: true
      provider: aliyun
      access-key: your-access-key
      secret-key: your-secret-key
      sign-name: 您的签名
      region-id: cn-hangzhou
      # 限流配置
      rate-limit:
        max-per-day: 10          # 每日最大发送次数
        max-per-hour: 5          # 每小时最大发送次数
        interval: 60             # 发送间隔（秒）
        max-per-ip-per-hour: 10  # 每IP每小时最大次数
      # 短信模板配置
      template:
        verify-code: SMS_123456      # 验证码模板
        login-notice: SMS_234567     # 登录通知模板
        register-notice: SMS_345678  # 注册通知模板
        order-notice: SMS_456789     # 订单通知模板
        system-notice: SMS_567890    # 系统通知模板
```

#### WebSocket服务配置

```yaml
jeecg:
  communication:
    websocket:
      enabled: true
      path: /websocket/**
      allowed-origins: "*"
      buffer-size: 8192
      max-text-message-size: 8192
      max-binary-message-size: 8192
      max-session-idle-timeout: 300000  # 5分钟
      # 心跳配置
      heartbeat:
        enabled: true
        interval: 30      # 心跳间隔（秒）
        timeout-count: 3  # 超时次数
```

### 3. 使用示例

#### 邮件服务使用

```java
@Autowired
private EmailService emailService;

// 发送文本邮件
emailService.sendText("user@example.com", "测试邮件", "这是邮件内容");

// 发送HTML邮件
String htmlContent = "<h1>欢迎</h1><p>这是一封HTML邮件</p>";
emailService.sendHtml("user@example.com", "欢迎", htmlContent);

// 发送带附件邮件
File[] attachments = {new File("report.pdf")};
emailService.sendWithAttachment("user@example.com", "报告", "请查收附件", attachments);

// 发送模板邮件
Map<String, Object> model = new HashMap<>();
model.put("username", "张三");
model.put("content", "您有一条新消息");
emailService.sendTemplate("user@example.com", "通知", "notice.ftl", model);

// 发送验证码邮件
emailService.sendVerifyCode("user@example.com", "123456", 5);

// 发送通知邮件
emailService.sendNotice("user@example.com", "系统通知", "您的订单已发货");
```

#### 短信服务使用

```java
@Autowired
private SmsService smsService;

// 发送验证码
smsService.sendVerifyCode("13800138000", "123456");

// 发送验证码（指定过期时间）
smsService.sendVerifyCode("13800138000", "123456", 5);

// 发送登录通知
smsService.sendLoginNotice("13800138000", "2025-01-08 10:00", "北京");

// 发送注册通知
smsService.sendRegisterNotice("13800138000", "张三");

// 发送订单通知
smsService.sendOrderNotice("13800138000", "ORDER123456", "已发货");

// 发送系统通知
smsService.sendSystemNotice("13800138000", "您的账户余额不足");

// 发送自定义模板短信
Map<String, String> params = new HashMap<>();
params.put("name", "张三");
params.put("code", "123456");
smsService.sendTemplate("13800138000", "SMS_123456", params);

// 批量发送
String[] phones = {"13800138000", "13900139000"};
smsService.sendTemplateBatch(phones, "SMS_123456", params);

// 检查是否超限
boolean limited = smsService.isRateLimited("13800138000");

// 获取剩余发送次数
int remaining = smsService.getRemainingCount("13800138000");
```

#### WebSocket服务使用

**后端发送消息：**

```java
@Autowired
private JeecgWebSocketHandler webSocketHandler;

// 发送消息给指定用户
webSocketHandler.sendToUser("user123", "您有一条新消息");

// 广播消息给所有在线用户
webSocketHandler.broadcast("系统维护通知");

// 获取在线用户数
int count = webSocketHandler.getOnlineCount();

// 检查用户是否在线
boolean online = webSocketHandler.isOnline("user123");
```

**前端连接示例：**

```javascript
// 建立WebSocket连接
const ws = new WebSocket('ws://localhost:8080/websocket/user123');

// 连接成功
ws.onopen = function() {
    console.log('WebSocket连接成功');
    
    // 启动心跳
    setInterval(() => {
        ws.send(JSON.stringify({
            type: 'PING'
        }));
    }, 30000);
};

// 收到消息
ws.onmessage = function(event) {
    const message = JSON.parse(event.data);
    console.log('收到消息:', message);
    
    switch(message.type) {
        case 'CONNECTED':
            console.log('连接成功:', message.content);
            break;
        case 'PONG':
            console.log('心跳响应');
            break;
        case 'MESSAGE':
            console.log('业务消息:', message.content);
            break;
        case 'BROADCAST':
            console.log('广播消息:', message.content);
            break;
    }
};

// 连接关闭
ws.onclose = function() {
    console.log('WebSocket连接关闭');
};

// 连接错误
ws.onerror = function(error) {
    console.error('WebSocket错误:', error);
};

// 发送消息
function sendMessage(targetUserId, content) {
    ws.send(JSON.stringify({
        type: 'MESSAGE',
        targetUserId: targetUserId,
        content: content
    }));
}

// 广播消息
function broadcast(content) {
    ws.send(JSON.stringify({
        type: 'BROADCAST',
        content: content
    }));
}
```

## 邮件模板

模块内置了两个邮件模板：

### 1. verify_code.ftl - 验证码模板

```freemarker
参数：
- code: 验证码
- expireMinutes: 过期时间（分钟）
- systemName: 系统名称
```

### 2. notice.ftl - 通知模板

```freemarker
参数：
- title: 通知标题
- message: 通知内容
- systemName: 系统名称
```

### 自定义模板

在 `src/main/resources/templates/email/` 目录下创建 `.ftl` 文件：

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
    <h1>欢迎 ${username}</h1>
    <p>${content}</p>
</body>
</html>
```

## 短信限流说明

为防止短信滥用和费用超支，系统提供多维度限流：

1. **每日限制**：单个手机号每日最大发送次数
2. **每小时限制**：单个手机号每小时最大发送次数
3. **发送间隔**：两次发送之间的最小间隔时间
4. **IP限制**：单个IP每小时最大发送次数

限流记录存储在Redis中，需要配置Redis连接。

## 依赖说明

### 必选依赖

- `jeecg-boot-base-constants`：常量定义
- `jeecg-boot-base-api`：API接口
- `jeecg-boot-base-utils`：工具类
- `spring-boot-starter`：Spring Boot核心

### 可选依赖

- `spring-boot-starter-mail`：邮件服务（需要启用邮件功能时添加）
- `spring-boot-starter-freemarker`：模板引擎（需要使用邮件模板时添加）
- `spring-boot-starter-websocket`：WebSocket（需要启用WebSocket时添加）
- `aliyun-java-sdk-core`：阿里云SDK（需要使用阿里云短信时添加）
- `aliyun-java-sdk-dysmsapi`：阿里云短信SDK
- `spring-boot-starter-data-redis`：Redis（需要短信限流时添加）

## 注意事项

1. **邮件服务**：需要配置SMTP服务器，建议使用企业邮箱或第三方邮件服务
2. **短信服务**：需要在阿里云/腾讯云开通短信服务并完成实名认证
3. **短信限流**：强烈建议配置Redis以启用限流功能，防止短信滥用
4. **WebSocket路径**：默认路径为 `/websocket/**`，需要在URL中包含userId
5. **心跳机制**：建议客户端定时发送PING消息，防止连接超时
6. **安全建议**：
   - AccessKey/SecretKey等敏感信息使用环境变量或配置中心
   - 生产环境限制WebSocket的allowed-origins
   - 启用短信发送频率限制
   - 验证码有效期不宜过长

## 配置项参考

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| jeecg.communication.email.enabled | Boolean | false | 是否启用邮件服务 |
| jeecg.communication.email.from | String | - | 发件人邮箱 |
| jeecg.communication.email.from-name | String | JeecgBoot | 发件人名称 |
| jeecg.communication.sms.enabled | Boolean | false | 是否启用短信服务 |
| jeecg.communication.sms.provider | String | aliyun | 短信服务商 |
| jeecg.communication.sms.rate-limit.max-per-day | Integer | 10 | 每日最大发送次数 |
| jeecg.communication.sms.rate-limit.max-per-hour | Integer | 5 | 每小时最大发送次数 |
| jeecg.communication.sms.rate-limit.interval | Integer | 60 | 发送间隔（秒） |
| jeecg.communication.websocket.enabled | Boolean | false | 是否启用WebSocket |
| jeecg.communication.websocket.path | String | /websocket/** | WebSocket路径 |
| jeecg.communication.websocket.heartbeat.enabled | Boolean | true | 是否启用心跳 |
| jeecg.communication.websocket.heartbeat.interval | Long | 30 | 心跳间隔（秒） |
| jeecg.communication.websocket.heartbeat.timeout-count | Integer | 3 | 心跳超时次数 |

## 常见问题

### 1. 邮件发送失败

- 检查SMTP服务器配置是否正确
- 确认邮箱账号密码正确（QQ邮箱需要使用授权码）
- 检查端口和SSL配置
- 查看防火墙是否拦截

### 2. 短信发送失败

- 确认AccessKey和SecretKey正确
- 检查短信签名和模板是否已审核通过
- 确认手机号格式正确
- 检查账户余额是否充足
- 查看是否触发限流

### 3. WebSocket连接失败

- 确认WebSocket服务已启用
- 检查URL路径是否正确
- 查看allowed-origins配置
- 确认防火墙和代理设置

### 4. 短信限流不生效

- 确认已配置Redis
- 检查Redis连接是否正常
- 查看限流配置参数

## 版本历史

- **v4.0.0** (2025-01-08)
  - 首次发布
  - 