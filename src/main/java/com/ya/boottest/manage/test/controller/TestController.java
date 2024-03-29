package com.ya.boottest.manage.test.controller;

import com.ya.boottest.utils.auth.UserAuthUtils;
import com.ya.boottest.utils.redis.RedisUtils;
import com.ya.boottest.utils.result.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试使用controller
 * </p>
 *
 * @author Ya Shi
 * @since 2023/8/12 14:43
 */

@Slf4j
@RestController
@RequestMapping("/test")
@Tag(name = "TestController", description = "测试controller")
public class TestController {
    @Autowired
    RedisUtils redisUtils;

    @GetMapping("/testApi")
    @Operation(summary = "testApi", description = "测试使用-直接返回成功")
    @PreAuthorize("hasAuthority('ya_fruit_list')")
    public Object testApi(){
        String userId = UserAuthUtils.getUserId();
        System.out.println(userId);
        return BaseResult.success(userId);
    }

    @GetMapping("/testApi2")
    @Operation(summary = "testApi2", description = "测试使用2-直接返回成功")
    @PreAuthorize("hasAuthority('test_test_123456')")
    public Object testApi2(){
        String userId = UserAuthUtils.getUserId();
        System.out.println(userId);
        return BaseResult.success(userId);
    }
}
