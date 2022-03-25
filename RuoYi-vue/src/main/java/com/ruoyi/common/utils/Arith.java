package com.ruoyi.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 终于白发始于青丝
 * @Classname Arith
 * @Description 类方法说明：精确的浮点数运算
 * @Date 2022/3/25 下午 14:08
 */
public class Arith {

    /** 默认除法运算精度 */
    private static final int DEF_DIV_SCALE = 10;

    /** 这个类不能实例化 */
    private Arith() {
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname add
     * @Description 类方法说明：提供精确的加法运算
     * @Return 返回值：double 两个参数的和
     * @Params double v1 被加数
     * @Params double v2 加数
     * @Date 2022/3/25 下午 14:08
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname sub
     * @Description 类方法说明：提供精确的减法运算
     * @Return 返回值：double 两个参数的差
     * @Params double v1 被减数
     * @Params double v2 减数
     * @Date 2022/3/25 下午 14:08
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname mul
     * @Description 类方法说明：提供精确的乘法运算
     * @Return 返回值：double 两个参数的积
     * @Params double v1 被乘数
     * @Params double v2 乘数
     * @Date 2022/3/25 下午 14:08
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname div
     * @Description 类方法说明：提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入
     * @Return 返回值：double 两个参数的商
     * @Params double v1 被除数
     * @Params double v2 除数
     * @Date 2022/3/25 下午 14:09
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname div
     * @Description 类方法说明：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @Return 返回值：double 两个参数的商
     * @Params double v1 被除数
     * @Params double v2 除数
     * @Params int scale 表示表示需要精确到小数点以后几位
     * @Date 2022/3/25 下午 14:09
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero" );
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        if (b1.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.doubleValue();
        }
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname round
     * @Description 类方法说明：提供精确的小数位四舍五入处理
     * @Return 返回值：double 四舍五入后的结果
     * @Params double v 需要四舍五入的数字
     * @Params int scale 小数点后保留几位
     * @Date 2022/3/25 下午 14:09
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero" );
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1" );
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }
}
