package io.github.jasonnull.dts.server.route.strategy;

import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.client.biz.model.TriggerParam;
import io.github.jasonnull.dts.server.route.ExecutorRouter;
import io.github.jasonnull.dts.server.trigger.JobTrigger;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


public class ExecutorRouteRound extends ExecutorRouter {

    private static ConcurrentHashMap<Long, Integer> routeCountEachJob = new ConcurrentHashMap<Long, Integer>();
    private static long CACHE_VALID_TIME = 0;

    private static int count(Long jobId) {
        // cache clear
        if (System.currentTimeMillis() > CACHE_VALID_TIME) {
            routeCountEachJob.clear();
            CACHE_VALID_TIME = System.currentTimeMillis() + 1000 * 60 * 60 * 24;
        }

        // count++
        Integer count = routeCountEachJob.get(jobId);
        count = (count == null || count > 1000000) ? (new Random().nextInt(100)) : ++count;  // 初始化时主动Random一次，缓解首次压力
        routeCountEachJob.put(jobId, count);
        return count;
    }

    public String route(Long jobId, ArrayList<String> addressList) {
        return addressList.get(count(jobId) % addressList.size());
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
