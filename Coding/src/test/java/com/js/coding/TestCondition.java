package com.js.coding;

import com.js.coding.bean.SystemBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Js on 2022/2/18 .
 **/
@SpringBootTest(classes = CodingApplication.class)
public class TestCondition {

    @Autowired
    private SystemBean windows;
    @Autowired
    private SystemBean linux;
    @Autowired
    private SystemBean mac;

    @Test
    public void test() {
        if (windows != null) {
            System.out.println("windows = " + windows);
        }
        if (linux != null) {
            System.out.println("Linux = " + linux);
        }
    }
}
