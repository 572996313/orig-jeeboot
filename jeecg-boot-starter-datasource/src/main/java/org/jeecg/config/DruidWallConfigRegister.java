package org.jeecg.config;

import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Druid Wall配置注册器
 * <p>允许SELECT语句的WHERE子句中存在永真条件</p>
 *
 * @author jeecg
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.datasource.druid", name = "enable", havingValue = "true", matchIfMissing = true)
public class DruidWallConfigRegister implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof WallFilter) {
            WallFilter wallFilter = (WallFilter) bean;
            WallConfig wallConfig = wallFilter.getConfig();
            if (wallConfig != null) {
                // 允许一次执行多条语句
                wallConfig.setMultiStatementAllow(true);
                // 允许SELECT语句WHERE子句永真条件（用于动态查询）
                wallConfig.setSelectWhereAlwayTrueCheck(false);
                log.info("Druid Wall配置已更新: multiStatementAllow=true, selectWhereAlwayTrueCheck=false");
            }
        }
        return bean;
    }
}