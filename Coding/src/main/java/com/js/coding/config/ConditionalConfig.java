package com.js.coding.config;

import com.js.coding.bean.LinuxCondition;
import com.js.coding.bean.SystemBean;
import com.js.coding.bean.WindowsCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Js on 2022/2/18 .
 **/
@Slf4j
@Configuration
public class ConditionalConfig {
    /**
     * 如果 WindowsCondition的实现方法返回true，则注入这个bean
     */
    @Bean("windows")
    @Conditional({WindowsCondition.class})
    public SystemBean systemWin() {
        log.info("ConditionalConfig方法注入 windows实体");
        return new SystemBean("windows系统","002");
    }
    /**
     * 如果LinuxCondition的实现方法返回true，则注入这个bean
     */
    @Bean("linux")
//    @Conditional({LinuxCondition.class})
    public SystemBean systemLinux() {
        log.info("ConditionalConfig方法注入 linux实体");
        return new SystemBean("Linux系统","001");
    }
}
