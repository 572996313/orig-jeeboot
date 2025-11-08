
# jeecg-boot-base-core 模块拆分 - 详细类清单

**生成时间**: 2025-11-08  
**基于**: jeecg-boot-base-core 当前实际代码结构

---

## 📊 模块统计概览

| 模块分类 | 文件数量 | 说明 |
|---------|---------|------|
| 常量类 (Constants) | 12个 | 纯Java常量定义 |
| 枚举类 (Enums) | 15个 | 枚举类型定义 |
| API/DTO/VO | 29个 | 接口和数据传输对象 |
| 工具类 (Utils) | 45个 | 纯工具方法类 |
| 配置类 (Config) | 30个 | Spring配置类 |
| 切面类 (Aspect) | 4个 | AOP切面 |
| 异常类 (Exception) | 6个 | 异常定义 |
| 基础服务类 | 5个 | CRUD基类 |
| **总计** | **146个** | Java源文件 |

---

## 🎯 Phase 1: 基础工具层拆分

### 1.1 jeecg-boot-base-constants 模块

**职责**: 纯Java常量和枚举定义,零依赖

#### 常量类 (12个文件)

```
org/jeecg/common/constant/
├── CommonConstant.java          # 通用常量
├── CommonSendStatus.java        # 发送状态常量
├── DataBaseConstant.java        # 数据库常量
├── DynamicTableConstant.java    # 动态表常量
├── FillRuleConstant.java        # 填充规则常量
├── PasswordConstant.java        # 密码常量
├── ProvinceCityArea.java        # 省市区常量
├── ServiceNameConstants.java    # 服务名常量
├── SymbolConstant.java          # 符号常量
├── TenantConstant.java          # 租户常量
├── VxeSocketConst.java          # VxeSocket常量
└── WebsocketConst.java          # WebSocket常量
```

#### 枚举类 (15个文件)

```
org/jeecg/common/constant/enums/
├── CgformEnum.java                  # 表单枚举
├── ClientTerminalTypeEnum.java     # 客户端类型枚举
├── DateRangeEnum.java              # 日期范围枚举
├── DepartCategoryEnum.java         # 部门分类枚举
├── DySmsEnum.java                  # 短信枚举
├── EmailTemplateEnum.java          # 邮件模板枚举
├── FileTypeEnum.java               # 文件类型枚举
├── MessageTypeEnum.java            # 消息类型枚举
├── ModuleType.java                 # 模块类型枚举
├── NoticeTypeEnum.java             # 通知类型枚举
├── OperateTypeEnum.java            # 操作类型枚举
├── PositionLevelEnum.java          # 职位级别枚举
├── RoleIndexConfigEnum.java        # 角色首页配置枚举
├── SysAnnmentTypeEnum.java         # 公告类型枚举
└── Vue3MessageHrefEnum.java        # Vue3消息跳转枚举
```

**依赖**: 无 (纯Java)  
**预计工作量**: 2人日

---

### 1.2 jeecg-boot-base-api 模块

**职责**: API接口定义、DTO、VO、异常类

#### API接口 (1个文件)

```
org/jeecg/common/api/
└── CommonAPI.java                   # 通用API接口
```

#### DTO类 (10个文件)

```
org/jeecg/common/api/dto/
├── AiragFlowDTO.java               # AI流程DTO
├── DataLogDTO.java                 # 数据日志DTO
├── FileDownDTO.java                # 文件下载DTO
├── FileUploadDTO.java              # 文件上传DTO
├── LogDTO.java                     # 日志DTO
├── OnlineAuthDTO.java              # 在线认证DTO
└── message/
    ├── BusTemplateMessageDTO.java  # 业务模板消息DTO
    ├── MessageDTO.java             # 消息DTO
    ├── TemplateDTO.java            # 模板DTO
    └── TemplateMessageDTO.java     # 模板消息DTO
```

#### VO类 (13个文件)

```
org/jeecg/common/api/vo/
└── Result.java                      # 统一响应结果

org/jeecg/common/system/vo/
├── ComboModel.java                  # 下拉框模型
├── DictModel.java                   # 字典模型
├── DictModelMany.java               # 字典多值模型
├── DictQuery.java                   # 字典查询
├── DynamicDataSourceModel.java      # 动态数据源模型
├── LoginUser.java                   # 登录用户
├── SelectTreeModel.java             # 选择树模型
├── SysCategoryModel.java            # 系统分类模型
├── SysDepartModel.java              # 系统部门模型
├── SysFilesModel.java               # 系统文件模型
├── SysPermissionDataRuleModel.java  # 权限数据规则模型
├── SysUserCacheInfo.java            # 用户缓存信息
└── UserAccountInfo.java             # 用户账户信息
```

#### 异常类 (6个文件)

```
org/jeecg/common/exception/
├── JeecgBoot401Exception.java       # 401异常
├── JeecgBootAssertException.java    # 断言异常
├── JeecgBootBizTipException.java    # 业务提示异常
├── JeecgBootException.java          # 基础异常
├── JeecgBootExceptionHandler.java   # 异常处理器(移至starter-web)
└── JeecgSqlInjectionException.java  # SQL注入异常
```

#### 配置VO类 (8个文件)

```
org/jeecg/config/vo/
├── BaiduApi.java                    # 百度API配置
├── DomainUrl.java                   # 域名URL配置
├── Elasticsearch.java               # ES配置
├── Firewall.java                    # 防火墙配置
├── GaoDeApi.java                    # 高德API配置
├── Path.java                        # 路径配置
├── Shiro.java                       # Shiro配置
└── WeiXinPay.java                   # 微信支付配置
```

**依赖**: jeecg-boot-base-constants  
**预计工作量**: 3人日

---

### 1.3 jeecg-boot-base-utils 模块

**职责**: 纯Java工具类,不依赖Spring

#### 基础工具类 (18个文件)

```
org/jeecg/common/util/
├── AssertUtils.java                 # 断言工具
├── BrowserType.java                 # 浏览器类型
├── BrowserUtils.java                # 浏览器工具
├── CommonUtils.java                 # 通用工具
├── DateRangeUtils.java              # 日期范围工具
├── DateUtils.java                   # 日期工具
├── HTMLUtils.java                   # HTML工具
├── IpUtils.java                     # IP工具
├── Md5Util.java                     # MD5加密
├── oConvertUtils.java               # 转换工具
├── PasswordUtil.java                # 密码工具
├── ReflectHelper.java               # 反射工具
├── SqlInjectionUtil.java            # SQL注入防护
├── UUIDGenerator.java               # UUID生成器
├── YouBianCodeUtil.java             # 编码工具
├── MyClassLoader.java               # 类加载器
├── PmsUtil.java                     # 权限工具
└── RestDesformUtil.java             # Rest表单工具
```

#### 加密安全工具 (7个文件)

```
org/jeecg/common/util/encryption/
├── AesEncryptUtil.java              # AES加密
└── EncryptedString.java             # 加密字符串

org/jeecg/common/util/security/
├── AbstractQueryBlackListHandler.java  # 查询黑名单抽象类
├── JdbcSecurityUtil.java            # JDBC安全工具
├── SecurityTools.java               # 安全工具
└── entity/
    ├── MyKeyPair.java               # 密钥对
    ├── SecurityReq.java             # 安全请求
    ├── SecurityResp.java            # 安全响应
    ├── SecuritySignReq.java         # 签名请求
    └── SecuritySignResp.java        # 签名响应
```

#### 过滤器工具 (2个文件)

```
org/jeecg/common/util/filter/
├── SsrfFileTypeFilter.java          # SSRF文件类型过滤
└── StrAttackFilter.java             # 字符串攻击过滤
```

#### SQL解析工具 (3个文件)

```
org/jeecg/common/util/sqlparse/
├── JSqlParserAllTableManager.java   # SQL解析表管理
├── JSqlParserUtils.java             # SQL解析工具
└── vo/
    └── SelectSqlInfo.java           # 查询SQL信息
```

#### 查询构建工具 (4个文件)

```
org/jeecg/common/system/query/
├── MatchTypeEnum.java               # 匹配类型枚举
├── QueryCondition.java              # 查询条件
├── QueryGenerator.java              # 查询生成器
└── QueryRuleEnum.java               # 查询规则枚举
```

#### 高级搜索工具 (3个文件)

```
org/jeecg/common/util/superSearch/
├── ObjectParseUtil.java             # 对象解析工具
├── QueryRuleEnum.java               # 查询规则枚举
└── QueryRuleVo.java                 # 查询规则VO
```

#### 数据库工具 (2个文件)

```
org/jeecg/common/util/dynamic/db/
├── DbTypeUtils.java                 # 数据库类型工具
└── FreemarkerParseFactory.java      # Freemarker解析工厂
```

#### JWT工具 (1个文件)

```
org/jeecg/common/system/util/
└── JwtUtil.java                     # JWT工具(准无状态)
```

**依赖**: 
- jeecg-boot-base-constants
- Hutool (可选)
- JSqlParser (SQL解析)

**预计工作量**: 5人日

---

## 🔧 Phase 2: 功能模块拆分

### 2.1 jeecg-boot-base-core-lite 模块

**职责**: 轻量级核心,提供最基础的Spring集成

#### 基础CRUD类 (5个文件)

```
org/jeecg/common/system/base/
├── controller/
│   └── JeecgController.java         # 控制器基类
├── entity/
│   └── JeecgEntity.java             # 实体基类
└── service/
    ├── JeecgService.java            # 服务接口
    └── impl/
        └── JeecgServiceImpl.java    # 服务实现基类
```

#### Spring工具类 (1个文件)

```
org/jeecg/common/util/
└── SpringContextUtils.java          # Spring上下文工具
```

#### 处理器接口 (1个文件)

```
org/jeecg/common/handler/
└── IFillRuleHandler.java            # 填充规则处理器接口
```

#### 基础配置类 (3个文件)

```
org/jeecg/config/
├── JeecgBaseConfig.java             # 基础配置
├── RestTemplateConfig.java          # RestTemplate配置
└── StaticConfig.java                # 静态配置
```

#### 系统工具类 (4个文件)

```
org/jeecg/common/system/util/
├── JeecgDataAutorUtils.java         # 数据作者工具
├── ResourceUtil.java                # 资源工具
└── SqlConcatUtil.java               # SQL拼接工具
```

#### 增强类 (1个文件)

```
org/jeecg/common/system/enhance/
└── UserFilterEnhance.java           # 用户过滤增强
```

#### 注解类 (1个文件)

```
org/jeecg/common/system/annotation/
└── EnumDict.java                    # 枚举字典注解
```

**依赖**:
- jeecg-boot-base-constants
- jeecg-boot-base-api
- jeecg-boot-base-utils
- Spring Boot
- MyBatis-Plus (基础)

**预计工作量**: 4人日

---

### 2.2 jeecg-boot-starter-security 模块

**职责**: Shiro + JWT 安全认证

#### Shiro配置类 (10个文件)

```
org/jeecg/config/shiro/
├── IgnoreAuth.java                  # 忽略认证注解
├── JwtToken.java                    # JWT令牌
├── ShiroConfig.java                 # Shiro配置
├── ShiroRealm.java                  # Shiro域
├── filters/
│   ├── CustomShiroFilterFactoryBean.java  # 自定义过滤器工厂
│   ├── JwtFilter.java               # JWT过滤器
│   └── ResourceCheckFilter.java     # 资源检查过滤器
└── ignore/
    ├── IgnoreAuthPostProcessor.java # 忽略认证后处理器
    └── InMemoryIgnoreAuth.java      # 内存忽略认证
```

#### 签名认证 (5个文件)

```
org/jeecg/config/sign/
├── interceptor/
│   ├── SignAuthConfiguration.java   # 签名认证配置
│   └── SignAuthInterceptor.java     # 签名认证拦截器
└── util/
    ├── BodyReaderHttpServletRequestWrapper.java  # 请求包装器
    ├── HttpUtils.java               # HTTP工具
    └── SignUtil.java                # 签名工具
```

#### JWT工具类 