package com.ya.boottest.manage.fruit.controller;

import com.ya.boottest.autocode.fruit.entity.Fruit;
import com.ya.boottest.autocode.fruit.service.IFruitService;
import com.ya.boottest.utils.redis.RedisUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Ya Shi
 * @since 2024/1/29 11:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
class FruitControllerTest {

    @Resource
    IFruitService fruitService;

    @Autowired
    RedisUtils redisUtils;

    @Test
    void contextLoads() {
        redisUtils.set("test:key:name", "xxxxxx");
        redisUtils.set("test:key:name2", "yyyyy");
        System.out.println(redisUtils.getString("test:key:name2"));
    }

    @Test
    void contextLoads2() {
        List<Fruit> list = fruitService.list();
        System.out.println(list);
    }



}
