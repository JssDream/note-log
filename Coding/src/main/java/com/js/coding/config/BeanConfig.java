package com.js.coding.config;

import com.js.coding.bean.City;
import com.js.coding.bean.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Js on 2022/2/18 .
 **/

@Configuration
public class BeanConfig {
    /*@Bean
    public City city() {
        City city = new City();
        city.setCityName("千岛湖");
        return city;
    }*/
    @Bean
    @ConditionalOnBean(name = "city")
    public People people(City city) {
        //这里如果city实体没有成功注入 这里就会报空指针
        city.setCityCode(301701);
        return new People("小小", 3, city);
    }
}
