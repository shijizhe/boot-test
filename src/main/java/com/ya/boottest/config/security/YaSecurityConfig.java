package com.ya.boottest.config.security;

import com.ya.boottest.config.security.filter.YaLoginFilter;
import com.ya.boottest.config.security.filter.YaTokenFilter;
import com.ya.boottest.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 *  Spring Security 配置文件
 * </p>
 *
 * @author Ya Shi
 * @since 2024/2/29 11:27
 */
@Configuration
@EnableWebSecurity // 开启网络安全注解
public class YaSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${ya-app.auth.jwt.expiration:1800}")
    private Long expiration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用basic明文验证
                .httpBasic().disable()
                // 禁用csrf保护
                .csrf().disable()
                // 禁用session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 身份认证过滤器
                .authenticationManager(authenticationManager(authenticationConfiguration))
                .authenticationProvider(new YaAuthenticationProvider())
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                // 允许OPTIONS请求访问
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                // 允许登录/注册接口访问
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                                // 允许匿名接口访问
                                .requestMatchers("/anon/**").permitAll()
                                // 允许swagger访问
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/doc.html/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/webjars/**").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterAt(new YaLoginFilter(authenticationManager(authenticationConfiguration), redisUtils, expiration), UsernamePasswordAuthenticationFilter.class)
                // 让校验Token的过滤器在身份认证过滤器之前
                .addFilterBefore(new YaTokenFilter(redisUtils, expiration), YaLoginFilter.class)
                // 禁用默认登出页
                .logout().disable();
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/webjars/**")
                .requestMatchers("/swagger-ui/**", "/doc.html/**", "/v3/api-docs/**")
                .requestMatchers("/anon/**");
    }

    /**
     * 使用BCrypt加密密码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
