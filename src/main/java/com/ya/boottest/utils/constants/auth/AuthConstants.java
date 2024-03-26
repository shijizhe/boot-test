package com.ya.boottest.utils.constants.auth;

/**
 * <p>
 *  身份认证相关常量
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/26 11:53
 */
public class AuthConstants {

    public static final String TOKEN_HEADER = "Ya-Auth-Token";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String REDIS_KEY_AUTH_TOKEN = "auth:token:";
    public static final String REDIS_KEY_AUTH_USER_DETAIL = "auth:user:detail:";
}
