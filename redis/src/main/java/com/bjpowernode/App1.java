package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class App1 {
    public static void main(String[] args) {
        JedisPool pool = null;

        try {
//            使用连接池活动一个连接池对象
            pool = RedisUtils.open("127.0.0.1", 6379);
//            通过getResource可以获得一个jedis连接对象
            Jedis jedis = pool.getResource();
            jedis.flushAll();
            jedis.set("sex", "男");
            jedis.set("address", "北京");
            String sex = jedis.get("sex");
            System.out.println(sex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

//        用完关闭不然一直开着
            RedisUtils.close();
        }


//        JedisPool pool = null;
//
//        try {
//            pool = RedisUtils.open("127.0.0.1", 6379);
//            Jedis jedis = pool.getResource();
//            jedis.flushAll();
//            jedis.set("str1", "aaa");
//            String str1 = jedis.get("str1");
//            System.out.println(str1);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ;
//        } finally {
//            RedisUtils.close();
//        }
    }
}
