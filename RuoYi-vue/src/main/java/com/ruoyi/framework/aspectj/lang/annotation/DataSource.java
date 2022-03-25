package com.ruoyi.framework.aspectj.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;

/**
 * @author 终于白发始于青丝
 * @Classname DataSource
 * @Description 类方法说明：自定义多数据源切换注解，优先级：先方法，后类，如果方法覆盖了类上的数据源类型，以方法的为准，否则以类上的为准
 * @Date 2022/3/25 下午 14:46
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
