package io.github.jasonnull.dts.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jasonnull.dts.server.conf.JacksonModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * xxl-job info
 */
@Alias("JobInfo")
@Data
public class JobInfo {

    /**
     * 主键ID        (JobKey.name)
     */
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long jobId;

    /**
     * 执行器主键ID    (JobKey.group)
     */
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long jobGroup;
    /**
     * 任务执行CRON表达式 【base on quartz】
     */
    private String jobCron;
    private String jobDesc;

    private Date addTime;
    private Date updateTime;

    /**
     *  负责人
     */
    private String author;

    /**
     * 报警邮件
     */
    private String alarmEmail;

    /**
     *  执行器路由策略
     */
    private String executorRouteStrategy;

    /**
     * 执行器，任务Handler名称
     */
    private String executorHandler;

    /**
     * 执行器，任务参数
     */
    private String executorParam;

    /**
     * 阻塞处理策略
     */
    private String executorBlockStrategy;

    /**
     * 失败处理策略
     */
    private String executorFailStrategy;

    /**
     * 任务执行超时时间，单位秒
     */
    private int executorTimeout;

    /**
     * GLUE类型    #io.github.jasonnull.dts.client.glue.GlueTypeEnum
     */
    private String glueType;

    /**
     * GLUE源代码
     */
    private String glueSource;

    /**
     * GLUE备注
     */
    private String glueRemark;

    /**
     * GLUE更新时间
     */
    private Date glueUpdatetime;

    /**
     * 子任务ID，多个逗号分隔
     */
    private String childJobId;

    /**
     *  // 任务状态 【base on quartz】
     * copy from quartz
     */
    private String jobStatus;
}
