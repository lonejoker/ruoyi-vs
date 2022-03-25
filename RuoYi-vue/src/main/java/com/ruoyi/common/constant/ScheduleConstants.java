package com.ruoyi.common.constant;

/**
* @author 终于白发始于青丝
* @Classname ScheduleConstants
* @Description 类方法说明：任务调度通用常量
* @Date 2022/3/25 下午 12:43
*/
public class ScheduleConstants {
    public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";

    /** 执行目标key */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";

    /** 默认 */
    public static final String MISFIRE_DEFAULT = "0";

    /** 立即触发执行 */
    public static final String MISFIRE_IGNORE_MISFIRES = "1";

    /** 触发一次执行 */
    public static final String MISFIRE_FIRE_AND_PROCEED = "2";

    /** 不触发立即执行 */
    public static final String MISFIRE_DO_NOTHING = "3";

    public enum Status {
        /**
         * 正常
         */
        NORMAL("0" ),
        /**
         * 暂停
         */
        PAUSE("1" );

        private String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
