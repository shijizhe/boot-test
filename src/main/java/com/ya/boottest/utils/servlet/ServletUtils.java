package com.ya.boottest.utils.servlet;

import com.alibaba.fastjson.JSON;
import com.ya.boottest.utils.result.BaseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * Servlet 工具类
 * </p>
 *
 * @author Ya Shi
 * @since 2024/1/4 14:29
 */

@Slf4j
public class ServletUtils {

    /**
     * 获取Attributes
     *
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if(Objects.isNull(attributes)){
            log.error("ServletUtils 获取到的RequestAttributes为空");
            throw new RuntimeException("ServletUtils 获取到的RequestAttributes为空");
        }
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取session
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取response
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            log.error("ServletUtils renderString 输出流写入异常：" + e);
            throw new RuntimeException("输出流写入异常:"  + e);
        }
    }

    /**
     * 将BaseResult渲染到客户端
     * @param response 渲染对象
     * @param result BaseResult
     */
    public static void renderResult(HttpServletResponse response, BaseResult<?> result) {
        renderString(response, JSON.toJSONString(result));
    }

}
