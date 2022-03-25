package com.ruoyi.common.exception.file;

import com.ruoyi.common.exception.base.BaseException;

/**
 * @author 终于白发始于青丝
 * @Classname FileException
 * @Description 类方法说明：文件信息异常类
 * @Date 2022/3/25 下午 13:26
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file" , code, args, null);
    }

}
