package com.ruoyi.common.enums;

import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.Nullable;

/**
 * @author 终于白发始于青丝
 * @Classname HttpMethod
 * @Description 类方法说明：请求方式
 * @Date 2022/3/25 下午 13:24
 */
public enum HttpMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

    static {
        for (HttpMethod httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    @Nullable
    public static HttpMethod resolve(@Nullable String method) {
        return (method != null ? mappings.get(method) : null);
    }

    public boolean matches(String method) {
        return (this == resolve(method));
    }
}
