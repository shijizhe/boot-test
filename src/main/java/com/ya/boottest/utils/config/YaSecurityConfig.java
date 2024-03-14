package com.ya.boottest.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用basic明文验证
                .httpBasic().disable()
                // 禁用csrf保护
                .csrf().disable()
                // 禁用默认登出页
                .logout().disable()
                // 禁用session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( authorizeHttpRequests ->
                        authorizeHttpRequests
                                // 允许OPTIONS请求访问
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                // 允许登录接口访问
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                                // 允许匿名接口访问
                        .requestMatchers("/anon/**").permitAll()
                                // 允许swagger访问
                        .requestMatchers( "/swagger-ui/**").permitAll()
                        .requestMatchers( "/doc.html/**").permitAll()
                        .requestMatchers( "*/api-docs/**").permitAll()
                                .anyRequest().authenticated());
        return http.build();
    }

    /**
     * 使用BCrypt加密密码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
