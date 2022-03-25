package com.ruoyi.framework.aspectj.lang.enums;

/**
 * @author 终于白发始于青丝
 * @Classname LimitType
 * @Description 类方法说明：限流类型
 * @Date 2022/3/25 下午 14:50
 */
public enum LimitType {
    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP
}
