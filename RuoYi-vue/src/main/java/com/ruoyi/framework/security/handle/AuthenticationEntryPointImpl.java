package com.ruoyi.framework.security.handle;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * @author 终于白发始于青丝
 * @Classname AuthenticationEntryPointImpl
 * @Description 类方法说明：认证失败处理类 返回未授权
 * @Date 2022/3/25 下午 15:02
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源" , request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
