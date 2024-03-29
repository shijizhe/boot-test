package com.ya.boottest.config.security;

import com.ya.boottest.utils.result.BaseResult;
import com.ya.boottest.utils.result.ResultEnum;
import com.ya.boottest.utils.servlet.ServletUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *  认证异常处理
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/28 16:01
 */
@Component
@Slf4j
public class YaAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("YaAuthenticationEntryPoint commence 认证过程中遇到异常：{}", authException.toString());
        ServletUtils.renderResult(response, new BaseResult<>(ResultEnum.FAILED_UNAUTHORIZED.code, "Security auth failed."));
    }
}
