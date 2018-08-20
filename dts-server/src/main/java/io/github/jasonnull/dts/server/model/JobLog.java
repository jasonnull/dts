package io.github.jasonnull.dts.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jasonnull.dts.server.conf.JacksonModel;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * xxl-job log, used to track trigger process
 */
@Alias("JobLog")
@Data
public class JobLog {
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long id;

    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    /**
     * 执行器主键ID    (JobKey.group)
     */
    private Long jobGroup;
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long jobId;

    /**
     * glueType
     */
    private String glueType;


    private String executorAddress;
    private String executorHandler;
    private String executorParam;

    private Date triggerTime;
    private int triggerCode;
    private String triggerMsg;

    private Date handleTime;
    private int handleCode;
    private String handleMsg;

    public void setTriggerMsg(String triggerMsg) {
        // plugin
        if (triggerMsg != null && triggerMsg.length() > 2000) {
            triggerMsg = triggerMsg.substring(0, 2000);
        }
        this.triggerMsg = triggerMsg;
    }

    public void setHandleMsg(String handleMsg) {
        // plugin
        if (handleMsg != null && handleMsg.length() > 2000) {
            handleMsg = handleMsg.substring(0, 2000);
        }
        this.handleMsg = handleMsg;
    }
}
