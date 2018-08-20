package io.github.jasonnull.dts.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jasonnull.dts.server.conf.JacksonModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Alias("JobGroup")
@Data
public class JobGroup {
    @JsonSerialize(using = JacksonModel.LongJsonSerializer.class)
    @JsonDeserialize(using = JacksonModel.LongJsonDeserializer.class)
    private Long jobGroup;
    private String appName;
    private String title;
    private int order;

    /**
     * 执行器地址类型：0=自动注册、1=手动录入
     */
    private int addressType;

    /**
     * 执行器地址列表，多地址逗号分隔(手动录入)
     */
    private String addressList;

    /**
     * registry list
     * 执行器地址列表(系统注册)
     */
    private List<String> registryList;

    public List<String> getRegistryList() {
        if (StringUtils.isNotBlank(addressList)) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }
}
