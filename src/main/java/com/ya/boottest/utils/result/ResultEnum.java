package com.ya.boottest.utils.result;

import lombok.Getter;

/**
 * <p>created time:2023/6/10 15:34</p>
 * <p>des:
 *   通用返回结果 枚举
 * </p>
 *
 * @author Ya Shi
 */

@Getter
public enum ResultEnum {
    SUCCESS(200,"[Ya]请求成功"),

    FAILED_BAD_REQUEST(400, "[Ya]Bad Request,错误的请求"),
    FAILED_UNAUTHORIZED(401, "[Ya]Unauthorized,未登录"),
    FAILED_FORBIDDEN(403, "[Ya]Forbidden,未经授权"),

    FAILED(500, "[Ya]Internal server error,服务器内部错误");

    public final int code;
    public final String message;

    ResultEnum(int code, String message){
        this.code=code;
        this.message=message;
    }
}
