package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.core.model.JobLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 */
public interface JobLogDao {

    public List<JobLog> pageList(@Param("offset") int offset,
                                 @Param("pagesize") int pagesize,
                                 @Param("jobGroup") int jobGroup,
                                 @Param("jobId") int jobId,
                                 @Param("triggerTimeStart") Date triggerTimeStart,
                                 @Param("triggerTimeEnd") Date triggerTimeEnd,
                                 @Param("logStatus") int logStatus);

    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("jobGroup") int jobGroup,
                             @Param("jobId") int jobId,
                             @Param("triggerTimeStart") Date triggerTimeStart,
                             @Param("triggerTimeEnd") Date triggerTimeEnd,
                             @Param("logStatus") int logStatus);

    public JobLog load(@Param("id") int id);

    public int save(JobLog jobLog);

    public int updateTriggerInfo(JobLog jobLog);

    public int updateHandleInfo(JobLog jobLog);

    public int delete(@Param("jobId") int jobId);

    public int triggerCountByHandleCode(@Param("handleCode") int handleCode);

    public List<Map<String, Object>> triggerCountByDay(@Param("from") Date from,
                                                       @Param("to") Date to);

    public int clearLog(@Param("jobGroup") int jobGroup,
                        @Param("jobId") int jobId,
                        @Param("clearBeforeTime") Date clearBeforeTime,
                        @Param("clearBeforeNum") int clearBeforeNum);

}
