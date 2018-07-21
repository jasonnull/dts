package io.github.jasonnull.dts.server.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.github.jasonnull.dts.server.schedule.JobDynamicScheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;


/**
 * Created by dty on 2018/7/21.
 */
@Configuration
public class WebConfig {
    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource()
    {
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler(DataSource dataSource){
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
    public JobDynamicScheduler xxlJobDynamicScheduler(SchedulerFactoryBean quartzScheduler) {
        JobDynamicScheduler jobDynamicScheduler = new JobDynamicScheduler();
        jobDynamicScheduler.setAccessToken(accessToken);
        jobDynamicScheduler.setScheduler(quartzScheduler.getObject());
        return jobDynamicScheduler;
    }

    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        frBean.setFilter(characterEncodingFilter);
        frBean.addUrlPatterns("/*");
        return frBean;
    }

    /**
     * 设置500，,404界面
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error");
            container.addErrorPages(error404Page, error500Page);
        };
    }
}
