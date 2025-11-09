# Security Starter 备份文件说明

## 备份原因

Phase 17.1 首次编译发现72个错误，主要问题：

### 1. 缺少依赖类（来自其他未完成的模块）
- `LoginUser` - 用户登录信息类（来自system模块）
- `RedisUtil` - Redis工具类（来自core-lite或cache模块）
- `TokenUtils` - Token工具类（需要RedisUtil）
- `CacheConstant` - 缓存常量（来自constants模块）
- `TenantContext` - 租户上下文（来自mybatis-plus starter）
- `MybatisPlusSaasConfig` - MyBatis租户配置（来自mybatis-plus starter）

### 2. Servlet API不兼容
- Shiro 1.13.0 使用 `javax.servlet.*`
- Spring Boot 3 使用 `jakarta.servlet.*`
- 无法同时满足两套API

### 3. 缺少Lombok和日志支持
- 所有 `@Slf4j` 注解的类找不到 `log` 变量
- 需要添加Lombok依赖

## 备份文件列表

| 文件名 | 备份原因 | 依赖的缺失类 | 优先级 |
|--------|---------|-------------|--------|
| ShiroRealm.java.bak | 核心认证授权类，依赖最多 | LoginUser, RedisUtil, SysUserCacheInfo, TenantContext, MybatisPlusSaasConfig | P1 |
| JwtUtil.java.bak | JWT工具类，依赖LoginUser | LoginUser, CacheConstant | P1 |
| JwtFilter.java.bak | JWT过滤器，servlet API冲突 | TokenUtils, LoginUser, javax.servlet.Filter | P1 |
| ResourceCheckFilter.java.bak | 资源检查过滤器，依赖Redis | RedisUtil, LoginUser, javax.servlet.Filter | P2 |
| ShiroConfig.java.bak | Shiro主配置，依赖Redis和Filter | RedisUtil, JwtFilter, ResourceCheckFilter | P1 |
| CustomShiroFilterFactoryBean.java.bak | 自定义Filter工厂 | javax.servlet.Filter | P2 |
| IgnoreAuthPostProcessor.java.bak | 免认证注解处理器 | InMemoryIgnoreAuth | P3 |

## 恢复策略

### 阶段1：完成所有基础Starter（当前）
- ✅ 先编译通过简单配置类（JeecgSecurityAutoConfiguration, Properties）
- ✅ 保留简单的工具类（JwtToken.java, IgnoreAuth.java）
- ⏳ 完成其他Starter模块（datasource, mybatis-plus, cache等）

### 阶段2：解决依赖问题
1. **完成mybatis-plus starter** - 提供TenantContext, MybatisPlusSaasConfig
2. **完成cache/redis starter** - 提供RedisUtil
3. **完成system模块** - 提供LoginUser, SysUserCacheInfo
4. **添加CacheConstant到constants模块**

### 阶段3：解决Servlet API冲突
- **方案A**: 升级到Shiro 2.0（如果支持jakarta.servlet）
- **方案B**: 创建适配器类，桥接javax和jakarta
- **方案C**: 使用Spring Security替代Shiro（重大架构调整）

### 阶段4：恢复备份文件
按优先级恢复：
1. P1: ShiroConfig, ShiroRealm, JwtUtil, JwtFilter
2. P2: ResourceCheckFilter, CustomShiroFilterFactoryBean
3. P3: IgnoreAuthPostProcessor

## 当前保留的文件

以下文件没有依赖问题，已保留在模块中：
- ✅ JwtToken.java - JWT Token POJO类
- ✅ IgnoreAuth.java - 免认证注解
- ✅ InMemoryIgnoreAuth.java - 内存存储免认证URL
- ✅ JeecgSecurityAutoConfiguration.java - 自动配置类
- ✅ JeecgSecurityProperties.java - 配置属性类

## 编译目标

**Phase 17.1目标**: 
- ✅ 创建模块结构
- ✅ 备份有问题的类
- ⏳ 编译通过（仅包含简单类）
- ⏳ 安装到本地Maven仓库

**后续恢复**: 在Phase 20+完成所有依赖后恢复备份文件

---

**创建时间**: 2025-11-08  
**创建者**: RooCode AI  
**模块版本**: jeecg-boot-starter-security-3.7.1