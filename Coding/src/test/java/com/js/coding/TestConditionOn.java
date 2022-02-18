package com.js.coding;

import com.js.coding.bean.People;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Js on 2022/2/18 .
 **/
@SpringBootTest(classes = CodingApplication.class)
//@RunWith(SpringRunner.class)
public class TestConditionOn {

    @Autowired(required = false)
    private People people;

    @Test
    public void test() {
        System.out.println("= = = = = = = = = = = = = ");
        System.out.println("people = " + people);
        System.out.println("= = = = = = = = = = = = = ");
    }
}
