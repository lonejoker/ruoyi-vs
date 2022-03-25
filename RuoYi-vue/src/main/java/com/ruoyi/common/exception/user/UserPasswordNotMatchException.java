package com.ruoyi.common.exception.user;

/**
 * @author 终于白发始于青丝
 * @Classname UserPasswordNotMatchException
 * @Description 类方法说明：用户密码不正确或不符合规范异常类
 * @Date 2022/3/25 下午 13:28
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match" , null);
    }
}
