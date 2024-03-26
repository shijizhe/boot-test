package com.ya.boottest.utils.auth;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 *  用户认证工具类
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/26 11:25
 */
public class UserAuthUtils {
    public static String getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "1";
    }
}
