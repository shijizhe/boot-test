package com.ya.boottest.config.security.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ya.boottest.config.security.YaUserDetailService;
import com.ya.boottest.utils.constants.auth.AuthConstants;
import com.ya.boottest.utils.jwt.JwtUtils;
import com.ya.boottest.utils.redis.RedisUtils;
import com.ya.boottest.utils.result.BaseResult;
import com.ya.boottest.utils.result.ResultEnum;
import com.ya.boottest.utils.servlet.ServletUtils;
import com.ya.boottest.utils.spring.SpringContextUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ya.boottest.utils.constants.auth.AuthConstants.REDIS_KEY_AUTH_TOKEN;
import static com.ya.boottest.utils.constants.auth.AuthConstants.REDIS_KEY_AUTH_USER_DETAIL;

/**
 * <p>
 *  每次请求过滤token
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/21 16:52
 */
@Slf4j
public class YaTokenFilter extends OncePerRequestFilter {

    private final RedisUtils redisUtils;

    private final Long expiration;

    private static final Set<String> WHITE_LIST = Stream.of(
            "/auth/register",
            "/auth/login"
            ).collect(Collectors.toSet());

    public YaTokenFilter(RedisUtils redisUtils, Long expiration) {
        this.redisUtils = redisUtils;
        this.expiration = expiration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("YaTokenFilter doFilterInternal start");
        final String authorization = request.getHeader(AuthConstants.TOKEN_HEADER);
        log.info("YaTokenFilter ya-auth-token: {}", authorization);

        // 白名单
        if (WHITE_LIST.contains(request.getServletPath())) {
            chain.doFilter(request, response);
            return;
        }

        // 1.请求头中没有携带token
        if (StrUtil.isBlank(authorization)) {
            ServletUtils.renderResult(response, new BaseResult<>(ResultEnum.FAILED_UNAUTHORIZED));
            return;
        }

        // 携带token
        final String token = authorization.replace(AuthConstants.TOKEN_PREFIX, "");
        String userId;
        // 2.提供的token异常
        try {
            userId =  JwtUtils.extractUserId(token);
        }catch (Exception e){
            log.error("YaTokenFilter doFilterInternal 解析jwt异常：{}", e.toString());
            ServletUtils.renderResult(response, new BaseResult<>(ResultEnum.FAILED_UNAUTHORIZED.code, "凭证异常"));
            return;
        }

        String redisToken = redisUtils.getString(AuthConstants.REDIS_KEY_AUTH_TOKEN + userId);
        // 3.token过期
        if(StrUtil.isBlank(redisToken)){
            ServletUtils.renderResult(response, new BaseResult<>(ResultEnum.FAILED_UNAUTHORIZED.code, "登录已过期，请重新登录过期。"));
            return;
        }

        // 4.提供的token是合法的，但是redis中的token又被使用登录功能重新刷新了一下，导致不一致。
        if(!Objects.equals(redisToken, token)){
            ServletUtils.renderResult(response, new BaseResult<>(ResultEnum.FAILED_UNAUTHORIZED.code, "账号在别处登陆。"));
            return;
        }
        // token续期
        redisUtils.set(REDIS_KEY_AUTH_TOKEN + userId, token, expiration);
        // 获取用户信息和权限
        String userDetailStr =  redisUtils.getString(AuthConstants.REDIS_KEY_AUTH_USER_DETAIL + userId);
        UserDetails userDetails;
        if(Objects.isNull(userDetailStr)){
            userDetails = yaUserDetailService().loadUserByUsername(userId);
            redisUtils.set(REDIS_KEY_AUTH_USER_DETAIL + userId, JSON.toJSONString(userDetails), 1, TimeUnit.DAYS);
        }else{
            userDetails = initUser(userDetailStr);
        }
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails, userDetails.getPassword(), userDetails.getAuthorities()
                )
        );
        log.info("YaTokenFilter doFilterInternal end");
        chain.doFilter(request, response);
    }

    private YaUserDetailService yaUserDetailService(){
        return SpringContextUtils.getBean(YaUserDetailService.class);
    }

    private User initUser(String userJsonStr){
        JSONObject userJson = JSON.parseObject(userJsonStr);

        String userId = userJson.getString("username");
        JSONArray authArray = userJson.getJSONArray("authorities");

        List<GrantedAuthority> authorities = new ArrayList<>(authArray.size());
        for(int i=0; i< authArray.size();i++){
            JSONObject authObj = authArray.getJSONObject(i);
            authorities.add(new SimpleGrantedAuthority(authObj.getString("authority")));
        }
        return new User(userId, "[PROTECTED]", authorities);
    }

}
