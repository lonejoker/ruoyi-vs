package com.ruoyi.common.exception.file;

/**
 * @author 终于白发始于青丝
 * @Classname FileSizeLimitExceededException
 * @Description 类方法说明：文件名大小限制异常类
 * @Date 2022/3/25 下午 13:27
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize" , new Object[]{defaultMaxSize});
    }
}
