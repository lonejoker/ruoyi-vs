package com.ruoyi.common.utils.job;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.monitor.domain.SysJob;

/**
 * @author 终于白发始于青丝
 * @Classname JobInvokeUtil
 * @Description 类方法说明：任务执行工具
 * @Date 2022/3/25 下午 14:23
 */
public class JobInvokeUtil {

    /**
     * @author 终于白发始于青丝
     * @Methodname invokeMethod
     * @Description 类方法说明：执行方法
     * @Return 返回值：void
     * @Params com.ruoyi.project.monitor.domain.SysJob sysJob 系统任务
     * @Date 2022/3/25 下午 14:23
     */
    public static void invokeMethod(SysJob sysJob) throws Exception {
        String invokeTarget = sysJob.getInvokeTarget();
        String beanName = getBeanName(invokeTarget);
        String methodName = getMethodName(invokeTarget);
        List<Object[]> methodParams = getMethodParams(invokeTarget);

        if (!isValidClassName(beanName)) {
            Object bean = SpringUtils.getBean(beanName);
            invokeMethod(bean, methodName, methodParams);
        } else {
            Object bean = Class.forName(beanName).newInstance();
            invokeMethod(bean, methodName, methodParams);
        }
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname invokeMethod
     * @Description 类方法说明：调用任务方法
     * @Return 返回值：void
     * @Params java.lang.Object bean 目标对象
     * @Params java.lang.String methodName 方法名称
     * @Params java.util.List<java.lang.Object> methodParams 方法参数
     * @Date 2022/3/25 下午 14:24
     */
    private static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        if (StringUtils.isNotNull(methodParams) && methodParams.size() > 0) {
            Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        } else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isValidClassName
     * @Description 类方法说明：校验是否为为class包名
     * @Return 返回值：boolean true是 false否
     * @Params java.lang.String invokeTarget 名称
     * @Date 2022/3/25 下午 14:25
     */
    public static boolean isValidClassName(String invokeTarget) {
        return StringUtils.countMatches(invokeTarget, "." ) > 1;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getBeanName
     * @Description 类方法说明：获取bean名称
     * @Return 返回值：java.lang.String bean名称
     * @Params java.lang.String invokeTarget 目标字符串
     * @Date 2022/3/25 下午 14:25
     */
    public static String getBeanName(String invokeTarget) {
        String beanName = StringUtils.substringBefore(invokeTarget, "(" );
        return StringUtils.substringBeforeLast(beanName, "." );
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getMethodName
     * @Description 类方法说明：获取bean方法
     * @Return 返回值：java.lang.String method方法
     * @Params java.lang.String invokeTarget 目标字符串
     * @Date 2022/3/25 下午 14:26
     */
    public static String getMethodName(String invokeTarget) {
        String methodName = StringUtils.substringBefore(invokeTarget, "(" );
        return StringUtils.substringAfterLast(methodName, "." );
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getMethodParams
     * @Description 类方法说明：获取method方法参数相关列表
     * @Return 返回值：java.util.List<java.lang.Object[]> method方法相关参数列表
     * @Params java.lang.String invokeTarget 目标字符串
     * @Date 2022/3/25 下午 14:26
     */
    public static List<Object[]> getMethodParams(String invokeTarget) {
        String methodStr = StringUtils.substringBetween(invokeTarget, "(" , ")" );
        if (StringUtils.isEmpty(methodStr)) {
            return null;
        }
        String[] methodParams = methodStr.split(",(?=([^\"']*[\"'][^\"']*[\"'])*[^\"']*$)" );
        List<Object[]> classs = new LinkedList<>();
        for (int i = 0; i < methodParams.length; i++) {
            String str = StringUtils.trimToEmpty(methodParams[i]);
            // String字符串类型，以'或"开头
            if (StringUtils.startsWithAny(str, "'" , "\"" )) {
                classs.add(new Object[]{StringUtils.substring(str, 1, str.length() - 1), String.class});
            }
            // boolean布尔类型，等于true或者false
            else if ("true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)) {
                classs.add(new Object[]{Boolean.valueOf(str), Boolean.class});
            }
            // long长整形，以L结尾
            else if (StringUtils.endsWith(str, "L" )) {
                classs.add(new Object[]{Long.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Long.class});
            }
            // double浮点类型，以D结尾
            else if (StringUtils.endsWith(str, "D" )) {
                classs.add(new Object[]{Double.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Double.class});
            }
            // 其他类型归类为整形
            else {
                classs.add(new Object[]{Integer.valueOf(str), Integer.class});
            }
        }
        return classs;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getMethodParamsType
     * @Description 类方法说明：获取参数类型
     * @Return 返回值：java.lang.Class<?>[] 参数类型列表
     * @Params java.util.List<java.lang.Object> methodParams 参数相关列表
     * @Date 2022/3/25 下午 14:26
     */
    public static Class<?>[] getMethodParamsType(List<Object[]> methodParams) {
        Class<?>[] classs = new Class<?>[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = (Class<?>) os[1];
            index++;
        }
        return classs;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getMethodParamsValue
     * @Description 类方法说明：获取参数值
     * @Return 返回值：java.lang.Object[] 参数值列表
     * @Params java.util.List<java.lang.Object> methodParams 参数相关列表
     * @Date 2022/3/25 下午 14:26
     */
    public static Object[] getMethodParamsValue(List<Object[]> methodParams) {
        Object[] classs = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = (Object) os[0];
            index++;
        }
        return classs;
    }
}
