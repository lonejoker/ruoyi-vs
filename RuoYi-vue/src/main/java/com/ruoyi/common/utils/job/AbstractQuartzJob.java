package com.ruoyi.common.utils.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.utils.ExceptionUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.monitor.domain.SysJob;
import com.ruoyi.project.monitor.domain.SysJobLog;
import com.ruoyi.project.monitor.service.ISysJobLogService;

/**
 * @author 终于白发始于青丝
 * @Classname AbstractQuartzJob
 * @Description 类方法说明：抽象quartz调用
 * @Date 2022/3/25 下午 14:20
 */
public abstract class AbstractQuartzJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysJob sysJob = new SysJob();
        BeanUtils.copyBeanProp(sysJob, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try {
            before(context, sysJob);
            if (sysJob != null) {
                doExecute(context, sysJob);
            }
            after(context, sysJob, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：" , e);
            after(context, sysJob, e);
        }
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname before
     * @Description 类方法说明：执行前
     * @Return 返回值：void
     * @Params org.quartz.JobExecutionContext context 工作执行上下文对象
     * @Params com.ruoyi.project.monitor.domain.SysJob sysJob 系统计划任务
     * @Date 2022/3/25 下午 14:20
     */
    protected void before(JobExecutionContext context, SysJob sysJob) {
        threadLocal.set(new Date());
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname after
     * @Description 类方法说明：执行后
     * @Return 返回值：void
     * @Params org.quartz.JobExecutionContext context 工作执行上下文对象
     * @Params com.ruoyi.project.monitor.domain.SysJob sysJob 系统计划任务
     * @Params java.lang.Exception e
     * @Date 2022/3/25 下午 14:21
     */
    protected void after(JobExecutionContext context, SysJob sysJob, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobName(sysJob.getJobName());
        sysJobLog.setJobGroup(sysJob.getJobGroup());
        sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
        sysJobLog.setStartTime(startTime);
        sysJobLog.setStopTime(new Date());
        long runMs = sysJobLog.getStopTime().getTime() - sysJobLog.getStartTime().getTime();
        sysJobLog.setJobMessage(sysJobLog.getJobName() + " 总共耗时：" + runMs + "毫秒" );
        if (e != null) {
            sysJobLog.setStatus(Constants.FAIL);
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            sysJobLog.setExceptionInfo(errorMsg);
        } else {
            sysJobLog.setStatus(Constants.SUCCESS);
        }

        // 写入数据库当中
        SpringUtils.getBean(ISysJobLogService.class).addJobLog(sysJobLog);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname doExecute
     * @Description 类方法说明：执行方法，由子类重载
     * @Return 返回值：void
     * @Params org.quartz.JobExecutionContext context 工作执行上下文对象
     * @Params com.ruoyi.project.monitor.domain.SysJob sysJob 系统计划任务
     * @Date 2022/3/25 下午 14:21
     */
    protected abstract void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception;
}
