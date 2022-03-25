package com.ruoyi.framework.aspectj.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 终于白发始于青丝
 * @Classname Excels
 * @Description 类方法说明：Excel注解集
 * @Date 2022/3/25 下午 14:46
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels {
    public Excel[] value();
}
