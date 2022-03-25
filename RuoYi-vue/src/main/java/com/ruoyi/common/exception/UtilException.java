package com.ruoyi.common.exception;

/**
 * @author 终于白发始于青丝
 * @Classname UtilException
 * @Description 类方法说明：工具类异常
 * @Date 2022/3/25 下午 13:29
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
