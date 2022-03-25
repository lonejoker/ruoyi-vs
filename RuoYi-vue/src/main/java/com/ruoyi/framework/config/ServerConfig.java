package com.ruoyi.framework.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.ServletUtils;

/**
 * @author 终于白发始于青丝
 * @Classname ServerConfig
 * @Description 类方法说明：服务相关配置
 * @Date 2022/3/25 下午 14:55
 */
@Component
public class ServerConfig {
    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     * @return 服务地址
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
