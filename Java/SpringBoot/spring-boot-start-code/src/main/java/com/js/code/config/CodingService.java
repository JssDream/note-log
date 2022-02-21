package com.js.code.config;


/**
 * Created by Js on 2022/2/18 .
 **/
public class CodingService {

    private CodingProperties properties;

    public CodingService() {
    }

    public CodingService(CodingProperties properties) {
        this.properties = properties;
    }

    public void sayHello(){
        System.out.println("大家好，我叫: " + properties.getName() + ", 今年" + properties.getAge() + "岁"
                + ", 性别: " + properties.getSex());
    }
}
