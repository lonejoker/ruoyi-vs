package com.ruoyi.common.utils.html;

import com.ruoyi.common.utils.StringUtils;

/**
 * @author 终于白发始于青丝
 * @Classname EscapeUtil
 * @Description 类方法说明：转义和反转义工具类
 * @Date 2022/3/25 下午 14:30
 */
public class EscapeUtil {
    public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";

    private static final char[][] TEXT = new char[64][];

    static {
        for (int i = 0; i < 64; i++) {
            TEXT[i] = new char[]{(char) i};
        }
        // special HTML characters
        TEXT['\''] = "&#039;".toCharArray(); // 单引号
        TEXT['"'] = "&#34;".toCharArray(); // 双引号
        TEXT['&'] = "&#38;".toCharArray(); // &符
        TEXT['<'] = "&#60;".toCharArray(); // 小于号
        TEXT['>'] = "&#62;".toCharArray(); // 大于号
    }

    /**
     *
     * @param text
     * @return
     */
    /**
     * @author 终于白发始于青丝
     * @Methodname escape
     * @Description 类方法说明：转义文本中的HTML字符为安全的字符
     * @Return 返回值：java.lang.String 转义后的文本
     * @Params java.lang.String text 被转义的文本
     * @Date 2022/3/25 下午 14:32
     */
    public static String escape(String text) {
        return encode(text);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname unescape
     * @Description 类方法说明：还原被转义的HTML特殊字符
     * @Return 返回值：java.lang.String 转换后的字符串
     * @Params java.lang.String content 包含转义符的HTML内容
     * @Date 2022/3/25 下午 14:32
     */
    public static String unescape(String content) {
        return decode(content);
    }

    /**
     *
     * @param content
     * @return
     */
    /**
     * @author 终于白发始于青丝
     * @Methodname clean
     * @Description 类方法说明： 清除所有HTML标签，但是不删除标签内的内容
     * @Return 返回值：java.lang.String 文本
     * @Params java.lang.String content
     * @Date 2022/3/25 下午 14:31 清除标签后的文本
     */
    public static String clean(String content) {
        return new HTMLFilter().filter(content);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname encode
     * @Description 类方法说明：Escape编码
     * @Return 返回值：java.lang.String 编码后的字符
     * @Params java.lang.String text 被编码的文本
     * @Date 2022/3/25 下午 14:31
     */
    private static String encode(String text) {
        if (StringUtils.isEmpty(text)) {
            return StringUtils.EMPTY;
        }

        final StringBuilder tmp = new StringBuilder(text.length() * 6);
        char c;
        for (int i = 0; i < text.length(); i++) {
            c = text.charAt(i);
            if (c < 256) {
                tmp.append("%" );
                if (c < 16) {
                    tmp.append("0" );
                }
                tmp.append(Integer.toString(c, 16));
            } else {
                tmp.append("%u" );
                if (c <= 0xfff) {
                    // issue#I49JU8@Gitee
                    tmp.append("0" );
                }
                tmp.append(Integer.toString(c, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname decode
     * @Description 类方法说明：Escape解码
     * @Return 返回值：java.lang.String 解码后的字符串
     * @Params java.lang.String content 被转义的内容
     * @Date 2022/3/25 下午 14:31
     */
    public static String decode(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }

        StringBuilder tmp = new StringBuilder(content.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < content.length()) {
            pos = content.indexOf("%" , lastPos);
            if (pos == lastPos) {
                if (content.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(content.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(content.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(content.substring(lastPos));
                    lastPos = content.length();
                } else {
                    tmp.append(content.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static void main(String[] args) {
        String html = "<script>alert(1);</script>";
        String escape = EscapeUtil.escape(html);
        // String html = "<scr<script>ipt>alert(\"XSS\")</scr<script>ipt>";
        // String html = "<123";
        // String html = "123>";
        System.out.println("clean: " + EscapeUtil.clean(html));
        System.out.println("escape: " + escape);
        System.out.println("unescape: " + EscapeUtil.unescape(escape));
    }
}
