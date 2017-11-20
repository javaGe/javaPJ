package com.ggf.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * java连接redis数据库
 */
public class Conn2redis {
    // 定义jedis对象
    private Jedis jedis = null;

    /**
     * 初始化连接
     */
    @Before
    public void init() {
        // 连接远程redis库，IP和端口
        jedis = new Jedis("39.108.146.127", 6379);
        //System.out.println(jedis);

        //权限认证(远程库密码)
        //jedis.auth("admin");
    }

    /**
     * redis操作字符串
     */
    @Test
    public void testString() {

        //------添加数据---set(key, value)
        jedis.set("name", "ggf"); // key为name, value为ggf
        System.out.println(jedis.get("name")); // 获取值 ggf

        //-----字符串拼接---append(key, value)
        jedis.append("name", " very good!");
        System.out.println(jedis.get("name")); // ggf very good

        //-----设置多个键值对---mset(key, value,....)
        jedis.mset("name","ggf", "age","22", "gender","man");
        System.out.println(jedis.get("name")+"-"+jedis.get("age")); // ggf-22

        //-----删除某个键---del(key)
        jedis.del("name");

    }

    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        //----添加数据----
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "ggf");
        map.put("age", "22");
        map.put("gender", "man");

        jedis.hmset("user", map);
        // 第一个参数是存入redis中map对象的key，
        // 后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> list = jedis.hmget("user", "name","age","gender");
        System.out.println(list); // [ggf, 22, man]

        //删除map中的某个键值
         jedis.hdel("user","age");
         System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
         System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
         System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
         System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
         System.out.println(jedis.hvals("user"));//返回map对象中的所有value

        // 通过迭代器输出集合中的值
        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }
    }

    /**
     * redis操作List
     */
    @Test
    public void testList() {
        // 移除列表中的内容
        jedis.del("user");
        // 向key为user中存放数据
        jedis.lpush("user", "ggf");
        jedis.lpush("user", "22");
        jedis.lpush("user", "man");

        // 按照长度范围输出 jedis.lrange(key, start, end)
        System.out.println(jedis.lrange("user", 0, -1));

    }

    /**
     * redis操作Set
     */
    @Test
    public void testSet() {
        jedis.del("user");
        // 添加
        jedis.sadd("user", "ggf");
        jedis.sadd("user", "cwy");

        //移除noname
        jedis.srem("user","who");
        System.out.println(jedis.smembers("user"));//获取所有加入的value
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user"));
        System.out.println(jedis.scard("user"));//返回集合的元素个数
    }
}
