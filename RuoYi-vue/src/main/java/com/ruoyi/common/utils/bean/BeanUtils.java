package com.ruoyi.common.utils.bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 终于白发始于青丝
 * @Classname BeanUtils
 * @Description 类方法说明：
 * @Date 2022/3/25 下午 14:43
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /** Bean方法名中属性名开始的下标 */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /** * 匹配getter方法的正则表达式 */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)" );

    /** * 匹配setter方法的正则表达式 */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)" );

    /**
     * @author 终于白发始于青丝
     * @Methodname copyBeanProp
     * @Description 类方法说明：Bean属性复制工具方法
     * @Return 返回值：void
     * @Params java.lang.Object dest 目标对象
     * @Params java.lang.Object src 源对象
     * @Date 2022/3/25 下午 14:43
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getSetterMethods
     * @Description 类方法说明：获取对象的setter方法
     * @Return 返回值：java.util.List<java.lang.reflect.Method> 对象的setter方法列表
     * @Params java.lang.Object obj 对象
     * @Date 2022/3/25 下午 14:43
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getGetterMethods
     * @Description 类方法说明：获取对象的getter方法
     * @Return 返回值：java.util.List<java.lang.reflect.Method>obj 对象的getter方法列表
     * @Params java.lang.Object obj 对象
     * @Date 2022/3/25 下午 14:44
     */
    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     *
     * 。
     *
     * @param m1
     * @param m2
     * @return
     */
    /**
     * @author 终于白发始于青丝
     * @Methodname isMethodPropEquals
     * @Description 类方法说明：检查Bean方法名中的属性名是否相等。<br>，如getName()和setName()属性名一样，getName()和setAge()属性名不一样
     * @Return 返回值：boolean 属性名一样返回true，否则返回false
     * @Params java.lang.String m1 方法名1
     * @Params java.lang.String m2 方法名2
     * @Date 2022/3/25 下午 14:44
     */
    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }
}
