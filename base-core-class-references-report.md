# Base-Core类引用分析报告

## 📊 摘要

- **分析时间**: 2025-11-09 12:40:47
- **类总数**: 201
- **引用总数**: 1575
- **平均引用**: 7.8 次/类

## 📦 类别统计

| 类别 | 数量 | 百分比 |
|------|------|--------|
| Util | 58 | 28.9% |
| Config | 41 | 20.4% |
| Constant | 27 | 13.4% |
| VO | 24 | 11.9% |
| Other | 18 | 9.0% |
| API | 13 | 6.5% |
| Annotation | 12 | 6.0% |
| Exception | 6 | 3.0% |
| Controller | 1 | 0.5% |
| Entity | 1 | 0.5% |

## 🎯 建议迁移顺序（Top 30）

| 优先级 | 类名 | 类别 | 引用次数 | 目标模块 |
|--------|------|------|----------|----------|
| 🔴 13 | `GaoDeApi` | VO | 0 | jeecg-boot-base-api |
| 🔴 12 | `AutoDict` | Annotation | 0 | jeecg-boot-base-api |
| 🔴 12 | `CgformEnum` | Constant | 0 | jeecg-boot-base-constants |
| 🔴 12 | `OperateTypeEnum` | Constant | 0 | jeecg-boot-base-constants |
| 🔴 12 | `PositionLevelEnum` | Constant | 0 | jeecg-boot-base-constants |
| 🔴 12 | `MyClassLoader` | Util | 0 | jeecg-boot-base-utils |
| 🔴 12 | `PmsUtil` | Util | 0 | jeecg-boot-base-utils |
| 🔴 12 | `RestDesformUtil` | Util | 0 | jeecg-boot-base-utils |
| 🔴 12 | `HttpUtils` | Util | 0 | jeecg-boot-base-utils |
| 🔴 12 | `SignUtil` | Util | 0 | jeecg-boot-base-utils |
| 🔴 11 | `SelectTreeModel` | VO | 1 | jeecg-boot-base-api |
| 🔴 11 | `QueryRuleVo` | VO | 1 | jeecg-boot-base-api |
| 🔴 11 | `BaiduApi` | VO | 1 | jeecg-boot-base-api |
| 🔴 11 | `WeiXinPay` | VO | 1 | jeecg-boot-base-api |
| 🔴 11 | `SysFilesModel` | VO | 2 | jeecg-boot-base-api |
| 🔴 11 | `DictModelMany` | VO | 3 | jeecg-boot-base-api |
| 🔴 11 | `DomainUrl` | VO | 3 | jeecg-boot-base-api |
| 🔴 11 | `ComboModel` | VO | 4 | jeecg-boot-base-api |
| 🔴 11 | `SysCategoryModel` | VO | 4 | jeecg-boot-base-api |
| 🔴 11 | `UserAccountInfo` | VO | 4 | jeecg-boot-base-api |
| 🔴 11 | `SelectSqlInfo` | VO | 4 | jeecg-boot-base-api |
| 🔴 10 | `FileDownDTO` | API | 0 | jeecg-boot-base-api |
| 🔴 10 | `FileUploadDTO` | API | 0 | jeecg-boot-base-api |
| 🔴 10 | `SensitiveSerialize` | Other | 0 | jeecg-boot-base-core-lite |
| 🔴 10 | `SensitiveEnum` | Other | 0 | jeecg-boot-base-core-lite |
| 🔴 10 | `QueryStringBuilder` | Other | 0 | jeecg-boot-base-core-lite |
| 🔴 10 | `JeecgBoot401Exception` | Exception | 0 | jeecg-boot-base-core-lite |
| 🔴 10 | `AutoPoiConfig` | Config | 0 | jeecg-boot-base-core-lite |
| 🔴 10 | `AutoPoiDictConfig` | Config | 0 | jeecg-boot-base-core-lite |
| 🔴 10 | `JeecgGaodeBaseConfig` | Config | 0 | jeecg-boot-base-core-lite |

## 📋 按类别分组

### API (13个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `FileDownDTO` | 0 | 10 | jeecg-boot-base-api |
| `FileUploadDTO` | 0 | 10 | jeecg-boot-base-api |
| `OnlineAuthDTO` | 3 | 8 | jeecg-boot-base-api |
| `AiragFlowDTO` | 4 | 8 | jeecg-boot-base-api |
| `DataLogDTO` | 4 | 8 | jeecg-boot-base-api |
| `BusTemplateMessageDTO` | 4 | 8 | jeecg-boot-base-api |
| `TemplateDTO` | 4 | 8 | jeecg-boot-base-api |
| `TemplateMessageDTO` | 4 | 8 | jeecg-boot-base-api |
| `IFillRuleHandler` | 4 | 8 | jeecg-boot-base-api |
| `BusMessageDTO` | 5 | 8 | jeecg-boot-base-api |
| `CommonAPI` | 6 | 5 | jeecg-boot-base-api |
| `LogDTO` | 13 | 5 | jeecg-boot-base-api |
| `MessageDTO` | 18 | 5 | jeecg-boot-base-api |

### Annotation (12个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `AutoDict` | 0 | 12 | jeecg-boot-base-api |
| `SensitiveDecode` | 1 | 10 | jeecg-boot-base-api |
| `SensitiveEncode` | 1 | 10 | jeecg-boot-base-api |
| `OnlineAuth` | 3 | 10 | jeecg-boot-base-api |
| `SensitiveField` | 4 | 10 | jeecg-boot-base-api |
| `EnumDict` | 6 | 7 | jeecg-boot-base-api |
| `IgnoreAuth` | 7 | 7 | jeecg-boot-base-api |
| `DynamicTable` | 8 | 7 | jeecg-boot-base-api |
| `Sensitive` | 10 | 7 | jeecg-boot-base-api |
| `AutoLog` | 15 | 7 | jeecg-boot-base-api |
| `PermissionData` | 21 | 4 | jeecg-boot-base-api |
| `Dict` | 63 | 4 | jeecg-boot-base-api |

### Config (41个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `AutoPoiConfig` | 0 | 10 | jeecg-boot-base-core-lite |
| `AutoPoiDictConfig` | 0 | 10 | jeecg-boot-base-core-lite |
| `JeecgGaodeBaseConfig` | 0 | 10 | jeecg-boot-base-core-lite |
| `JeecgSmsTemplateConfig` | 0 | 10 | jeecg-boot-base-core-lite |
| `Swagger2Config` | 0 | 10 | jeecg-boot-base-core-lite |
| `WebsocketFilter` | 0 | 10 | jeecg-boot-base-core-lite |
| `LowCodeUrlsEnum` | 0 | 10 | jeecg-boot-base-core-lite |
| `SignAuthConfiguration` | 0 | 10 | jeecg-boot-base-core-lite |
| `SignAuthInterceptor` | 0 | 10 | jeecg-boot-base-core-lite |
| `JeecgCloudCondition` | 1 | 8 | jeecg-boot-base-core-lite |
| `RestTemplateConfig` | 1 | 8 | jeecg-boot-base-core-lite |
| `Swagger3Config` | 1 | 8 | jeecg-boot-base-core-lite |
| `SysDictTableWhite` | 1 | 8 | jeecg-boot-base-core-lite |
| `JeecgTenantParser` | 1 | 8 | jeecg-boot-base-core-lite |
| `DynamicTableAspect` | 1 | 8 | jeecg-boot-base-core-lite |
| `DynamicDatasourceInterceptor` | 1 | 8 | jeecg-boot-base-core-lite |
| `JwtToken` | 1 | 8 | jeecg-boot-base-core-lite |
| `CustomShiroFilterFactoryBean` | 1 | 8 | jeecg-boot-base-core-lite |
| `InMemoryIgnoreAuth` | 1 | 8 | jeecg-boot-base-core-lite |
| `StaticConfig` | 2 | 8 | jeecg-boot-base-core-lite |
| `MinioConfig` | 2 | 8 | jeecg-boot-base-core-lite |
| `OssConfiguration` | 2 | 8 | jeecg-boot-base-core-lite |
| `ResourceCheckFilter` | 2 | 8 | jeecg-boot-base-core-lite |
| `IgnoreAuthPostProcessor` | 2 | 8 | jeecg-boot-base-core-lite |
| `CorsFilterCondition` | 3 | 8 | jeecg-boot-base-core-lite |
| `DruidConfig` | 3 | 8 | jeecg-boot-base-core-lite |
| `DruidWallConfigRegister` | 3 | 8 | jeecg-boot-base-core-lite |
| `UndertowCustomizer` | 3 | 8 | jeecg-boot-base-core-lite |
| `WebMvcConfiguration` | 3 | 8 | jeecg-boot-base-core-lite |
| `LowCodeModeConfiguration` | 3 | 8 | jeecg-boot-base-core-lite |
| `MybatisInterceptor` | 3 | 8 | jeecg-boot-base-core-lite |
| `ShiroConfig` | 3 | 8 | jeecg-boot-base-core-lite |
| `JwtFilter` | 3 | 8 | jeecg-boot-base-core-lite |
| `RequestBodyReserveFilter` | 4 | 8 | jeecg-boot-base-core-lite |
| `LowCodeModeInterceptor` | 4 | 8 | jeecg-boot-base-core-lite |
| `WebSocketConfig` | 5 | 8 | jeecg-boot-base-core-lite |
| `IDictTableWhiteListHandler` | 5 | 8 | jeecg-boot-base-core-lite |
| `ShiroRealm` | 5 | 8 | jeecg-boot-base-core-lite |
| `JeecgBaseConfig` | 11 | 5 | jeecg-boot-base-core-lite |
| `TenantContext` | 25 | 2 | jeecg-boot-base-core-lite |
| `MybatisPlusSaasConfig` | 27 | 2 | jeecg-boot-base-core-lite |

### Constant (27个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `CgformEnum` | 0 | 12 | jeecg-boot-base-constants |
| `OperateTypeEnum` | 0 | 12 | jeecg-boot-base-constants |
| `PositionLevelEnum` | 0 | 12 | jeecg-boot-base-constants |
| `PasswordConstant` | 1 | 10 | jeecg-boot-base-constants |
| `DateRangeEnum` | 1 | 10 | jeecg-boot-base-constants |
| `FileTypeEnum` | 1 | 10 | jeecg-boot-base-constants |
| `ModuleType` | 1 | 10 | jeecg-boot-base-constants |
| `RoleIndexConfigEnum` | 1 | 10 | jeecg-boot-base-constants |
| `Vue3MessageHrefEnum` | 1 | 10 | jeecg-boot-base-constants |
| `DynamicTableConstant` | 2 | 10 | jeecg-boot-base-constants |
| `ProvinceCityArea` | 2 | 10 | jeecg-boot-base-constants |
| `ServiceNameConstants` | 2 | 10 | jeecg-boot-base-constants |
| `ClientTerminalTypeEnum` | 2 | 10 | jeecg-boot-base-constants |
| `CommonSendStatus` | 3 | 10 | jeecg-boot-base-constants |
| `FillRuleConstant` | 3 | 10 | jeecg-boot-base-constants |
| `VxeSocketConst` | 3 | 10 | jeecg-boot-base-constants |
| `EmailTemplateEnum` | 4 | 10 | jeecg-boot-base-constants |
| `SysAnnmentTypeEnum` | 4 | 10 | jeecg-boot-base-constants |
| `DepartCategoryEnum` | 5 | 10 | jeecg-boot-base-constants |
| `NoticeTypeEnum` | 5 | 10 | jeecg-boot-base-constants |
| `TenantConstant` | 6 | 7 | jeecg-boot-base-constants |
| `WebsocketConst` | 6 | 7 | jeecg-boot-base-constants |
| `DySmsEnum` | 6 | 7 | jeecg-boot-base-constants |
| `MessageTypeEnum` | 12 | 7 | jeecg-boot-base-constants |
| `DataBaseConstant` | 13 | 7 | jeecg-boot-base-constants |
| `SymbolConstant` | 51 | 4 | jeecg-boot-base-constants |
| `CommonConstant` | 84 | 4 | jeecg-boot-base-constants |

### Controller (1个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `JeecgController` | 24 | 1 | jeecg-boot-base-core-lite |

### Entity (1个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `JeecgEntity` | 18 | 2 | jeecg-boot-base-core-lite |

### Exception (6个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `JeecgBoot401Exception` | 0 | 10 | jeecg-boot-base-core-lite |
| `JeecgBootAssertException` | 1 | 8 | jeecg-boot-base-core-lite |
| `JeecgBootExceptionHandler` | 3 | 8 | jeecg-boot-base-core-lite |
| `JeecgSqlInjectionException` | 3 | 8 | jeecg-boot-base-core-lite |
| `JeecgBootBizTipException` | 6 | 5 | jeecg-boot-base-core-lite |
| `JeecgBootException` | 40 | 2 | jeecg-boot-base-core-lite |

### Other (18个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `SensitiveSerialize` | 0 | 10 | jeecg-boot-base-core-lite |
| `SensitiveEnum` | 0 | 10 | jeecg-boot-base-core-lite |
| `QueryStringBuilder` | 0 | 10 | jeecg-boot-base-core-lite |
| `UrlMatchEnum` | 1 | 8 | jeecg-boot-base-core-lite |
| `SensitiveDataAspect` | 1 | 8 | jeecg-boot-base-core-lite |
| `BaseCommonServiceImpl` | 1 | 8 | jeecg-boot-base-core-lite |
| `UserFilterEnhance` | 2 | 8 | jeecg-boot-base-core-lite |
| `AutoLogAspect` | 3 | 8 | jeecg-boot-base-core-lite |
| `DictAspect` | 3 | 8 | jeecg-boot-base-core-lite |
| `PermissionDataAspect` | 3 | 8 | jeecg-boot-base-core-lite |
| `JeecgElasticsearchTemplate` | 3 | 8 | jeecg-boot-base-core-lite |
| `JeecgServiceImpl` | 3 | 8 | jeecg-boot-base-core-lite |
| `BaseCommonMapper` | 3 | 8 | jeecg-boot-base-core-lite |
| `MatchTypeEnum` | 4 | 8 | jeecg-boot-base-core-lite |
| `QueryCondition` | 6 | 5 | jeecg-boot-base-core-lite |
| `JeecgService` | 8 | 5 | jeecg-boot-base-core-lite |
| `BaseCommonService` | 18 | 5 | jeecg-boot-base-core-lite |
| `QueryGenerator` | 43 | 2 | jeecg-boot-base-core-lite |

### Util (58个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `MyClassLoader` | 0 | 12 | jeecg-boot-base-utils |
| `PmsUtil` | 0 | 12 | jeecg-boot-base-utils |
| `RestDesformUtil` | 0 | 12 | jeecg-boot-base-utils |
| `HttpUtils` | 0 | 12 | jeecg-boot-base-utils |
| `SignUtil` | 0 | 12 | jeecg-boot-base-utils |
| `BrowserType` | 1 | 10 | jeecg-boot-base-utils |
| `DateRangeUtils` | 1 | 10 | jeecg-boot-base-utils |
| `HTMLUtils` | 1 | 10 | jeecg-boot-base-utils |
| `ShiroThreadPoolExecutor` | 1 | 10 | jeecg-boot-base-utils |
| `AesEncryptUtil` | 1 | 10 | jeecg-boot-base-utils |
| `SecurityTools` | 1 | 10 | jeecg-boot-base-utils |
| `ObjectParseUtil` | 1 | 10 | jeecg-boot-base-utils |
| `SqlConcatUtil` | 2 | 10 | jeecg-boot-base-utils |
| `DySmsLimit` | 2 | 10 | jeecg-boot-base-utils |
| `FileDownloadUtils` | 2 | 10 | jeecg-boot-base-utils |
| `DynamicDBUtil` | 2 | 10 | jeecg-boot-base-utils |
| `AbstractQueryBlackListHandler` | 2 | 10 | jeecg-boot-base-utils |
| `JdbcSecurityUtil` | 2 | 10 | jeecg-boot-base-utils |
| `MyKeyPair` | 2 | 10 | jeecg-boot-base-utils |
| `SecurityResp` | 2 | 10 | jeecg-boot-base-utils |
| `SecuritySignReq` | 2 | 10 | jeecg-boot-base-utils |
| `SecuritySignResp` | 2 | 10 | jeecg-boot-base-utils |
| `JSqlParserAllTableManager` | 2 | 10 | jeecg-boot-base-utils |
| `JSqlParserUtils` | 2 | 10 | jeecg-boot-base-utils |
| `BodyReaderHttpServletRequestWrapper` | 2 | 10 | jeecg-boot-base-utils |
| `BrowserUtils` | 3 | 10 | jeecg-boot-base-utils |
| `Md5Util` | 3 | 10 | jeecg-boot-base-utils |
| `EncryptedString` | 3 | 10 | jeecg-boot-base-utils |
| `SecurityReq` | 3 | 10 | jeecg-boot-base-utils |
| `SensitiveInfoUtil` | 4 | 10 | jeecg-boot-base-utils |
| `JeecgDataAutorUtils` | 4 | 10 | jeecg-boot-base-utils |
| `FillRuleUtil` | 4 | 10 | jeecg-boot-base-utils |
| `MinioUtil` | 4 | 10 | jeecg-boot-base-utils |
| `DbTypeUtils` | 4 | 10 | jeecg-boot-base-utils |
| `OssBootUtil` | 4 | 10 | jeecg-boot-base-utils |
| `ResourceUtil` | 5 | 10 | jeecg-boot-base-utils |
| `DySmsHelper` | 5 | 10 | jeecg-boot-base-utils |
| `ReflectHelper` | 5 | 10 | jeecg-boot-base-utils |
| `RestUtil` | 5 | 10 | jeecg-boot-base-utils |
| `YouBianCodeUtil` | 5 | 10 | jeecg-boot-base-utils |
| `QueryRuleEnum` | 6 | 7 | jeecg-boot-base-utils |
| `SqlInjectionUtil` | 6 | 7 | jeecg-boot-base-utils |
| `FreemarkerParseFactory` | 6 | 7 | jeecg-boot-base-utils |
| `ThreadLocalDataHelper` | 6 | 7 | jeecg-boot-base-utils |
| `IpUtils` | 7 | 7 | jeecg-boot-base-utils |
| `UUIDGenerator` | 8 | 7 | jeecg-boot-base-utils |
| `DataSourceCachePool` | 8 | 7 | jeecg-boot-base-utils |
| `ImportExcelUtil` | 9 | 7 | jeecg-boot-base-utils |
| `AssertUtils` | 10 | 7 | jeecg-boot-base-utils |
| `PasswordUtil` | 11 | 7 | jeecg-boot-base-utils |
| `StrAttackFilter` | 11 | 7 | jeecg-boot-base-utils |
| `SsrfFileTypeFilter` | 16 | 7 | jeecg-boot-base-utils |
| `DateUtils` | 17 | 7 | jeecg-boot-base-utils |
| `TokenUtils` | 17 | 7 | jeecg-boot-base-utils |
| `JwtUtil` | 20 | 7 | jeecg-boot-base-utils |
| `CommonUtils` | 25 | 4 | jeecg-boot-base-utils |
| `SpringContextUtils` | 37 | 4 | jeecg-boot-base-utils |
| `oConvertUtils` | 121 | 4 | jeecg-boot-base-utils |

### VO (24个)

| 类名 | 引用次数 | 优先级 | 目标模块 |
|------|----------|--------|----------|
| `GaoDeApi` | 0 | 13 | jeecg-boot-base-api |
| `SelectTreeModel` | 1 | 11 | jeecg-boot-base-api |
| `QueryRuleVo` | 1 | 11 | jeecg-boot-base-api |
| `BaiduApi` | 1 | 11 | jeecg-boot-base-api |
| `WeiXinPay` | 1 | 11 | jeecg-boot-base-api |
| `SysFilesModel` | 2 | 11 | jeecg-boot-base-api |
| `DictModelMany` | 3 | 11 | jeecg-boot-base-api |
| `DomainUrl` | 3 | 11 | jeecg-boot-base-api |
| `ComboModel` | 4 | 11 | jeecg-boot-base-api |
| `SysCategoryModel` | 4 | 11 | jeecg-boot-base-api |
| `UserAccountInfo` | 4 | 11 | jeecg-boot-base-api |
| `SelectSqlInfo` | 4 | 11 | jeecg-boot-base-api |
| `DictQuery` | 6 | 8 | jeecg-boot-base-api |
| `SysPermissionDataRuleModel` | 7 | 8 | jeecg-boot-base-api |
| `SysDepartModel` | 8 | 8 | jeecg-boot-base-api |
| `Elasticsearch` | 8 | 8 | jeecg-boot-base-api |
| `SysUserCacheInfo` | 10 | 8 | jeecg-boot-base-api |
| `Shiro` | 11 | 8 | jeecg-boot-base-api |
| `DynamicDataSourceModel` | 12 | 8 | jeecg-boot-base-api |
| `Firewall` | 13 | 8 | jeecg-boot-base-api |
| `DictModel` | 20 | 8 | jeecg-boot-base-api |
| `Path` | 101 | 5 | jeecg-boot-base-api |
| `Result` | 110 | 5 | jeecg-boot-base-api |
| `LoginUser` | 49 | 2 | jeecg-boot-base-api |

## 🔥 高优先级类详情

### GaoDeApi

- **完整类名**: `org.jeecg.config.vo.GaoDeApi`
- **类别**: VO
- **优先级**: 13
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-api
- **文件路径**: `org\jeecg\config\vo\GaoDeApi.java`
- **代码行数**: 18

### AutoDict

- **完整类名**: `org.jeecg.common.aspect.annotation.AutoDict`
- **类别**: Annotation
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-api
- **文件路径**: `org\jeecg\common\aspect\annotation\AutoDict.java`
- **代码行数**: 24

### CgformEnum

- **完整类名**: `org.jeecg.common.constant.enums.CgformEnum`
- **类别**: Constant
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-constants
- **文件路径**: `org\jeecg\common\constant\enums\CgformEnum.java`
- **代码行数**: 168

### OperateTypeEnum

- **完整类名**: `org.jeecg.common.constant.enums.OperateTypeEnum`
- **类别**: Constant
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-constants
- **文件路径**: `org\jeecg\common\constant\enums\OperateTypeEnum.java`
- **代码行数**: 96

### PositionLevelEnum

- **完整类名**: `org.jeecg.common.constant.enums.PositionLevelEnum`
- **类别**: Constant
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-constants
- **文件路径**: `org\jeecg\common\constant\enums\PositionLevelEnum.java`
- **代码行数**: 180

### MyClassLoader

- **完整类名**: `org.jeecg.common.util.MyClassLoader`
- **类别**: Util
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-utils
- **文件路径**: `org\jeecg\common\util\MyClassLoader.java`
- **代码行数**: 106

### PmsUtil

- **完整类名**: `org.jeecg.common.util.PmsUtil`
- **类别**: Util
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-utils
- **文件路径**: `org\jeecg\common\util\PmsUtil.java`
- **代码行数**: 68

### RestDesformUtil

- **完整类名**: `org.jeecg.common.util.RestDesformUtil`
- **类别**: Util
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-utils
- **文件路径**: `org\jeecg\common\util\RestDesformUtil.java`
- **代码行数**: 121

### HttpUtils

- **完整类名**: `org.jeecg.config.sign.util.HttpUtils`
- **类别**: Util
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-utils
- **文件路径**: `org\jeecg\config\sign\util\HttpUtils.java`
- **代码行数**: 213

### SignUtil

- **完整类名**: `org.jeecg.config.sign.util.SignUtil`
- **类别**: Util
- **优先级**: 12
- **引用次数**: 0
- **目标模块**: jeecg-boot-base-utils
- **文件路径**: `org\jeecg\config\sign\util\SignUtil.java`
- **代码行数**: 66

