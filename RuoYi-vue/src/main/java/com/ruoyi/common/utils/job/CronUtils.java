package com.ruoyi.common.utils.job;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;

/**
* @author 终于白发始于青丝
* @Classname CronUtils
* @Description 类方法说明：cron表达式工具类
* @Date 2022/3/25 下午 14:22
*/
public class CronUtils {

    /**
     * @author 终于白发始于青丝
     * @Methodname isValid
     * @Description 类方法说明：返回一个布尔值代表一个给定的Cron表达式的有效性
     * @Return 返回值：boolean 表达式是否有效
     * @Params java.lang.String cronExpression Cron表达式
     * @Date 2022/3/25 下午 14:22
     */
    public static boolean isValid(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getInvalidMessage
     * @Description 类方法说明：返回一个字符串值,表示该消息无效Cron表达式给出有效性
     * @Return 返回值：java.lang.String 无效时返回表达式错误描述,如果有效返回null
     * @Params java.lang.String cronExpression Cron表达式
     * @Date 2022/3/25 下午 14:22
     */
    public static String getInvalidMessage(String cronExpression) {
        try {
            new CronExpression(cronExpression);
            return null;
        } catch (ParseException pe) {
            return pe.getMessage();
        }
    }


    /**
     * @author 终于白发始于青丝
     * @Methodname getNextExecution
     * @Description 类方法说明：返回下一个执行时间根据给定的Cron表达式
     * @Return 返回值：java.util.Date 下次Cron表达式执行时间
     * @Params java.lang.String cronExpression Cron表达式
     * @Date 2022/3/25 下午 14:23
     */
    public static Date getNextExecution(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
