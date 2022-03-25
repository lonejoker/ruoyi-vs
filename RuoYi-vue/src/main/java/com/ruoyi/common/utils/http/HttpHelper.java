package com.ruoyi.common.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 终于白发始于青丝
 * @Classname HttpHelper
 * @Description 类方法说明：通用http工具封装
 * @Date 2022/3/25 下午 14:28
 */
public class HttpHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try (InputStream inputStream = request.getInputStream()) {
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.warn("getBodyString出现问题！" );
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error(ExceptionUtils.getMessage(e));
                }
            }
        }
        return sb.toString();
    }
}
