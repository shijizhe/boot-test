package com.ya.boottest;

import com.ya.boottest.autocode.user.service.IYaUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootTestApplicationTests {
    @Resource
    IYaUserService iYaUserService;


    @Test
    void contextLoads() {
        System.out.println("我是一个测试类------INFO-----");
        System.out.println(iYaUserService.list());
    }

}
