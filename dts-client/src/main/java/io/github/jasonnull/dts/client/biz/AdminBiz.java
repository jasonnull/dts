package io.github.jasonnull.dts.client.biz;

import io.github.jasonnull.dts.client.biz.model.HandleCallbackParam;
import io.github.jasonnull.dts.client.biz.model.RegistryParam;
import io.github.jasonnull.dts.client.biz.model.ReturnT;

import java.util.List;


public interface AdminBiz {

    public static final String MAPPING = "/api";


    // ---------------------- callback ----------------------

    /**
     * callback
     *
     * @param callbackParamList
     * @return
     */
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);


    // ---------------------- registry ----------------------

    /**
     * registry
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registryRemove(RegistryParam registryParam);


    // ---------------------- job opt ----------------------

    /**
     * trigger job for once
     *
     * @param jobId
     * @return
     */
    public ReturnT<String> triggerJob(Long jobId);

}
