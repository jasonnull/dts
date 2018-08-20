package io.github.jasonnull.dts.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jasonnull.dts.server.conf.JacksonModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * xxl-job log for glue, used to track job code process
 */
@Alias("JobLogGlue")
@Data
public class JobLogGlue {
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long id;

    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    /**
     *     任务主键ID
      */
    private Long jobId;

    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    /**
     * 执行器主键ID    (JobKey.group)
     */
    private Long jobGroup;

    /**
     * GLUE类型    #io.github.jasonnull.dts.client.glue.GlueTypeEnum
     */
    private String glueType;
    private String glueSource;
    private String glueRemark;
    private String addTime;
    private String updateTime;
}
