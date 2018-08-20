package io.github.jasonnull.dts.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jasonnull.dts.server.conf.JacksonModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("JobRegistry")
@Data
public class JobRegistry {
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long id;

    private String registryGroup;

    /**
     * 执行器主键ID    (JobKey.group)
     */
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long jobGroup;

    private String registryKey;
    private String registryValue;
    private Date updateTime;
}
