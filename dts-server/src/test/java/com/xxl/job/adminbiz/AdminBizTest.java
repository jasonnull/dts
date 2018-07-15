package io.github.jasonnull.dts.serverbiz;

import io.github.jasonnull.dts.client.biz.AdminBiz;
import io.github.jasonnull.dts.client.biz.model.RegistryParam;
import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.client.enums.RegistryConfig;
import io.github.jasonnull.dts.client.rpc.netcom.NetComClientProxy;
import org.junit.Assert;
import org.junit.Test;

/**
 * admin api test
 */
public class AdminBizTest {

    // admin-client
    private static String addressUrl = "http://127.0.0.1:8080/xxl-job-admin".concat(AdminBiz.MAPPING);
    private static String accessToken = null;

    /**
     * registry executor
     *
     * @throws Exception
     */
    @Test
    public void registryTest() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        // test executor registry
        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), "xxl-job-executor-example", "127.0.0.1:9999");
        ReturnT<String> returnT = adminBiz.registry(registryParam);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

    /**
     * registry executor remove
     *
     * @throws Exception
     */
    @Test
    public void registryRemove() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        // test executor registry remove
        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), "xxl-job-executor-example", "127.0.0.1:9999");
        ReturnT<String> returnT = adminBiz.registryRemove(registryParam);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

    /**
     * trigger job for once
     *
     * @throws Exception
     */
    @Test
    public void triggerJob() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        int jobId = 1;
        ReturnT<String> returnT = adminBiz.triggerJob(jobId);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

}
