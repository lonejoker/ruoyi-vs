package com.ruoyi.common.utils.poi;

/**
 * @author 终于白发始于青丝
 * @Classname ExcelHandlerAdapter
 * @Description 类方法说明：Excel数据格式处理适配器
 * @Date 2022/3/25 下午 14:18
 */
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     * @param value 单元格数据值
     * @param args excel注解args参数组
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
