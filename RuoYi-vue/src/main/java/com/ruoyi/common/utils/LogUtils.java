package com.ruoyi.common.utils;

/**
 * @author 终于白发始于青丝
 * @Classname LogUtils
 * @Description 类方法说明：处理并记录日志文件
 * @Date 2022/3/25 下午 14:04
 */
public class LogUtils {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
