package com.ya.boottest.config.security;

import com.ya.boottest.utils.result.BaseResult;
import com.ya.boottest.utils.result.ResultEnum;
import com.ya.boottest.utils.servlet.ServletUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * 鉴权异常处理
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/28 16:06
 */
@Component
@Slf4j
public class YaAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("YaAccessDeniedHandler handle 鉴权过程中遇到异常：{}", accessDeniedException.toString());
        ServletUtils.renderResult(response, new BaseResult<>(ResultEnum.FAILED_FORBIDDEN.code, "鉴权失败：" + accessDeniedException.getMessage()));
    }
}
