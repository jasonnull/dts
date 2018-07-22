package io.github.jasonnull.dts.server.conf;

import io.github.jasonnull.dts.server.schedule.JobDynamicScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * Created by dty on 2018/7/21.
 */
@Configuration
public class SchedulerConfig {
    @Bean("schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(DataSource dataSource) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        Resource resource = new ClassPathResource("quartz.properties");

        schedulerFactoryBean.setConfigLocation(resource);
        schedulerFactoryBean.setDataSource(dataSource);
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
