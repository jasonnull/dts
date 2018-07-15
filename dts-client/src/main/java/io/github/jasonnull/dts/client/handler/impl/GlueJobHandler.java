package io.github.jasonnull.dts.client.handler.impl;

import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.client.handler.IJobHandler;
import io.github.jasonnull.dts.client.log.XxlJobLogger;

/**
 * glue job handler
 */
public class GlueJobHandler extends IJobHandler {

    private long glueUpdatetime;
    private IJobHandler jobHandler;

    public GlueJobHandler(IJobHandler jobHandler, long glueUpdatetime) {
        this.jobHandler = jobHandler;
        this.glueUpdatetime = glueUpdatetime;
    }

    public long getGlueUpdatetime() {
        return glueUpdatetime;
    }

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("----------- glue.version:" + glueUpdatetime + " -----------");
        return jobHandler.execute(param);
    }

}
