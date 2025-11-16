### Jeecg-boot-base-bundle 模块化重构迁移计划

| 源路径 (Source Path) | 目标模块 (Target Module) | 迁移理由 (Reason) |
| :--- | :--- | :--- |
| **API & VO (通用)** | | |
| `api/vo/Result.java` | `jeecg-boot-base-api` | 框架标准的API响应结构体，是所有API模块的基础，应放在最底层的API定义模块。 |
| `api/dto/LogDTO.java` | `jeecg-boot-base-api` | 日志的数据传输对象，作为服务间契约的一部分，适合放在API模块。 |
| `api/CommonAPI.java` | `jeecg-boot-base-api` | 定义了模块间通用的API接口，是典型的API模块内容。 |
| `system/vo/DictModel.java` | `jeecg-boot-base-api` | 字典模型，广泛用于前端下拉框和数据展示，是通用的API数据结构。 |
| `system/vo/DictModelMany.java` | `jeecg-boot-base-api` | 字典模型（多选），同上，属于通用API数据结构。 |
| `system/vo/ComboModel.java` | `jeecg-boot-base-api` | 下拉框模型，通用前端组件数据结构，应放入API模块。 |
| `system/vo/SelectTreeModel.java` | `jeecg-boot-base-api` | 树形选择控件模型，通用前端组件数据结构，应放入API模块。 |
| `system/vo/DictQuery.java` | `jeecg-boot-base-api` | 字典查询参数对象，属于API层的数据契约。 |
| `system/vo/SysCategoryModel.java` | `jeecg-boot-base-api` | 系统分类模型，作为通用数据结构在多个模块中使用，适合放入基础API包。 |
| `system/vo/SysDepartModel.java` | `jeecg-boot-base-api` | 部门模型，同上，属于多模块共享的通用VO。 |
| `system/vo/SysFilesModel.java` | `jeecg-boot-base-api` | 文件模型，同上，属于多模块共享的通用VO。 |
| **Core (核心工具与基础类)** | | |
| `system/query/**` | `jeecg-boot-base-core` | 动态查询条件生成器 (`QueryGenerator`) 及其相关类，是框架的数据查询核心能力，不依赖Web环境。 |
| `system/base/entity/JeecgEntity.java` | `jeecg-boot-base-core` | 基础实体类，定义了通用字段（如创建时间），是ORM映射的基础，属于核心模型。 |
| `system/util/CommonUtils.java` | `jeecg-boot-base-core` | 通用工具类，提供了各种静态方法，无特定业务或环境依赖。 |
| `system/util/FillRuleUtil.java` | `jeecg-boot-base-core` | 编码生成规则工具类，是独立的核心功能。 |
| `system/util/ResourceUtil.java` | `jeecg-boot-base-core` | 资源文件读取工具，是底层的基础功能。 |
| `util/dynamic/db/FreemarkerParseFactory.java` | `jeecg-boot-base-core` | Freemarker模板解析工具，属于通用的文本处理功能，不依赖特定业务。 |
| `constant/ProvinceCityArea.java` | `jeecg-boot-base-core` | 省市区静态数据，作为基础数据，放入Core模块便于各业务模块共享。 |
| `constant/enums/**` | `jeecg-boot-base-api` | 消息类型等枚举，通常作为API的一部分对外暴露，应定义在API模块。 |
| `aspect/DictAspect.java` & `annotations` | `jeecg-boot-base-core` | 字典切面，用于自动翻译数据，是核心的数据处理能力，不强依赖Web环境。 |
| `system/annotation/EnumDict.java` | `jeecg-boot-base-core` | 枚举字典注解，配合字典切面使用，属于核心功能。 |
| **Web (Web环境相关)** | | |
| `exception/JeecgBootExceptionHandler.java` | `jeecg-boot-starter-web` | 全局异常处理器，强依赖Spring MVC的`@RestControllerAdvice`，是Web层的标准组件。 |
| `system/base/controller/JeecgController.java` | `jeecg-boot-starter-web` | 基础Controller，封装了服务调用和响应逻辑，是Web层的通用基类。 |
| `aspect/AutoLogAspect.java` & `annotation` | `jeecg-boot-starter-web` | 自动日志切面，用于记录HTTP请求日志，依赖Web环境获取请求信息。 |
| `system/util/RestUtil.java` | `jeecg-boot-starter-web` | REST请求工具类，用于发起HTTP请求，是Web环境下的常用工具。 |
| `system/util/RestDesformUtil.java` | `jeecg-boot-starter-web` | 处理表单设计的REST工具，与Web请求相关。 |
| **Security (安全认证)** | | |
| `system/util/JwtUtil.java` | `jeecg-boot-starter-security` | JWT Token生成和校验工具，是无状态认证的核心，属于安全领域。 |
| `util/TokenUtils.java` | `jeecg-boot-starter-security` | Token相关工具类，与用户认证状态紧密相关，属于安全领域。 |
| `system/vo/LoginUser.java` | `jeecg-boot-starter-security` | 登录用户信息模型，是安全认证体系的核心VO。 |
| `system/vo/SysUserCacheInfo.java` | `jeecg-boot-starter-security` | 用户缓存信息，用于存储和管理用户会话，属于安全领域。 |
| `system/vo/UserAccountInfo.java` | `jeecg-boot-starter-security` | 用户账户信息VO，用于安全认证流程。 |
| `aspect/PermissionDataAspect.java` & `annotation` | `jeecg-boot-starter-security` | 数据权限切面，用于在查询时注入数据权限过滤，是安全体系的一部分。 |
| `system/vo/SysPermissionDataRuleModel.java` | `jeecg-boot-starter-security` | 数据权限规则模型，配合数据权限切面使用。 |
| **MyBatis-Plus (持久层)** | | |
| `system/base/service/**` | `jeecg-boot-starter-mybatis-plus` | `JeecgService`及其实现类，封装了基于MyBatis-Plus的通用CRUD操作，应归属到MyBatis-Plus的starter中。 |
| **Datasource (多数据源)** | | |
| `system/util/db/**` | `jeecg-boot-starter-datasource` | 动态数据源相关的工具类和缓存池，应统一归属到多数据源starter。 |
| `util/dynamic/db/DataSourceCachePool.java` | `jeecg-boot-starter-datasource` | （重复类）动态数据源缓存池，应合并并迁移到多数据源starter。 |
| `system/vo/DynamicDataSourceModel.java` | `jeecg-boot-starter-datasource` | 动态数据源的模型，用于配置和管理数据源。 |
| **Excel (独立功能)** | | |
| `excel/ImportExcelUtil.java` | `jeecg-boot-starter-excel` | Excel导入工具类，是Excel功能的核心实现，不依赖Web。 |
| `util/ImportExcelUtil.java` | `jeecg-boot-starter-excel` | （重复类）Excel导入工具类，应合并并统一迁移。 |
| `controller/JeecgExcelController.java` | `jeecg-boot-starter-excel-web` (新) | 结合了Web和Excel功能的控制器，应独立为一个依赖`jeecg-boot-starter-web`和`jeecg-boot-starter-excel`的新模块。 |