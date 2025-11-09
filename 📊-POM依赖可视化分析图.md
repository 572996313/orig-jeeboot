# 📊 POM依赖可视化分析图

> **生成时间**：2025-11-09 16:14  
> **项目**：JeecgBoot 4.0.0-SNAPSHOT  
> **分析目标**：全局依赖关系、问题定位、优化路径

---

## 🎯 快速导航

- [整体架构图](#整体架构图)
- [问题依赖链路](#问题依赖链路)
- [模块编译状态](#模块编译状态)
- [优化路径图](#优化路径图)

---

## 📐 整体架构图

### 四层依赖结构

```mermaid
graph TB
    subgraph Layer4["🚀 应用层 (Application Layer)"]
        App[jeecg-system-start<br/>启动模块]
    end
    
    subgraph Layer3["💼 业务层 (Business Layer)"]
        SystemBiz[jeecg-system-biz<br/>❌ 78个编译错误<br/>18个文件受影响]
        ModuleAirag[jeecg-boot-module-airag<br/>⚠️ 依赖冲突]
    end
    
    subgraph Layer2["🔌 API层 (API Layer)"]
        SystemLocalAPI[jeecg-system-local-api<br/>✅ 编译成功]
    end
    
    subgraph Layer1["🏗️ 基础设施层 (Infrastructure Layer)"]
        direction TB
        
        subgraph CoreModules["核心模块组"]
            BaseCore[jeecg-boot-base-core<br/>✅ 编译成功]
            BaseAPI[jeecg-boot-base-api<br/>✅ 编译成功]
            BaseUtils[jeecg-boot-base-utils<br/>✅ 编译成功]
            BaseConstants[jeecg-boot-base-constants<br/>✅ 编译成功]
        end
        
        subgraph StarterModules["Starter模块组 (10个)"]
            StarterWeb[starter-web ✅]
            StarterSecurity[starter-security ✅]
            StarterDatasource[starter-datasource ✅]
            StarterMyBatis[starter-mybatis-plus ✅]
            StarterOSS[starter-oss ✅]
            StarterExcel[starter-excel ✅]
            StarterOthers[...其他6个 ✅]
        end
    end
    
    subgraph External["📦 外部依赖"]
        Common383[jeecg-boot-common:3.8.3<br/>⚠️ 不完整jar包<br/>缺失5个成员]
        SpringBoot[Spring Boot 3.3.1]
        MyBatisPlus[MyBatis-Plus 3.5.7]
        Shiro[Shiro 1.13.0]
        OtherLibs[其他第三方库...]
    end
    
    App --> SystemBiz
    SystemBiz --> SystemLocalAPI
    SystemBiz --> BaseCore
    SystemBiz -.显式依赖.-> Common383
    
    SystemLocalAPI --> BaseCore
    SystemLocalAPI -.显式依赖.-> Common383
    
    BaseCore --> BaseAPI
    BaseCore --> BaseUtils
    BaseCore -.必需依赖.-> Common383
    
    BaseAPI --> BaseConstants
    
    SystemBiz --> StarterWeb
    SystemBiz --> StarterSecurity
    SystemBiz --> StarterMyBatis
    
    StarterWeb --> BaseCore
    StarterSecurity --> BaseCore
    StarterDatasource --> BaseCore
    StarterMyBatis --> BaseCore
    
    BaseCore --> SpringBoot
    StarterMyBatis --> MyBatisPlus
    SystemBiz --> Shiro
    
    style SystemBiz fill:#ffcccc,stroke:#ff0000,stroke-width:3px
    style ModuleAirag fill:#ffffcc,stroke:#ffaa00,stroke-width:2px
    style Common383 fill:#ffffcc,stroke:#ffaa00,stroke-width:3px
    style SystemLocalAPI fill:#ccffcc
    style BaseCore fill:#ccffcc
    style BaseAPI fill:#ccffcc
    style BaseUtils fill:#ccffcc
    style BaseConstants fill:#ccffcc
```

---

## 🔍 问题依赖链路

### system-biz编译失败的依赖传递路径

```mermaid
graph LR
    subgraph "问题根源"
        Common[jeecg-boot-common:3.8.3<br/>⚠️ 缺失5个成员]
    end
    
    subgraph "第1层传递"
        BaseCore[base-core<br/>✅ 编译成功<br/>因为不直接使用缺失成员]
    end
    
    subgraph "第2层传递"
        LocalAPI[system-local-api<br/>✅ 编译成功<br/>因为不直接使用缺失成员]
    end
    
    subgraph "第3层 - 失败点"
        Biz[system-biz<br/>❌ 编译失败<br/>直接使用了缺失成员]
    end
    
    Common -->|optional依赖| BaseCore
    Common -->|显式3.8.3| LocalAPI
    Common -->|显式3.8.3| Biz
    
    BaseCore -->|传递| LocalAPI
    LocalAPI -->|传递| Biz
    BaseCore -->|显式| Biz
    
    style Common fill:#ffcccc
    style Biz fill:#ffcccc
    style BaseCore fill:#ccffcc
    style LocalAPI fill:#ccffcc
```

### 缺失成员的影响范围

```mermaid
graph TD
    subgraph Missing["❌ jeecg-boot-common:3.8.3 缺失内容"]
        Constant[MybatisPlusSaasConfig<br/>.OPEN_SYSTEM_TENANT_CONTROL]
        Method1[ImportExcelUtil<br/>.imporReturnRes]
        Method2[ImportExcelUtil<br/>.importDateSave]
        Method3[ImportExcelUtil<br/>.importDateSaveOne]
        Method4[SensitiveInfoUtil<br/>.handlerObject]
    end
    
    subgraph Files["📄 受影响文件 (18个)"]
        direction TB
        File1[SysDictController<br/>SysRoleController<br/>SysUserController<br/>SysTenantController<br/>等14个Controller/Service]
    end
    
    subgraph Errors["⚠️ 编译错误统计"]
        Err1[常量缺失: 52处]
        Err2[方法缺失: 22处]
        Total[总计: 74处 → 78个错误]
    end
    
    Constant --> Err1
    Method1 --> Err2
    Method2 --> Err2
    Method3 --> Err2
    Method4 --> Err2
    
    Err1 --> File1
    Err2 --> File1
    
    File1 --> Total
    
    style Missing fill:#ffcccc
    style Errors fill:#ffffcc
    style Files fill:#ffeeee
```

---

## 📊 模块编译状态全景图

```mermaid
graph TB
    subgraph Success["✅ 编译成功 (20/21)"]
        direction LR
        S1[constants]
        S2[base-api]
        S3[base-utils]
        S4[base-core-lite]
        S5[starter-security]
        S6[starter-datasource]
        S7[starter-mybatis]
        S8[starter-oss]
        S9[starter-api-doc]
        S10[starter-excel]
        S11[starter-desensitization]
        S12[starter-communication]
        S13[starter-elasticsearch]
        S14[starter-web]
        S15[base-core-aggregator]
        S16[父POM]
        S17[base-core]
        S18[module-system父]
        S19[system-api父]
        S20[system-local-api]
    end
    
    subgraph Failed["❌ 编译失败 (1/21)"]
        F1[system-biz<br/>78个编译错误]
    end
    
    Success -.前置依赖.-> Failed
    
    style Success fill:#ccffcc
    style Failed fill:#ffcccc
```

### 编译顺序与耗时

```mermaid
gantt
    title Maven Reactor 编译顺序与状态
    dateFormat X
    axisFormat %s
    
    section 基础模块
    constants         :done, 0, 6s
    base-api          :done, 6, 23s
    base-utils        :done, 29, 11s
    base-core-lite    :done, 40, 11s
    
    section Starter模块
    starter-security  :done, 51, 11s
    starter-datasource:done, 62, 11s
    starter-mybatis   :done, 73, 22s
    starter-oss       :done, 95, 11s
    starter-api-doc   :done, 106, 11s
    starter-excel     :done, 117, 11s
    starter-desensi   :done, 128, 1s
    starter-comm      :done, 129, 11s
    starter-es        :done, 140, 11s
    starter-web       :done, 151, 11s
    
    section 聚合模块
    base-core-agg     :done, 162, 1s
    parent-pom        :done, 163, 1s
    base-core         :done, 164, 6s
    
    section 系统模块
    module-system     :done, 170, 1s
    system-api        :done, 171, 1s
    system-local-api  :done, 172, 22s
    system-biz        :crit, 194, 9s
```

---

## 🛠️ 优化路径图

### 解决方案决策树

```mermaid
graph TD
    Start([78个编译错误<br/>system-biz失败])
    
    Start --> Q1{是否有<br/>GitHub访问权限?}
    
    Q1 -->|是| Sol1[✅ 方案1<br/>从GitHub获取完整源码]
    Q1 -->|否| Q2{是否有<br/>反编译工具?}
    
    Q2 -->|是| Sol2[⚠️ 方案2<br/>反编译jar补全代码]
    Q2 -->|否| Q3{是否愿意<br/>手动实现?}
    
    Q3 -->|是| Sol3[❌ 方案3<br/>手动实现缺失类<br/>不推荐]
    Q3 -->|否| Sol4[⚠️ 方案4<br/>降级到3.8.3]
    
    Sol1 --> Action1[1. git clone官方仓库<br/>2. checkout v3.8.3<br/>3. 复制3个文件<br/>4. 重新编译]
    Sol2 --> Action2[1. 下载JD-GUI<br/>2. 反编译jar<br/>3. 导出源码<br/>4. 修复错误<br/>5. 重新编译]
    Sol3 --> Action3[1. 分析业务逻辑<br/>2. 猜测实现<br/>3. 单元测试<br/>4. 集成测试<br/>风险极高!]
    Sol4 --> Action4[1. 修改所有pom版本<br/>2. 放弃4.0.0新特性<br/>3. 重新编译]
    
    Action1 --> Result1[✅ 成功<br/>2小时<br/>风险低]
    Action2 --> Result2[⚠️ 可能成功<br/>4小时<br/>风险中]
    Action3 --> Result3[❌ 高风险<br/>1天+<br/>不推荐]
    Action4 --> Result4[✅ 成功<br/>1小时<br/>但失去升级意义]
    
    style Start fill:#ffcccc
    style Sol1 fill:#ccffcc
    style Sol2 fill:#ffffcc
    style Sol3 fill:#ffcccc
    style Sol4 fill:#ffffcc
    style Result1 fill:#ccffcc
    style Result2 fill:#ffffcc
    style Result3 fill:#ffcccc
    style Result4 fill:#ffffcc
```

### 推荐方案1执行流程

```mermaid
sequenceDiagram
    participant Dev as 开发者
    participant Git as GitHub
    participant Local as 本地项目
    participant Maven as Maven构建
    
    Dev->>Git: 1. git clone jeecg-boot
    Git-->>Dev: 克隆完成
    
    Dev->>Git: 2. git checkout v3.8.3
    Git-->>Dev: 切换到3.8.3标签
    
    Dev->>Local: 3. 复制3个文件
    Note over Local: MybatisPlusSaasConfig.java<br/>ImportExcelUtil.java<br/>SensitiveInfoUtil.java
    
    Dev->>Maven: 4. mvn clean install base-core
    Maven-->>Dev: ✅ base-core编译成功
    
    Dev->>Maven: 5. mvn clean install system-biz
    Maven-->>Dev: ✅ system-biz编译成功<br/>78个错误已解决
    
    Note over Dev,Maven: 🎉 问题解决!
```

---

## 📈 优化前后对比

### 依赖清理前后

```mermaid
graph LR
    subgraph Before["❌ 优化前"]
        direction TB
        B1[system-biz<br/>依赖3次common]
        B2[system-local-api<br/>依赖2次common]
        B3[base-core<br/>依赖1次common]
        B4[jeecg-boot-common:3.8.3<br/>外部依赖 ⚠️]
        
        B1 --> B2
        B1 --> B3
        B1 -.显式.-> B4
        B2 --> B3
        B2 -.显式.-> B4
        B3 -.optional.-> B4
    end
    
    subgraph After["✅ 优化后"]
        direction TB
        A1[system-biz<br/>无外部依赖]
        A2[system-local-api<br/>无外部依赖]
        A3[base-core<br/>包含所有代码]
        
        A1 --> A2
        A1 --> A3
        A2 --> A3
    end
    
    Before -.优化.-> After
    
    style Before fill:#ffeeee
    style After fill:#eeffee
    style B4 fill:#ffcccc
```

### 性能提升预期

