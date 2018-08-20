package io.github.jasonnull.dts.server.service;


import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.server.model.JobInfo;

import java.util.Date;
import java.util.Map;

/**
 * core job action for xxl-job
 */
public interface JobService {

    /**
     * page list
     *
     * @param start
     * @param length
     * @param jobGroup
     * @param jobDesc
     * @param executorHandler
     * @param filterTime
     * @return
     */
    public Map<String, Object> pageList(int start, int length, Long jobGroup, String jobDesc, String executorHandler, String filterTime);

    /**
     * add job
     *
     * @param jobInfo
     * @return
     */
    public ReturnT<String> add(JobInfo jobInfo);

    /**
     * update job
     *
     * @param jobInfo
     * @return
     */
    public ReturnT<String> update(JobInfo jobInfo);

    /**
     * remove job
     *
     * @param id
     * @return
     */
    public ReturnT<String> remove(Long id);

    /**
     * pause job
     *
     * @param id
     * @return
     */
    public ReturnT<String> pause(Long id);

    /**
     * resume job
     *
     * @param id
     * @return
     */
    public ReturnT<String> resume(Long id);

    /**
     * trigger job
     *
     * @param id
     * @return
     */
    public ReturnT<String> triggerJob(Long id);

    /**
     * dashboard info
     *
     * @return
     */
    public Map<String, Object> dashboardInfo();

    /**
     * chart info
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate);

}
