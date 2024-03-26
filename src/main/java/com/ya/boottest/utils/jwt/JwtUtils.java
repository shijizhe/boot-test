package com.ya.boottest.utils.jwt;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.ya.boottest.utils.constants.date.DateConstants.DATE_FORMAT_DATE;

/**
 * <p>
 *  Jwt 工具类
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/19 14:47
 */
@Component
public class JwtUtils {

    private static String secret;
    @Value("${ya-app.auth.jwt.secret:aBcD1234eFgH5678IjKl90MnOpQrStUvWxYz4567}")
    public void setSecret(String secret){
        JwtUtils.secret = secret;
    }

    private static Long expiration;
    @Value("${ya-app.auth.jwt.expiration:1800}")
    public void setExpiration(Long expiration) {
        JwtUtils.expiration = expiration;
    }

    private static String notBefore;
    @Value("${ya-app.auth.jwt.not-before:2024-01-01}")
    public void setNotBefore(String notBefore){
        JwtUtils.notBefore = notBefore;
    }

    /**
     * 生成token
     * @param userId userId
     * @return jwt-token
     */
    public static String generateToken(String userId) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("userId", userId);
        return Jwts.builder()
                .setClaims(claimsMap)
                .setId(UUID.randomUUID().toString(true))
                .setIssuer("Mr.Ya")
                .setSubject("Ya User Center.auth")
                .setAudience("Ya App Series.user")
                .setNotBefore(notBeforeDate())
                .setIssuedAt(nowDate())
                .signWith(signKey())
                .compact();
    }

    public static boolean validateToken(String token, String userId) {
        if(StrUtil.isBlank(userId)){
            return false;
        }
        final String claimUserId = extractUserId(token);
        return userId.equals(claimUserId);
    }

    private static SecretKey signKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解析token字符串中的加密信息, 提取所有声明的方法
     * @param token jwt-token
     * @return Claims
     */
    private static Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public static String extractUserId(String token) {
        return extractClaim(token, Claims -> Claims.get("userId", String.class));
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date expirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private static Date nowDate() {
        return new Date(System.currentTimeMillis());
    }

    private static Date notBeforeDate() {
        return DateUtil.parse(notBefore, DATE_FORMAT_DATE);
    }


}
