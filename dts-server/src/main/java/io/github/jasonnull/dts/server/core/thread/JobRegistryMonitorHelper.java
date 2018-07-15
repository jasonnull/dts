package io.github.jasonnull.dts.server.core.thread;

import io.github.jasonnull.dts.client.enums.RegistryConfig;
import io.github.jasonnull.dts.server.core.model.JobGroup;
import io.github.jasonnull.dts.server.core.model.JobRegistry;
import io.github.jasonnull.dts.server.core.schedule.JobDynamicScheduler;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * job registry instance
 */
public class JobRegistryMonitorHelper {
    private static Logger logger = LoggerFactory.getLogger(JobRegistryMonitorHelper.class);

    private static JobRegistryMonitorHelper instance = new JobRegistryMonitorHelper();
    private Thread registryThread;
    private volatile boolean toStop = false;

    public static JobRegistryMonitorHelper getInstance() {
        return instance;
    }

    public void start() {
        registryThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!toStop) {
                    try {
                        // auto registry group
                        List<JobGroup> groupList = JobDynamicScheduler.jobGroupDao.findByAddressType(0);
                        if (CollectionUtils.isNotEmpty(groupList)) {

                            // remove dead address (admin/executor)
                            JobDynamicScheduler.jobRegistryDao.removeDead(RegistryConfig.DEAD_TIMEOUT);

                            // fresh online address (admin/executor)
                            HashMap<String, List<String>> appAddressMap = new HashMap<String, List<String>>();
                            List<JobRegistry> list = JobDynamicScheduler.jobRegistryDao.findAll(RegistryConfig.DEAD_TIMEOUT);
                            if (list != null) {
                                for (JobRegistry item : list) {
                                    if (RegistryConfig.RegistType.EXECUTOR.name().equals(item.getRegistryGroup())) {
                                        String appName = item.getRegistryKey();
                                        List<String> registryList = appAddressMap.get(appName);
                                        if (registryList == null) {
                                            registryList = new ArrayList<String>();
                                        }

                                        if (!registryList.contains(item.getRegistryValue())) {
                                            registryList.add(item.getRegistryValue());
                                        }
                                        appAddressMap.put(appName, registryList);
                                    }
                                }
                            }

                            // fresh group address
                            for (JobGroup group : groupList) {
                                List<String> registryList = appAddressMap.get(group.getAppName());
                                String addressListStr = null;
                                if (CollectionUtils.isNotEmpty(registryList)) {
                                    Collections.sort(registryList);
                                    addressListStr = StringUtils.join(registryList, ",");
                                }
                                group.setAddressList(addressListStr);
                                JobDynamicScheduler.jobGroupDao.update(group);
                            }
                        }
                    } catch (Exception e) {
                        logger.error("job registry instance error:{}", e);
                    }
                    try {
                        TimeUnit.SECONDS.sleep(RegistryConfig.BEAT_TIMEOUT);
                    } catch (InterruptedException e) {
                        logger.error("job registry instance error:{}", e);
                    }
                }
            }
        });
        registryThread.setDaemon(true);
        registryThread.start();
    }

    public void toStop() {
        toStop = true;
        // interrupt and wait
        registryThread.interrupt();
        try {
            registryThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
