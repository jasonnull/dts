package io.github.jasonnull.dts.client.biz.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HandleCallbackParam implements Serializable {
    private static final long serialVersionUID = 42L;

    private Long logId;
    private long logDateTim;

    private ReturnT<String> executeResult;

    public HandleCallbackParam() {
    }

    public HandleCallbackParam(Long logId, long logDateTim, ReturnT<String> executeResult) {
        this.logId = logId;
        this.logDateTim = logDateTim;
        this.executeResult = executeResult;
    }

    @Override
    public String toString() {
        return "HandleCallbackParam{" +
                "logId=" + logId +
                ", logDateTim=" + logDateTim +
                ", executeResult=" + executeResult +
                '}';
    }

}
