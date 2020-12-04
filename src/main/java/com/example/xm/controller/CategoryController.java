package com.example.xm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.xm.bean.Category;
import com.example.xm.service.CategoryService;
import com.example.xm.util.JedisUtil;
import com.example.xm.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    private static int ExpireTime = 60;   // redis中存储的过期时间60s
    @Resource
    private RedisUtil redisUtil;
    @GetMapping("/finAll")
    public  String findAll(){

        List<Category> cs=null;
        //1.获取jedis客户端
       // ValueOperations<String, Object> redisString = template.opsForValue();

        //2.判断查询的集合是否为空
       //  Set<String> categotys = jedis.zrange("categoty",0,-1);

        //获取长度
        boolean categoty = redisUtil.hasKey("categoty");
        //3.如果为空，需要从数据库查询,在将数据存入Redis
        if(!categoty){
            cs=categoryService.findAll();
            for (Category c:cs) {
                System.out.println(c);
            }
            System.out.println("从数据库查");
            //将集合存入到redis中的category的key
                redisUtil.lSet("categoty",cs);
                //jedis.zadd("categoty",cs.get(i).getCid(),cs.get(i).getCname());



        }else {
            System.out.println("从redis中查询");
            //如果不为空 将set的数据存入list
            cs=new ArrayList<Category>();
           // for (Object o:categoty1){
               /* Category category =new Category();
              category.setCname(o);
              cs.add(category);*/
            List<Object> categoty1 = redisUtil.lGet("categoty", 0, -1);
            /*
            for (Object o:categoty1) {
                System.out.println("dd"+o);
            }*/
            return JSON.toJSONString(categoty1.get(0));
            }
            //}

        return JSON.toJSONString(cs);
    }
}
