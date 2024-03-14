package com.ya.boottest.manage.test.controller;

import com.ya.boottest.utils.result.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @PostMapping("/shiwuceshi")
    @Operation(summary = "shiwuceshi", description = "shiwuceshi")
    public Object shiwuceshi(@RequestParam(value = "fruitCode") String fruitCode) {
        return BaseResult.success();
    }
}
