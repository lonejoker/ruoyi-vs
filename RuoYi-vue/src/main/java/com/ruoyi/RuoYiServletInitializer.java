package com.ruoyi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 终于白发始于青丝
 * @Classname RuoYiServletInitializer
 * @Description 类方法说明：web容器中进行部署
 * @Date 2022/3/25 下午 14:59
 */
public class RuoYiServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RuoYiApplication.class);
    }
}
