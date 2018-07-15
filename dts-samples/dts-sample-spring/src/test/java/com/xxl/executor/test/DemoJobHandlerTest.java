package com.xxl.executor.test;

import io.github.jasonnull.dts.client.biz.ExecutorBiz;
import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.client.biz.model.TriggerParam;
import io.github.jasonnull.dts.client.enums.ExecutorBlockStrategyEnum;
import io.github.jasonnull.dts.client.glue.GlueTypeEnum;
import io.github.jasonnull.dts.client.rpc.netcom.NetComClientProxy;

/**
 * executor-api client, test
 * <p>
 */
public class DemoJobHandlerTest {

    public static void main(String[] args) throws Exception {

        // param
        String jobHandler = "demoJobHandler";
        String params = "";

        // trigger data
        TriggerParam triggerParam = new TriggerParam();
        triggerParam.setJobId(1);
        triggerParam.setExecutorHandler(jobHandler);
        triggerParam.setExecutorParams(params);
        triggerParam.setExecutorBlockStrategy(ExecutorBlockStrategyEnum.COVER_EARLY.name());
        triggerParam.setGlueType(GlueTypeEnum.BEAN.name());
        triggerParam.setGlueSource(null);
        triggerParam.setGlueUpdatetime(System.currentTimeMillis());
        triggerParam.setLogId(1);
        triggerParam.setLogDateTim(System.currentTimeMillis());

        // do remote trigger
        String accessToken = null;
        ExecutorBiz executorBiz = (ExecutorBiz) new NetComClientProxy(ExecutorBiz.class, "127.0.0.1:9999", null).getObject();
        ReturnT<String> runResult = executorBiz.run(triggerParam);
    }

}
