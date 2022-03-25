package com.ruoyi.common.utils.job;

import org.quartz.JobExecutionContext;
import com.ruoyi.project.monitor.domain.SysJob;

/**
 * @author 终于白发始于青丝
 * @Classname QuartzJobExecution
 * @Description 类方法说明：定时任务处理（允许并发执行）
 * @Date 2022/3/25 下午 14:27
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
