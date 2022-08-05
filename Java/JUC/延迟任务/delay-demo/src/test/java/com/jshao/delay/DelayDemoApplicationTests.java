package com.jshao.delay;

import com.jshao.delay.demo.DelayQueueExampleRedis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.Instant;

@SpringBootTest
class DelayDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testRedis() throws InterruptedException {

        Jedis jedis = jedisPool.getResource();
        // 延迟 30s 执行（30s 后的时间）
        long delayTime = Instant.now().plusSeconds(30).getEpochSecond();
        jedis.zadd(DelayQueueExampleRedis._KEY, delayTime, "order_1");
        // 继续添加测试数据
        jedis.zadd(DelayQueueExampleRedis._KEY, Instant.now().plusSeconds(2).getEpochSecond(), "order_2");
        jedis.zadd(DelayQueueExampleRedis._KEY, Instant.now().plusSeconds(2).getEpochSecond(), "order_3");
        jedis.zadd(DelayQueueExampleRedis._KEY, Instant.now().plusSeconds(7).getEpochSecond(), "order_4");
        jedis.zadd(DelayQueueExampleRedis._KEY, Instant.now().plusSeconds(10).getEpochSecond(), "order_5");
        // 开启延迟队列
        DelayQueueExampleRedis.doDelayQueue(jedis);
    }

}
