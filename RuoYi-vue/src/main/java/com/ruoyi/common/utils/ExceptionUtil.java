package com.ruoyi.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author 终于白发始于青丝
 * @Classname ExceptionUtil
 * @Description 类方法说明：错误信息处理类
 * @Date 2022/3/25 下午 14:04
 */
public class ExceptionUtil {
    /**
     * 获取exception的详细错误信息
     */
    public static String getExceptionMessage(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

    public static String getRootErrorMessage(Exception e) {
        Throwable root = ExceptionUtils.getRootCause(e);
        root = (root == null ? e : root);
        if (root == null) {
            return "";
        }
        String msg = root.getMessage();
        if (msg == null) {
            return "null";
        }
        return StringUtils.defaultString(msg);
    }
}
