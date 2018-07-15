package io.github.jasonnull.dts.server.core.route.strategy;

import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.client.biz.model.TriggerParam;
import io.github.jasonnull.dts.server.core.route.ExecutorRouter;
import io.github.jasonnull.dts.server.core.trigger.JobTrigger;

import java.util.ArrayList;
import java.util.Random;


public class ExecutorRouteRandom extends ExecutorRouter {

    private static Random localRandom = new Random();

    public String route(int jobId, ArrayList<String> addressList) {
        // Collections.shuffle(addressList);
        return addressList.get(localRandom.nextInt(addressList.size()));
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
