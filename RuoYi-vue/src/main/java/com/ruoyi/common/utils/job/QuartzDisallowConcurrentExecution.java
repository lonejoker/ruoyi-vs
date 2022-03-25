package com.ruoyi.common.utils.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import com.ruoyi.project.monitor.domain.SysJob;

/**
 * @author 终于白发始于青丝
 * @Classname QuartzDisallowConcurrentExecution
 * @Description 类方法说明：定时任务处理（禁止并发执行）
 * @Date 2022/3/25 下午 14:27
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
