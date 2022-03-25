package com.ruoyi.common.utils.file;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 终于白发始于青丝
 * @Classname FileTypeUtils
 * @Description 类方法说明：文件类型工具类
 * @Date 2022/3/25 下午 14:33
 */
public class FileTypeUtils {
    /**
     * @author 终于白发始于青丝
     * @Methodname getFileType
     * @Description 类方法说明：获取文件类型，例如: ruoyi.txt, 返回: txt
     * @Return 返回值：java.lang.String 后缀（不含".")
     * @Params java.io.File file 文件名
     * @Date 2022/3/25 下午 14:33
     */
    public static String getFileType(File file) {
        if (null == file) {
            return StringUtils.EMPTY;
        }
        return getFileType(file.getName());
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getFileType
     * @Description 类方法说明：获取文件类型，例如: ruoyi.txt, 返回: txt
     * @Return 返回值：java.lang.String 后缀（不含".")
     * @Params java.lang.String fileName 文件名
     * @Date 2022/3/25 下午 14:34
     */
    public static String getFileType(String fileName) {
        int separatorIndex = fileName.lastIndexOf("." );
        if (separatorIndex < 0) {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }


    /**
     * @author 终于白发始于青丝
     * @Methodname getFileExtendName
     * @Description 类方法说明：获取文件类型
     * @Return 返回值：java.lang.String 后缀（不含".")
     * @Params byte photoByte 文件字节码
     * @Date 2022/3/25 下午 14:34
     */
    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = "JPG";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97)) {
            strFileExtendName = "GIF";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70)) {
            strFileExtendName = "JPG";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            strFileExtendName = "BMP";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            strFileExtendName = "PNG";
        }
        return strFileExtendName;
    }
}