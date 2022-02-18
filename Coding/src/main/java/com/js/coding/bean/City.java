package com.js.coding.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Js on 2022/2/18 .
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市code
     */
    private Integer cityCode;
}
