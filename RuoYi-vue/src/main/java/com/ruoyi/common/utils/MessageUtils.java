package com.ruoyi.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import com.ruoyi.common.utils.spring.SpringUtils;

/**
 * @author 终于白发始于青丝
 * @Classname MessageUtils
 * @Description 类方法说明：获取i18n资源文件
 * @Date 2022/3/25 下午 14:03
 */
public class MessageUtils {

    /**
     * @author 终于白发始于青丝
     * @Methodname message
     * @Description 类方法说明：根据消息键和参数 获取消息 委托给spring messageSource
     * @Return 返回值：java.lang.String 获取国际化翻译值
     * @Params java.lang.String code 消息键
     * @Params java.lang.Object... args 参数
     * @Date 2022/3/25 下午 14:03
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
