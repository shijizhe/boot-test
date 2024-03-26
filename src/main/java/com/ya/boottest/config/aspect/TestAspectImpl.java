package com.ya.boottest.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * 切面
 *
 */
@Aspect
@Component
public class TestAspectImpl {
    private static final Logger logger = LoggerFactory.getLogger(TestAspectImpl.class);

    @Pointcut(value = "@annotation(com.ya.boottest.config.aspect.TestApi)")
    public void testApiPointcut() {
    }

    @Before("testApiPointcut()")
    public void beforeInnerAnonApi(JoinPoint point){
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        logger.error("beforeTestApi 测试注解接口：调用方法名称：{}。方法参数：{}", methodName, args);
    }

}
