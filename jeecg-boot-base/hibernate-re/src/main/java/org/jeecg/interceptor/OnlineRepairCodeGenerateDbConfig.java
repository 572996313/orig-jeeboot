//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.interceptor;

import com.alibaba.druid.filter.config.ConfigTools;
import java.util.ResourceBundle;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.online.config.dirc.d;
import org.jeecgframework.codegenerate.database.CodegenDatasourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("onlineRepairCodeGenerateDbConfig")
@ConditionalOnMissingClass({"org.jeecg.config.init.CodeGenerateDbConfig"})
public class OnlineRepairCodeGenerateDbConfig {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(OnlineRepairCodeGenerateDbConfig.class);
    @Value("${spring.datasource.dynamic.datasource.master.url:}")
    private String url;
    @Value("${spring.datasource.dynamic.datasource.master.username:}")
    private String username;
    @Value("${spring.datasource.dynamic.datasource.master.password:}")
    private String password;
    @Value("${spring.datasource.dynamic.datasource.master.driver-class-name:}")
    private String driverClassName;
    @Value("${spring.datasource.dynamic.datasource.master.druid.public-key:}")
    private String publicKey;
    private static ResourceBundle database_bundle;

    @Bean({"initOnlineRepairCodeGenerateDbConfig"})
    public OnlineRepairCodeGenerateDbConfig initOnlineRepairCodeGenerateDbConfig() {
        if (database_bundle == null && StringUtils.isNotBlank(this.url)) {
            if (StringUtils.isNotBlank(this.publicKey)) {
                try {
                    this.password = ConfigTools.decrypt(this.publicKey, this.password);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(" 代码生成器数据库连接，数据库密码解密失败！");
                }
            }

            CodegenDatasourceConfig.initDbConfig(this.driverClassName, this.url, this.username, this.password);
        }

        return null;
    }

    static {
        try {
            database_bundle = d.d("jeecg/jeecg_database");
            if (database_bundle == null) {
                database_bundle = ResourceBundle.getBundle("jeecg/jeecg_database");
            }
        } catch (Exception var1) {
        }

    }
}
