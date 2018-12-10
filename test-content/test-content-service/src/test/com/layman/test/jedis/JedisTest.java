package com.layman.test.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    //测试单击版
    @Test
    public void testJedis(){
        //1.创建Jedis对象 需要指定连接的地址和端口
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.直接操作Redis set
        jedis.set("key1234","value");
        System.out.println(jedis.get("key1234"));
        //3.关闭Jedis
        jedis.close();
    }

    @Test
    public void testJedisPool(){
        //1. 创建JedisPool 对象需要之指定端口和地址
        JedisPool jedisPool = new JedisPool("127.0.0.1",6379);
        //2. 获取Jedis的对象
        Jedis jedis = jedisPool.getResource();
        //3. 直接操作Jedis
        jedis.set("keypool","keypool");
        System.out.println(jedis.get("keypool"));
        //4. 关闭Jedis（释放资源到连接池）
        jedis.close();
        //5. 关闭连接池
        jedisPool.close();
    }

    /**
     * 集群的操作
     */
//    @Test
//    public void testJedisCluster(){
//        Set<HostAndPort> nodes = new HashSet<>();
//        nodes.add(new HostAndPort("127.0.0.1",6370));
//        nodes.add(new HostAndPort("127.0.0.1",6371));
//        nodes.add(new HostAndPort("127.0.0.1",6372));
//        nodes.add(new HostAndPort("127.0.0.1",6373));
//        nodes.add(new HostAndPort("127.0.0.1",6374));
//        nodes.add(new HostAndPort("127.0.0.1",6375));
//        nodes.add(new HostAndPort("127.0.0.1",6376));
//        //创建JedisCluster对象
//        JedisCluster cluster = new JedisCluster(nodes);
//        //直接根据Jediscluster对象操作Jedis集群
//        cluster.sadd("keyCluster","value");
//        System.out.println(cluster.get("keyCluster"));
//        // 3. 关闭Jediscluster对象(是在应用系统关闭的时候关闭) 封装了连接池
//        cluster.close();
//    }
}
