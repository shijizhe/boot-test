package com.ya.boottest.utils.exception;

/**
 * <p>
 *  异常基础类
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/19 16:42
 */
public class YaBaseException extends RuntimeException{

    public YaBaseException() {
        super();
    }

    public YaBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public YaBaseException(String message) {
        super(message);
    }

    public YaBaseException(Throwable cause) {
        super(cause);
    }
}
