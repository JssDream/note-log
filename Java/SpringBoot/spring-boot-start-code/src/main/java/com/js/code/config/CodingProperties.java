package com.js.code.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Js on 2022/2/18 .
 **/
@ConfigurationProperties(prefix = "spring.code")
@Data
public class CodingProperties {
        // 姓名
        private String name;
        // 年龄
        private int age;
        // 性别
        private String sex = "M";
}
