package com.js.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://www.jianshu.com/p/37a46741a2ad
 *
 * Created by Js on 2022/2/18 .
 **/
@Configuration
@EnableConfigurationProperties(CodingProperties.class)
@ConditionalOnClass(CodingService.class)
@ConditionalOnProperty(prefix = "spring.code", value = "enabled", matchIfMissing = true)
public class CodeAutoConfiguration {
    @Autowired
    private CodingProperties properties;

    @Bean
    @ConditionalOnMissingBean(CodingService.class)  // 当容器中没有指定Bean的情况下，自动配置CodingService类
    public CodingService personService(){
        CodingService personService = new CodingService(properties);
        return personService;
    }
}
