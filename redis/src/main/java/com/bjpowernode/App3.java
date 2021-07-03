package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class App3 {
    public static void main(String[] args) {
//        初始化连接池
        JedisPool pool = null;
        try {
//            获得连接池对象
            pool = RedisUtils.open("127.0.0.1", 6379);
//              获得连接对象
            Jedis jedis = pool.getResource();
            jedis.flushAll();
//            开启事务   multi会返回一个Transaction对象
            Transaction tran = jedis.multi();
            tran.set("oo", "OOO");
            tran.set("aa", "AAA");
            tran.set("cc", "CCC");
//    退出事务并保存存储的值   因为事务返回的值时各种类型的 所以exec方法会返回一个List<Object>用来接收存取值的状态
            List<Object> list = tran.exec();
            for (Object l : list) {
                System.out.println(l);
            }
            String o = jedis.get("oo");
            String a = jedis.get("aa");
            String c = jedis.get("cc");
            System.out.println(a + o + c);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            关闭连接池
            RedisUtils.close();
        }


//        JedisPool pool = null;
//        try {
//            pool = RedisUtils.open("127.0.0.1", 6379);
//            Jedis jedis = pool.getResource();
//            jedis.flushAll();
//            Transaction tran = jedis.multi();
//            tran.set("str1", "aaa");
//            tran.set("str2", "bbb");
//            //事务处理
//            List<Object> oList = tran.exec();
//            System.out.println("事务结果打印");
//            for (Object o : oList) {
//                System.out.println(o);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            ;
//        } finally {
//            RedisUtils.close();
//        }
    }
}
