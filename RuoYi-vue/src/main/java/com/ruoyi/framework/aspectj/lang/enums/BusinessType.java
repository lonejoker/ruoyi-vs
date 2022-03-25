package com.ruoyi.framework.aspectj.lang.enums;

/**
 * @author 终于白发始于青丝
 * @Classname BusinessType
 * @Description 类方法说明：业务操作类型
 * @Date 2022/3/25 下午 14:50
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN,
}
