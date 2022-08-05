package com.jshao.delay.demo;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 1. 无限循环实现延迟任务
 * <p>
 * Created by Js on 2022/8/5 .
 **/
@Slf4j
public class DelayTaskExampleLoop {
    // 存放定时任务
    private static Map<String, Long> _TaskMap = new HashMap<>();

    public static void main(String[] args) {
        log.info("程序启动时间：{}", LocalDateTime.now());
        // 添加定时任务
        _TaskMap.put("task-1", Instant.now().plusSeconds(3).toEpochMilli()); // 延迟 3s

        // 调用无限循环实现延迟任务
        loopTask();
    }

    /**
     * 无限循环实现延迟任务
     */
    public static void loopTask() {
        Long itemLong = 0L;
        while (true) {
            Iterator<Map.Entry<String, Long>> it = _TaskMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = it.next();
                itemLong = (Long) entry.getValue();
                // 有任务需要执行
                if (Instant.now().toEpochMilli() >= itemLong) {
                    // 延迟任务，业务逻辑执行
                    log.info("执行任务：{} ，执行时间：{}",entry.getKey(), LocalDateTime.now());
                    // 删除任务
                    _TaskMap.remove(entry.getKey());
                }
            }
        }
    }
}
