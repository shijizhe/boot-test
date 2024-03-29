package com.ya.boottest.config.security;

import com.ya.boottest.utils.redis.RedisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * <p>
 *  登出成功
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/28 10:47
 */
@Slf4j
public class YaLogoutSuccessHandler implements LogoutSuccessHandler {
    private final RedisUtils redisUtils;

    public YaLogoutSuccessHandler(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        throw new UsernameNotFoundException("xxxx");
       /* final String authorization = request.getHeader(AuthConstants.TOKEN_HEADER);
        // 1.请求头中没有携带token
        if (StrUtil.isBlank(authorization)) {
            ServletUtils.renderResult(response, BaseResult.successWithMessage("没有登录信息，无需退出"));
            return;
        }

        // 携带token
        final String token = authorization.replace(AuthConstants.TOKEN_PREFIX, "");
        String userId;
        // 2.提供的token异常
        try {
            userId =  JwtUtils.extractUserId(token);
        }catch (Exception e){
            log.error("YaLogoutHandler logout 解析jwt异常：{}", e.toString());
            ServletUtils.renderResult(response, new BaseResult<>(ResultEnum.FAILED_UNAUTHORIZED.code, "凭证异常"));
            return;
        }
        // 清空Redis
        redisUtils.delete(REDIS_KEY_AUTH_TOKEN + userId);
        log.info("YaLogoutSuccessHandler onLogoutSuccess");
        ServletUtils.renderResult(response, BaseResult.successWithMessage("退出登录成功"));*/
    }
}
