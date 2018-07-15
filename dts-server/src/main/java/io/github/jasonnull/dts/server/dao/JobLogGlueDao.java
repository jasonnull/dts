package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobLogGlue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * job log for glue
 */
public interface JobLogGlueDao {

    public int save(JobLogGlue jobLogGlue);

    public List<JobLogGlue> findByJobId(@Param("jobId") int jobId);

    public int removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

    public int deleteByJobId(@Param("jobId") int jobId);

}
