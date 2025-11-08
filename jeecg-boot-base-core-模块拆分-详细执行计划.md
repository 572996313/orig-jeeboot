
# jeecg-boot-base-core 模块拆分 - 详细执行计划

**生成时间**: 2025-11-08  
**分析基于**: jeecg-boot-base-core 实际代码结构  
**总文件数**: 146+ Java源文件

---

## 📊 总体概览

根据实际代码分析,jeecg-boot-base-core 模块包含以下内容:

| 类别 | 数量 | 拆分目标模块 |
|-----|------|------------|
| 常量类 | 12个 | constants |
| 枚举类 | 15个 | constants |
| API/DTO/VO | 29个 | api |
| 基础工具类 | 45个 | utils |
| 配置类 | 30个 | 各Starter |
| 切面类 | 4个 | starter-web |
| 异常类 | 6个 | api + starter-web |
| 基础服务 | 5个 | core-lite |

---

## 🎯 模块拆分架构图

```
jeecg-boot-base/
├── jeecg-boot-base-constants (27个类)
│   └── 常量 + 枚举 (零依赖)
│
├── jeecg-boot-base-api (29个类)
│   └── API接口 + DTO + VO + 异常
│
├── jeecg-boot-base-utils (45个类)
│   └── 纯Java工具类 (不依赖Spring)
│
├── jeecg-boot-base-core-lite (16个类)
│   └── 最小Spring集成 + CRUD基类
│
├── jeecg-boot-starter-security (16个类)
│   └── Shiro + JWT + 签名认证
│
├── jeecg-boot-starter-datasource (6个类)
│   └── Druid + 动态数据源
│
├── jeecg-boot-starter-mybatis-plus (11个类)
│   └── MyBatis增强 + 租户隔离
│
├── jeecg-boot-starter-oss (4个类)
│   └── MinIO + OSS
│
├── jeecg-boot-starter-api-doc (2个类)
│   └── Swagger + Knife4j
│
├── jeecg-boot-starter-excel (4个类)
│   └── EasyPoi导入导出
│
├── jeecg-boot-starter-desensitization (8个类)
│   └── 数据脱敏
│
├── jeecg-boot-starter-communication (9个类)
│   └── 短信 + 邮件 + WebSocket
│
├── jeecg-boot-starter-elasticsearch (2个类)
│   └── ES集成
│
├── jeecg-boot-starter-web (18个类)
│   └── Web增强 + AOP + 异常处理
│
└── jeecg-boot-base-core (聚合模块)
    └── 依赖所有子模块,向后兼容
```

---

## 📝 详细迁移清单

### Phase 1.1: jeecg-boot-base-constants (27个文件)

**迁移清单**:

```
org.jeecg.common.constant/
├── CommonConstant.java
├── CommonSendStatus.java
├── DataBaseConstant.java
├── DynamicTableConstant.java
├── FillRuleConstant.java
├── PasswordConstant.java
├── ProvinceCityArea.java
├── ServiceNameConstants.java
├── SymbolConstant.java
├── TenantConstant.java
├── VxeSocketConst.java
└── WebsocketConst.java

org.jeecg.common.constant.enums/
├── CgformEnum.java
├── ClientTerminalTypeEnum.java
├── DateRangeEnum.java
├── DepartCategoryEnum.java
├── DySmsEnum.java
├── EmailTemplateEnum.java
├── FileTypeEnum.java
├── MessageTypeEnum.java
├── ModuleType.java
├── NoticeTypeEnum.java
├── OperateTypeEnum.java
├── PositionLevelEnum.java
├── RoleIndexConfigEnum.java
├── SysAnnmentTypeEnum.java
└── Vue3MessageHrefEnum.java
```

**依赖**: 无 (纯Java)  
**预计工作量**: 2人日

---

### Phase 1.2: jeecg-boot-base-api (29个文件)

**迁移清单**:

```
org.jeecg.common.api/
├── CommonAPI.java
├── vo/Result.java
└── dto/
    ├── AiragFlowDTO.java
    ├── DataLogDTO.java
    ├── FileDownDTO.java
    ├── FileUploadDTO.java
    ├── LogDTO.java
    ├── OnlineAuthDTO.java
    └── message/
        ├── BusTemplateMessageDTO.java
        ├── MessageDTO.java
        ├── TemplateDTO.java
        └── TemplateMessageDTO.java

org.jeecg.common.system.vo/
├── ComboModel.java
├── DictModel.java
├── DictModelMany.java
├── DictQuery.java
├── DynamicDataSourceModel.java
├── LoginUser.java
├── SelectTreeModel.java
├── SysCategoryModel.java
├── SysDepartModel.java
├── SysFilesModel.java
├── SysPermissionDataRuleModel.java
├── SysUserCacheInfo.java
└── UserAccountInfo.java

org.jeecg.common.exception/
├── JeecgBoot401Exception.java
├── JeecgBootAssertException.java
├── JeecgBootBizTipException.java
├── JeecgBootException.java
└── JeecgSqlInjectionException.java

org.jeecg.config.vo/
├── BaiduApi.java
├── DomainUrl.java
├── Elasticsearch.java
├── Firewall.java
├── GaoDeApi.java
├── Path.java
├── Shiro.java
└── WeiXinPay.java
```

**依赖**: jeecg-boot-base-constants  
**预计工作量**: 3人日

---

### Phase 1.3: jeecg-boot-base-utils (45个文件)

**迁移清单**:

```
org.jeecg.common.util/
├── AssertUtils.java
├── BrowserType.java
├── BrowserUtils.java
├── CommonUtils.java
├── DateRangeUtils.java
├── DateUtils.java
├── HTMLUtils.java
├── IpUtils.java
├── Md5Util.java
├── MyClassLoader.java
├── oConvertUtils.java
├── PasswordUtil.java
├── PmsUtil.java
├── ReflectHelper.java
├── RestDesformUtil.java
├── SqlInjectionUtil.java
├── UUIDGenerator.java
└── YouBianCodeUtil.java

org.jeecg.common.util.encryption/
├── AesEncryptUtil.java
└── EncryptedString.java

org.jeecg.common.util.security/
├── AbstractQueryBlackListHandler.java
├── JdbcSecurityUtil.java
├── SecurityTools.java
└── entity/
    ├── MyKeyPair.java
    ├── SecurityReq.java
    ├── SecurityResp.java
    ├── SecuritySignReq.java
    └── SecuritySignResp.java

org.jeecg.common.util.filter/
├── SsrfFileTypeFilter.java
└── StrAttackFilter.java

org.jeecg.common.util.sqlparse/
├── JSqlParserAllTableManager.java
├── JSqlParserUtils.java
└── vo/SelectSqlInfo.java

org.jeecg.common.util.dynamic.db/
├── DbTypeUtils.java
└── FreemarkerParseFactory.java

org.jeecg.common.system.query/
├── MatchTypeEnum.java
├── QueryCondition.java
├── QueryGenerator.java
└── QueryRuleEnum.java

org.jeecg.common.util.superSearch/
├── ObjectParseUtil.java
├── QueryRuleEnum.java
└── QueryRuleVo.java

org.jeecg.common.system.util/
└── JwtUtil.java (纯工具方法部分)
```

**依赖**: 
- jeecg-boot-base-constants
- Hutool
- JSqlParser

**预计工作量**: 5人日

---

## 🚀 实施建议

### 步骤1: 立即开始Phase 1

Phase 1的三个模块(constants、api、utils)是纯Java模块,不依赖Spring,可以立即开始创建:

1. **创建Maven模块结构**
2. **复制源代码文件**
3. **配置pom.xml依赖**
4. **编写单元测试**
5. **发布到本地Maven仓库**

### 步骤2: 验证基础模块

确保Phase 1的三个模块可以:
- 独立编译通过
- 在非Spring项目中使用
- 单元测试覆盖率>80%

### 步骤3: 逐步创建Starter模块

按优先级顺序创建:
1. core-lite (最高优先级)
2. security (核心功能)
3. datasource + mybatis-plus (数据访问)
4. 其他Starter按需创建

### 步骤4: 创建聚合模块

最后创建jeecg-boot-base-core聚合模块,确保向后兼容。

---

## ⚠️ 关键注意事项

### 需要重构的混合类

以下类包含Spring依赖,需要拆分:

1. **DySmsHelper** → 拆分为工具方法 + Spring服务
2. **FileDownloadUtils** → 拆分为工具方法 + Spring服务
3. **ImportExcelUtil** → 拆分为工具方法 + Spring服务
4. **MinioUtil** → 拆分为工具方法 + Spring服务
5. **TokenUtils** → 拆分为工具方法 + Redis服务
6. **DynamicDBUtil** → 拆分为工具方法 + DataSource服务

### 包路径策略

**选项A**: 保持原包路径 (推荐)
- 优点: 向后兼容,现有代码无需修改
- 缺点: 包路径较长

**选项B**: 优化包路径
- 优点: 更清晰的包结构
- 缺点: 需要迁移指南和代码修改

**建议**: 优先使用选项A,后续版本可逐步迁移到选项B

---

## 📅 时间规划

| 阶段 | 任务 | 工作量 | 预计时间 |
|-----|------|--------|---------|
| Phase 1 | 基础工具层 | 10人日 | 2周 |
| Phase 2.1 | core-lite | 4人日 | 1周 |
| Phase 2.2-2.4 | 核心Starter | 15人日 | 3周 |
| Phase 2.5-2.11 | 其他Starter | 24人日 | 5周 |
| Phase 3 | 重构混合组件 | 5人日 | 1周 |
| Phase 4 | 聚合模块+测试 | 7人日 | 2周 |
| **总计** | | **65人日** | **14周** |

---

## ✅ 下一步行动

### 立即执行

1. **创建 jeecg-boot-base-constants 模块**
   - 在项目根目录创建新的Maven模块
   - 复制27个常量和枚举类
   - 配置pom.xml
   - 测试编译

2. **创建 jeecg-boot-base-api 模块**
   - 创建Maven模块
   - 复制29个API/DTO/VO/异常类
   - 添加对constants的依赖
   - 测试编译

3. **创建 jeecg-boot-base-utils 模块**
   - 创建Maven模块
   - 复制45个工具类
   - 添加必要的第三方依赖
   - 编写单元测试

### 需要确认的问题

1. ✅ 是否需要保持原有的包路径结构?
2. ✅ 新模块的groupId和artifactId命名规范?
3. ✅ 版本号策略 (建议从4.0.0开始)?
4. ✅ Maven仓库发布策略?

---

## 📞 后续支持

完成当前分析后,建议:

1. 