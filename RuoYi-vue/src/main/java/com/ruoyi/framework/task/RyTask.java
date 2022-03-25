package com.ruoyi.framework.task;

import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

/**
* @author 终于白发始于青丝
* @Classname RyTask
* @Description 类方法说明：定时任务调度测试
* @Date 2022/3/25 下午 15:01
*/
@Component("ryTask" )
public class RyTask {
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}" , s, b, l, d, i));
    }

    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法" );
    }
}
