package com.ruoyi.common.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.ruoyi.common.core.text.Convert;

/**
 * @author 终于白发始于青丝
 * @Classname ServletUtils
 * @Description 类方法说明：客户端工具类
 * @Date 2022/3/25 下午 13:46
 */
public class ServletUtils {
    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name) {
        return Convert.toBool(getRequest().getParameter(name));
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        return Convert.toBool(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname renderString
     * @Description 类方法说明：将字符串渲染到客户端
     * @Return 返回值：void
     * @Params javax.servlet.http.HttpServletResponse response 渲染对象
     * @Params java.lang.String string 待渲染的字符串
     * @Date 2022/3/25 下午 14:01
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json" );
            response.setCharacterEncoding("utf-8" );
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isAjaxRequest
     * @Description 类方法说明：是否是Ajax异步请求
     * @Return 返回值：boolean
     * @Params javax.servlet.http.HttpServletRequest request
     * @Date 2022/3/25 下午 14:02
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept" );
        if (accept != null && accept.contains("application/json" )) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With" );
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest" )) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json" , ".xml" )) {
            return true;
        }

        String ajax = request.getParameter("__ajax" );
        return StringUtils.inStringIgnoreCase(ajax, "json" , "xml" );
    }
}
