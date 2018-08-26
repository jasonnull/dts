package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 */
public interface JobLogDao {

    public List<JobLog> pageList(@Param("offset") Integer offset,
                                 @Param("pagesize") Integer pagesize,
                                 @Param("jobGroup") Long jobGroup,
                                 @Param("jobId") Long jobId,
                                 @Param("triggerTimeStart") Date triggerTimeStart,
                                 @Param("triggerTimeEnd") Date triggerTimeEnd,
                                 @Param("logStatus") Integer logStatus);

    public Integer pageListCount(@Param("offset") Integer offset,
                             @Param("pagesize") Integer pagesize,
                             @Param("jobGroup") Long jobGroup,
                             @Param("jobId") Long jobId,
                             @Param("triggerTimeStart") Date triggerTimeStart,
                             @Param("triggerTimeEnd") Date triggerTimeEnd,
                             @Param("logStatus") Integer logStatus);

    public JobLog load(@Param("id") Long id);

    public int save(JobLog jobLog);

    public int updateTriggerInfo(JobLog jobLog);

    public int updateHandleInfo(JobLog jobLog);

    public int delete(@Param("jobId") Long jobId);

    public Integer triggerCountByHandleCode(@Param("handleCode") Integer handleCode);

    public List<Map<String, Object>> triggerCountByDay(@Param("from") Date from,
                                                       @Param("to") Date to);

    public int clearLog(@Param("jobGroup") Long jobGroup,
                        @Param("jobId") Long jobId,
                        @Param("clearBeforeTime") Date clearBeforeTime,
                        @Param("clearBeforeNum") Integer clearBeforeNum);

}
