package com.ruoyi.common.exception.job;

/**
 * @author 终于白发始于青丝
 * @Classname TaskException
 * @Description 类方法说明：计划策略异常
 * @Date 2022/3/25 下午 13:27
 */
public class TaskException extends Exception {
    private static final long serialVersionUID = 1L;

    private Code code;

    public TaskException(String msg, Code code) {
        this(msg, code, null);
    }

    public TaskException(String msg, Code code, Exception nestedEx) {
        super(msg, nestedEx);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public enum Code {
        TASK_EXISTS, NO_TASK_EXISTS, TASK_ALREADY_STARTED, UNKNOWN, CONFIG_ERROR, TASK_NODE_NOT_AVAILABLE
    }
}