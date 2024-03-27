package com.ya.boottest.utils.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

/**
 * <p>
 * 用户认证工具类
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/26 11:25
 */
public class UserAuthUtils {

    public static String getUserId() {
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            return null;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.isNull(userDetails)) {
            return null;
        }
        return userDetails.getUsername();
    }
}
