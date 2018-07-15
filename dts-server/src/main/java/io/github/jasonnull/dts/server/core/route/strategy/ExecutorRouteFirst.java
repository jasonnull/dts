package io.github.jasonnull.dts.server.core.route.strategy;

import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.client.biz.model.TriggerParam;
import io.github.jasonnull.dts.server.core.route.ExecutorRouter;
import io.github.jasonnull.dts.server.core.trigger.JobTrigger;

import java.util.ArrayList;


public class ExecutorRouteFirst extends ExecutorRouter {

    public String route(int jobId, ArrayList<String> addressList) {
        return addressList.get(0);
    }

    @Override
    public ReturnT<String> routeRun(TriggerParam triggerParam, ArrayList<String> addressList) {

        // address
        String address = route(triggerParam.getJobId(), addressList);

        // run executor
        ReturnT<String> runResult = JobTrigger.runExecutor(triggerParam, address);
        runResult.setContent(address);
        return runResult;
    }
}
