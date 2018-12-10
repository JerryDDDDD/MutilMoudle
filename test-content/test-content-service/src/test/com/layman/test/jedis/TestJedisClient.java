package com.layman.test.jedis;

import com.layman.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJedisClient {

    @Test
    public void testdj(){
        //1. 初始化Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient bean = context.getBean(JedisClient.class);
        //2. 获取实现类
        bean.set("jedisClientKey","jedisclietKey");
        //3. 调用方法操作
        System.out.println(bean.get("jedisClientKey"));
    }
}
