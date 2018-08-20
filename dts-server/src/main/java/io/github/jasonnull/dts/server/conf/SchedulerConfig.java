package io.github.jasonnull.dts.server.conf;

import com.zaxxer.hikari.HikariDataSource;
import io.github.jasonnull.dts.server.DTSApplication;
import io.github.jasonnull.dts.server.schedule.JobDynamicScheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dty on 2018/7/21.
 */
@Configuration
public class SchedulerConfig {
    @Value("${org.quartz.dataSource.url}")
    private String url;

    @Value("${org.quartz.dataSource.userName}")
    private String userName;

    @Value("${org.quartz.dataSource.password}")
    private String password;

    @Bean("schedulerFactory")
    public SchedulerFactoryBean schedulerFactory() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        Properties properties = new Properties();
        properties.load(DTSApplication.class.getClassLoader().getResourceAsStream("quartz.properties"));

        // todo fix
        properties.replace("org.quartz.dataSource.xxl-job.URL", url);
        properties.replace("org.quartz.dataSource.xxl-job.user", userName);
        properties.replace("org.quartz.dataSource.xxl-job.password", password);
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
