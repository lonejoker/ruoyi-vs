package com.ruoyi.common.exception.file;

/**
 * @author 终于白发始于青丝
 * @Classname FileNameLengthLimitExceededException
 * @Description 类方法说明：文件名称超长限制异常类
 * @Date 2022/3/25 下午 13:27
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length" , new Object[]{defaultFileNameLength});
    }
}
