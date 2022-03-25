package com.ruoyi.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.framework.config.RuoYiConfig;

/**
* @author 终于白发始于青丝
* @Classname FileUtils
* @Description 类方法说明：文件处理工具类
* @Date 2022/3/25 下午 14:38
*/
public class FileUtils {
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * @author 终于白发始于青丝
     * @Methodname writeBytes
     * @Description 类方法说明：输出指定文件的byte数组
     * @Return 返回值：void
     * @Params java.lang.String filePath 文件路径
     * @Params java.io.OutputStream os 输出流
     * @Date 2022/3/25 下午 14:38
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.close(os);
            IOUtils.close(fis);
        }
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname writeImportBytes
     * @Description 类方法说明：写数据到文件中
     * @Return 返回值：java.lang.String 目标文件
     * @Params byte data 数据
     * @throws IOException IO异常
     * @Date 2022/3/25 下午 14:39
     */
    public static String writeImportBytes(byte[] data) throws IOException {
        return writeBytes(data, RuoYiConfig.getImportPath());
    }
    /**
     * @author 终于白发始于青丝
     * @Methodname writeBytes
     * @Description 类方法说明：写数据到文件中
     * @Return 返回值：java.lang.String 目标文件
     * @Params byte data 数据
     * @Params java.lang.String uploadDir 目标文件
     * @throws IOException IO异常
     * @Date 2022/3/25 下午 14:39
     */
    public static String writeBytes(byte[] data, String uploadDir) throws IOException {
        FileOutputStream fos = null;
        String pathName = "";
        try {
            String extension = getFileExtendName(data);
            pathName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
            File file = FileUploadUtils.getAbsoluteFile(uploadDir, pathName);
            fos = new FileOutputStream(file);
            fos.write(data);
        } finally {
            IOUtils.close(fos);
        }
        return FileUploadUtils.getPathFileName(uploadDir, pathName);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname deleteFile
     * @Description 类方法说明：删除文件
     * @Return 返回值：boolean
     * @Params java.lang.String filePath 文件
     * @Date 2022/3/25 下午 14:40
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    /**
     * @author 终于白发始于青丝
     * @Methodname isValidFilename
     * @Description 类方法说明：文件名称验证
     * @Return 返回值：boolean true 正常 false 非法
     * @Params java.lang.String filename 文件名称
     * @Date 2022/3/25 下午 14:40
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname checkAllowDownload
     * @Description 类方法说明：检查文件是否可下载
     * @Return 返回值：boolean true 正常 false 非法
     * @Params java.lang.String resource 需要下载的文件
     * @Date 2022/3/25 下午 14:40
     */
    public static boolean checkAllowDownload(String resource) {
        // 禁止目录上跳级别
        if (StringUtils.contains(resource, ".." )) {
            return false;
        }

        // 检查允许下载的文件规则
        if (ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource))) {
            return true;
        }

        // 不在允许下载的文件规则
        return false;
    }
    /**
     * @author 终于白发始于青丝
     * @Methodname setFileDownloadHeader
     * @Description 类方法说明：下载文件名重新编码
     * @Return 返回值：java.lang.String 编码后的文件名
     * @Params javax.servlet.http.HttpServletRequest request 请求对象
     * @Params java.lang.String fileName 文件名
     * @Date 2022/3/25 下午 14:41
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT" );
        String filename = fileName;
        if (agent.contains("MSIE" )) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8" );
            filename = filename.replace("+" , " " );
        } else if (agent.contains("Firefox" )) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1" );
        } else if (agent.contains("Chrome" )) {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8" );
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8" );
        }
        return filename;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname setAttachmentResponseHeader
     * @Description 类方法说明：下载文件名重新编码
     * @Return 返回值：void
     * @Params javax.servlet.http.HttpServletResponse response 响应对象
     * @Params java.lang.String realFileName 真实文件名
     * @Date 2022/3/25 下午 14:41
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
        String percentEncodedFileName = percentEncode(realFileName);

        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=" )
                .append(percentEncodedFileName)
                .append(";" )
                .append("filename*=" )
                .append("utf-8''" )
                .append(percentEncodedFileName);

        response.addHeader("Access-Control-Expose-Headers" , "Content-Disposition,download-filename" );
        response.setHeader("Content-disposition" , contentDispositionValue.toString());
        response.setHeader("download-filename" , percentEncodedFileName);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname percentEncode
     * @Description 类方法说明：百分号编码工具方法
     * @Return 返回值：java.lang.String 百分号编码后的字符串
     * @Params java.lang.String s 需要百分号编码的字符串
     * @Date 2022/3/25 下午 14:41
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+" , "%20" );
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getFileExtendName
     * @Description 类方法说明：获取图像后缀
     * @Return 返回值：java.lang.String 后缀名
     * @Params byte photoByte 图像数据
     * @Date 2022/3/25 下午 14:42
     */
    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = "jpg";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97)) {
            strFileExtendName = "gif";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70)) {
            strFileExtendName = "jpg";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            strFileExtendName = "bmp";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            strFileExtendName = "png";
        }
        return strFileExtendName;
    }
    /**
     * @author 终于白发始于青丝
     * @Methodname getName
     * @Description 类方法说明：获取名称
     * @Return 返回值：java.lang.String 没有文件路径的名称
     * @Params java.lang.String fileName 路径名称
     * @Date 2022/3/25 下午 14:42
     */
    public static String getName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int lastUnixPos = fileName.lastIndexOf('/');
        int lastWindowsPos = fileName.lastIndexOf('\\');
        int index = Math.max(lastUnixPos, lastWindowsPos);
        return fileName.substring(index + 1);
    }
}
