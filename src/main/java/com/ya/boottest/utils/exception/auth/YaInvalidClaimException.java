package com.ya.boottest.utils.exception.auth;

import com.ya.boottest.utils.exception.YaBaseException;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Jwt Claim无效异常
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/19 16:14
 */
@NoArgsConstructor
public class YaInvalidClaimException extends YaBaseException {

    public YaInvalidClaimException(String message) {
        super(message);
    }
}
