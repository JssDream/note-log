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
public class SystemBean {
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 系统code
     */
    private String systemCode;
}
