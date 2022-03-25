package com.ruoyi.common.core.text;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.ruoyi.common.utils.StringUtils;

/**
 * @author 终于白发始于青丝
 * @Classname CharsetKit
 * @Description 类方法说明：字符集工具类
 * @Date 2022/3/25 下午 12:44
 */
public class CharsetKit {
    /** ISO-8859-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /** UTF-8 */
    public static final String UTF_8 = "UTF-8";
    /** GBK */
    public static final String GBK = "GBK";

    /** ISO-8859-1 */
    public static final Charset CHARSET_ISO_8859_1 = Charset.forName(ISO_8859_1);
    /** UTF-8 */
    public static final Charset CHARSET_UTF_8 = Charset.forName(UTF_8);
    /** GBK */
    public static final Charset CHARSET_GBK = Charset.forName(GBK);

    /**
     * @author 终于白发始于青丝
     * @Methodname charset
     * @Description 类方法说明：转换为Charset对象
     * @Return 返回值：java.nio.charset.Charset
     * @Params java.lang.String charset 字符集，为空则返回默认字符集
     * @Date 2022/3/25 下午 12:45
     */
    public static Charset charset(String charset) {
        return StringUtils.isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset);
    }

    /**
     * 转换字符串的字符集编码
     * @param source 字符串
     * @param srcCharset 源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    /**
     * @author 终于白发始于青丝
     * @Methodname convert
     * @Description 类方法说明：转换字符串的字符集编码
     * @Return 返回值：java.lang.String 转换后的字符集
     * @Params java.lang.String source 字符串
     * @Params java.lang.String srcCharset 源字符集，默认ISO-8859-1
     * @Params java.lang.String destCharset 源字符集，默认ISO-8859-1
     * @Date 2022/3/25 下午 12:45
     */
    public static String convert(String source, String srcCharset, String destCharset) {
        return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname convert
     * @Description 类方法说明：转换字符串的字符集编码
     * @Return 返回值：java.lang.String 转换字符串的字符集编码
     * @Params java.lang.String source 字符串
     * @Params java.nio.charset.Charset srcCharset 源字符集，默认ISO-8859-1
     * @Params java.nio.charset.Charset destCharset 目标字符集，默认UTF-8
     * @Date 2022/3/25 下午 12:47
     */
    public static String convert(String source, Charset srcCharset, Charset destCharset) {
        if (null == srcCharset) {
            srcCharset = StandardCharsets.ISO_8859_1;
        }

        if (null == destCharset) {
            destCharset = StandardCharsets.UTF_8;
        }

        if (StringUtils.isEmpty(source) || srcCharset.equals(destCharset)) {
            return source;
        }
        return new String(source.getBytes(srcCharset), destCharset);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname systemCharset
     * @Description 类方法说明：
     * @Return 返回值：java.lang.String 系统字符集编码
     * @Date 2022/3/25 下午 12:47
     */
    public static String systemCharset() {
        return Charset.defaultCharset().name();
    }
}
