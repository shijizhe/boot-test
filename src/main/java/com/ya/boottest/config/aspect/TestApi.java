package com.ya.boottest.config.aspect;

import java.lang.annotation.*;

/**
 * 测试注解
 */
@Target(ElementType.METHOD) // 方法注解
@Retention(RetentionPolicy.RUNTIME) // 运行时可见
@Documented
public @interface TestApi {

}
