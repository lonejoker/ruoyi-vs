package com.ruoyi.common.exception.user;

import com.ruoyi.common.exception.base.BaseException;

/**
 * @author 终于白发始于青丝
 * @Classname UserException
 * @Description 类方法说明：用户信息异常类
 * @Date 2022/3/25 下午 13:28
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user" , code, args, null);
    }
}
