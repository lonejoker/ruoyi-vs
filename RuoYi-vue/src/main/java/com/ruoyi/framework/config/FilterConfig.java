package com.ruoyi.framework.config;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ruoyi.common.filter.RepeatableFilter;
import com.ruoyi.common.filter.XssFilter;
import com.ruoyi.common.utils.StringUtils;

/**
 * @author 终于白发始于青丝
 * @Classname FilterConfig
 * @Description 类方法说明：Filter配置
 * @Date 2022/3/25 下午 14:54
 */
@Configuration
public class FilterConfig {
    @Value("${xss.excludes}" )
    private String excludes;

    @Value("${xss.urlPatterns}" )
    private String urlPatterns;

    @SuppressWarnings({"rawtypes" , "unchecked"})
    @Bean
    @ConditionalOnProperty(value = "xss.enabled" , havingValue = "true" )
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(StringUtils.split(urlPatterns, "," ));
        registration.setName("xssFilter" );
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes" , excludes);
        registration.setInitParameters(initParameters);
        return registration;
    }

    @SuppressWarnings({"rawtypes" , "unchecked"})
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*" );
        registration.setName("repeatableFilter" );
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }

}
