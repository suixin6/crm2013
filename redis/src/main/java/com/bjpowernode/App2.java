package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App2 {

    public static void main(String[] args) {
//        初始化连接池
        JedisPool pool = null;

        try {
//        获得连接池
            pool = RedisUtils.open("127.0.0.1", 6379);
//        获得连接对象
            Jedis jedis = pool.getResource();
//        清除缓存中的所有数据
            jedis.flushAll();
//        使用hest类型存储数据
            jedis.hset("website", "taobao", "www.taobao.com");
//        使用 hget获取数据
            String n = jedis.hget("website", "taobao");
//        打印
            System.out.println(n);

            System.out.println("==================================");

            Map<String, String> map = new HashMap<>();
//        使用map封装批量存入
            map.put("id", "001");
            map.put("num", "002");
            map.put("num1", "003");
//        插入map    hmset 可以一次存多个值
            jedis.hmset("student", map);
//        批量取值
            List<String> s = jedis.hmget("student", "id", "num", "num1");
            for (String s1 : s) {
                System.out.println(s1 + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RedisUtils.close();
        }


    }


//    public static void main(String[] args) {
//        JedisPool pool = null;
//        try {
//            pool = RedisUtils.open("127.0.0.1", 6379);
//            Jedis jedis = pool.getResource();
//            jedis.flushAll();
//            jedis.hset("hset1", "str1", "abc");
//            String str1 = jedis.hget("hset1", "str1");
//            System.out.println(str1);
//            System.out.println("------------------------------------");
//
//            Map<String, String> map = new HashMap<>();
//            map.put("id", "a001");
//            map.put("name", "zs");
//            map.put("age", "22");
//            jedis.hmset("student", map);
//            List<String> sList = jedis.hmget("student", "id", "name", "age");
//            for (String str : sList) {
//                System.out.println(str);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            ;
//        } finally {
//            RedisUtils.close();
//        }
//    }
}
