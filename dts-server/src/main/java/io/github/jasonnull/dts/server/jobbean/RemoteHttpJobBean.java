package io.github.jasonnull.dts.server.jobbean;

import io.github.jasonnull.dts.server.trigger.JobTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * http job bean
 * “@DisallowConcurrentExecution” diable concurrent, thread size can not be only one, better given more
 */
public class RemoteHttpJobBean extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(RemoteHttpJobBean.class);

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println(new Date() + ": " + context.getJobDetail().getKey().getName() + "\t");
        JobKey jobKey = context.getTrigger().getJobKey();
        Long jobId = Long.valueOf(jobKey.getName());
        JobTrigger.trigger(jobId);
    }
}