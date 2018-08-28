package io.github.jasonnull.dts.server.conf;

import io.github.jasonnull.dts.server.DTSApplication;
import io.github.jasonnull.dts.server.schedule.JobDynamicScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by dty on 2018/7/21.
 */
@Configuration
public class SchedulerConfig {
    @Bean("schedulerFactory")
    public SchedulerFactoryBean schedulerFactory() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        Properties properties = new Properties();
        properties.load(DTSApplication.class.getClassLoader().getResourceAsStream("quartz.properties"));

        schedulerFactoryBean.setQuartzProperties(properties);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        return schedulerFactoryBean;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public JobDynamicScheduler xxlJobDynamicScheduler(SchedulerFactoryBean schedulerFactory) {
        JobDynamicScheduler jobDynamicScheduler = new JobDynamicScheduler();

        jobDynamicScheduler.setAccessToken(null);
        jobDynamicScheduler.setScheduler(schedulerFactory.getObject());

        return jobDynamicScheduler;
    }
}
