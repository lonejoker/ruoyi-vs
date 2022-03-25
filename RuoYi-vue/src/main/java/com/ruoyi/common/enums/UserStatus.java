package com.ruoyi.common.enums;

/**
 * @author 终于白发始于青丝
 * @Classname UserStatus
 * @Description 类方法说明：用户状态
 * @Date 2022/3/25 下午 13:25
 */
public enum UserStatus {
    OK("0" , "正常" ),
    DISABLE("1" , "停用" ),
    DELETED("2" , "删除" );

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
