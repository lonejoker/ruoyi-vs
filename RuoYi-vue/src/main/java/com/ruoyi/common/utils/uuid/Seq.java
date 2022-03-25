package com.ruoyi.common.utils.uuid;

import java.util.concurrent.atomic.AtomicInteger;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;

/**
 * @author 终于白发始于青丝
 * @Classname Seq
 * @Description 类方法说明：序列生成类
 * @Date 2022/3/25 下午 14:13
 */
public class Seq {
    // 通用序列类型
    public static final String commSeqType = "COMMON";

    // 上传序列类型
    public static final String uploadSeqType = "UPLOAD";

    // 通用接口序列数
    private static AtomicInteger commSeq = new AtomicInteger(1);

    // 上传接口序列数
    private static AtomicInteger uploadSeq = new AtomicInteger(1);

    // 机器标识
    private static String machineCode = "A";

    /**
     * @author 终于白发始于青丝
     * @Methodname getId
     * @Description 类方法说明：获取通用序列号
     * @Return 返回值：java.lang.String 序列值
     * @Params
     * @Date 2022/3/25 下午 14:14
     */
    public static String getId() {
        return getId(commSeqType);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getId
     * @Description 类方法说明：默认16位序列号 yyMMddHHmmss + 一位机器标识 + 3长度循环递增字符串
     * @Return 返回值：java.lang.String 序列值
     * @Params java.lang.String type
     * @Date 2022/3/25 下午 14:14
     */
    public static String getId(String type) {
        AtomicInteger atomicInt = commSeq;
        if (uploadSeqType.equals(type)) {
            atomicInt = uploadSeq;
        }
        return getId(atomicInt, 3);
    }
    /**
     * @author 终于白发始于青丝
     * @Methodname getId
     * @Description 类方法说明：通用接口序列号 yyMMddHHmmss + 一位机器标识 + length长度循环递增字符串
     * @Return 返回值：java.lang.String 序列值
     * @Params java.util.concurrent.atomic.AtomicInteger atomicInt 序列数
     * @Params int length 数值长度
     * @Date 2022/3/25 下午 14:14
     */
    public static String getId(AtomicInteger atomicInt, int length) {
        String result = DateUtils.dateTimeNow();
        result += machineCode;
        result += getSeq(atomicInt, length);
        return result;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getSeq
     * @Description 类方法说明：序列循环递增字符串[1, 10 的 (length)幂次方), 用0左补齐length位数
     * @Return 返回值：java.lang.String 序列值
     * @Params java.util.concurrent.atomic.AtomicInteger atomicInt
     * @Params int length
     * @Date 2022/3/25 下午 14:14
     */
    private synchronized static String getSeq(AtomicInteger atomicInt, int length) {
        // 先取值再+1
        int value = atomicInt.getAndIncrement();

        // 如果更新后值>=10 的 (length)幂次方则重置为1
        int maxSeq = (int) Math.pow(10, length);
        if (atomicInt.get() >= maxSeq) {
            atomicInt.set(1);
        }
        // 转字符串，用0左补齐
        return StringUtils.padl(value, length);
    }
}
