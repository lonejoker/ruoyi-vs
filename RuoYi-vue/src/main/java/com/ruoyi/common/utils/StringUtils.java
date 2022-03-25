package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.AntPathMatcher;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.text.StrFormatter;

/**
 * @author 终于白发始于青丝
 * @Classname StringUtils
 * @Description 类方法说明：字符串工具类
 * @Date 2022/3/25 下午 13:32
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /** 空字符串 */
    private static final String NULLSTR = "";

    /** 下划线 */
    private static final char SEPARATOR = '_';

    /**
     * @author 终于白发始于青丝
     * @Methodname nvl
     * @Description 类方法说明：获取参数不为空值
     * @Return 返回值：T 返回值
     * @Params T value 要判断的value
     * @Params T defaultValue  默认的value
     * @Date 2022/3/25 下午 13:33
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isEmpty
     * @Description 类方法说明：判断一个Collection是否为空， 包含List，Set，Queue
     * @Return 返回值：boolean true：为空 false：非空
     * @Params java.util.Collection<?> coll 要判断的Collection
     * @Date 2022/3/25 下午 13:35
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isNotEmpty
     * @Description 类方法说明：判断一个Collection是否非空，包含List，Set，Queue
     * @Return 返回值：boolean true：非空 false：空
     * @Params java.util.Collection<?> coll 要判断的Collection
     * @Date 2022/3/25 下午 13:35
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    /**
     * @author 终于白发始于青丝
     * @Methodname isEmpty
     * @Description 类方法说明：
     * @Return 返回值：boolean
     * @Params java.lang.Object objects
     * @Date 2022/3/25 下午 13:35
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isNotEmpty
     * @Description 类方法说明：判断一个对象数组是否非空
     * @Return 返回值：boolean true：非空 false：空
     * @Params java.lang.Object objects 要判断的对象数组
     * @Date 2022/3/25 下午 13:35
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isEmpty
     * @Description 类方法说明：判断一个Map是否为空
     * @Return 返回值：boolean true：为空 false：非空
     * @Params java.util.Map<?, ?>map 要判断的Map
     * @Date 2022/3/25 下午 13:35
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isNotEmpty
     * @Description 类方法说明：判断一个Map是否为空
     * @Return 返回值：boolean true：非空 false：空
     * @Params java.util.Map<?, ?> map 要判断的Map
     * @Date 2022/3/25 下午 13:36
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isEmpty
     * @Description 类方法说明：判断一个字符串是否为空串
     * @Return 返回值：boolean true：为空 false：非空
     * @Params java.lang.String str String
     * @Date 2022/3/25 下午 13:38
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isNotEmpty
     * @Description 类方法说明：判断一个字符串是否为非空串
     * @Return 返回值：boolean true：非空串 false：空串
     * @Params java.lang.String str String
     * @Date 2022/3/25 下午 13:38
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isNull
     * @Description 类方法说明：判断一个对象是否为空
     * @Return 返回值：boolean true：为空 false：非空
     * @Params java.lang.Object object Object
     * @Date 2022/3/25 下午 13:38
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isNotNull
     * @Description 类方法说明：判断一个对象是否非空
     * @Return 返回值：boolean true：非空 false：空
     * @Params java.lang.Object object Object
     * @Date 2022/3/25 下午 13:40
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isArray
     * @Description 类方法说明：判断一个对象是否是数组类型（Java基本型别的数组）
     * @Return 返回值：boolean true：是数组 false：不是数组
     * @Params java.lang.Object object 对象
     * @Date 2022/3/25 下午 13:40
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname trim
     * @Description 类方法说明：去空格
     * @Return 返回值：java.lang.String
     * @Params java.lang.String str
     * @Date 2022/3/25 下午 13:41
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname substring
     * @Description 类方法说明：截取字符串
     * @Return 返回值：java.lang.String 结果
     * @Params java.lang.String str 字符串
     * @Params int start 开始
     * @Date 2022/3/25 下午 13:41
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULLSTR;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname substring
     * @Description 类方法说明：截取字符串
     * @Return 返回值：java.lang.String 结果
     * @Params java.lang.String str 字符串
     * @Params int start 开始
     * @Params int end 结束
     * @Date 2022/3/25 下午 13:41
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULLSTR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname format
     * @Description 类方法说明：
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     * @Return 返回值：java.lang.String 格式化后的文本
     * @Params java.lang.String template 文本模板，被替换的部分用 {} 表示
     * @Params java.lang.Object... params 参数值
     * @Date 2022/3/25 下午 13:41
     */
    public static String format(String template, Object... params) {
        if (isEmpty(params) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname ishttp
     * @Description 类方法说明：是否为http(s)://开头
     * @Return 返回值：boolean 结果
     * @Params java.lang.String link 链接
     * @Date 2022/3/25 下午 13:42
     */
    public static boolean ishttp(String link) {
        return StringUtils.startsWithAny(link, Constants.HTTP, Constants.HTTPS);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname str2Set
     * @Description 类方法说明：字符串转set
     * @Return 返回值：java.util.Set<java.lang.String> set集合
     * @Params java.lang.String str 字符串
     * @Params java.lang.String sep 分隔符
     * @Date 2022/3/25 下午 13:42
     */
    public static final Set<String> str2Set(String str, String sep) {
        return new HashSet<String>(str2List(str, sep, true, false));
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname str2List
     * @Description 类方法说明：字符串转list
     * @Return 返回值：java.util.List<java.lang.String>list集合
     * @Params java.lang.String str 字符串
     * @Params java.lang.String sep 分隔符
     * @Params boolean filterBlank 过滤纯空白
     * @Params boolean trim 去掉首尾空白
     * @Date 2022/3/25 下午 13:42
     */
    public static final List<String> str2List(String str, String sep, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isEmpty(str)) {
            return list;
        }

        // 过滤空白字符串
        if (filterBlank && StringUtils.isBlank(str)) {
            return list;
        }
        String[] split = str.split(sep);
        for (String string : split) {
            if (filterBlank && StringUtils.isBlank(string)) {
                continue;
            }
            if (trim) {
                string = string.trim();
            }
            list.add(string);
        }

        return list;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname containsAnyIgnoreCase
     * @Description 类方法说明：查找指定字符串是否包含指定字符串列表中的任意一个字符串同时串忽略大小写
     * @Return 返回值：boolean 是否包含任意一个字符串
     * @Params java.lang.CharSequence cs 指定字符串
     * @Params java.lang.CharSequence... searchCharSequences 需要检查的字符串数组
     * @Date 2022/3/25 下午 13:43
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences) {
        if (isEmpty(cs) || isEmpty(searchCharSequences)) {
            return false;
        }
        for (CharSequence testStr : searchCharSequences) {
            if (containsIgnoreCase(cs, testStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname toUnderScoreCase
     * @Description 类方法说明：驼峰转下划线命名
     * @Return 返回值：java.lang.String
     * @Params java.lang.String str
     * @Date 2022/3/25 下午 13:43
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname inStringIgnoreCase
     * @Description 类方法说明：是否包含字符串
     * @Return 返回值：boolean 包含返回true
     * @Params java.lang.String str 验证字符串
     * @Params java.lang.String... strs 字符串组
     * @Date 2022/3/25 下午 13:44
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname convertToCamelCase
     * @Description 类方法说明：将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     * @Return 返回值：java.lang.String 转换后的驼峰式命名的字符串
     * @Params java.lang.String name 转换前的下划线大写方式命名的字符串
     * @Date 2022/3/25 下午 13:44
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_" )) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_" );
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname toCamelCase
     * @Description 类方法说明：驼峰式命名法 例如：user_name->userName
     * @Return 返回值：java.lang.String
     * @Params java.lang.String s
     * @Date 2022/3/25 下午 13:44
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname matches
     * @Description 类方法说明：查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     * @Return 返回值：boolean 是否匹配
     * @Params java.lang.String str 指定字符串
     * @Params java.util.List<java.lang.String> strs 需要检查的字符串数组
     * @Date 2022/3/25 下午 13:44
     */
    public static boolean matches(String str, List<String> strs) {
        if (isEmpty(str) || isEmpty(strs)) {
            return false;
        }
        for (String pattern : strs) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isMatch
     * @Description 类方法说明：
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     * @Return 返回值：boolean
     * @Params java.lang.String pattern 匹配规则
     * @Params java.lang.String url 需要匹配的url
     * @Date 2022/3/25 下午 13:45
     */
    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    @SuppressWarnings("unchecked" )
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname padl
     * @Description 类方法说明：数字左边补齐0，使之达到指定长度。注意，如果数字转换为字符串后，长度大于size，则只保留 最后size个字符
     * @Return 返回值：java.lang.String 返回数字的字符串格式，该字符串为指定长度
     * @Params java.lang.Numbe 字符串指定长度r num 数字对象
     * @Params int size
     * @Date 2022/3/25 下午 13:45
     */
    public static final String padl(final Number num, final int size) {
        return padl(num.toString(), size, '0');
    }


    /**
     * @author 终于白发始于青丝
     * @Methodname padl
     * @Description 类方法说明：字符串左补齐，如果原始字符串s长度大于size，则只保留最后size个字符
     * @Return 返回值：java.lang.String 返回指定长度的字符串，由原字符串左补齐或截取得到。
     * @Params java.lang.String s 原始字符串
     * @Params int size 字符串指定长度
     * @Params char c 用于补齐的字符
     * @Date 2022/3/25 下午 13:33
     */
    public static final String padl(final String s, final int size, final char c) {
        final StringBuilder sb = new StringBuilder(size);
        if (s != null) {
            final int len = s.length();
            if (s.length() <= size) {
                for (int i = size - len; i > 0; i--) {
                    sb.append(c);
                }
                sb.append(s);
            } else {
                return s.substring(len - size, len);
            }
        } else {
            for (int i = size; i > 0; i--) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}