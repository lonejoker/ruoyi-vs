package com.ruoyi.common.exception.user;

/**
 * @author 终于白发始于青丝
 * @Classname CaptchaException
 * @Description 类方法说明：验证码错误异常类
 * @Date 2022/3/25 下午 13:28
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error" , null);
    }
}
