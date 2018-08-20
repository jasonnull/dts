package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * job info
 */
@Mapper
public interface JobInfoDao {

    public List<JobInfo> pageList(@Param("offset") int offset,
                                  @Param("pagesize") int pagesize,
                                  @Param("jobGroup") Long jobGroup,
                                  @Param("jobDesc") String jobDesc,
                                  @Param("executorHandler") String executorHandler);

    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("jobGroup") Long jobGroup,
                             @Param("jobDesc") String jobDesc,
                             @Param("executorHandler") String executorHandler);

    public int save(JobInfo info);

    public JobInfo loadById(@Param("jobId") Long jobId);

    public int update(JobInfo item);

    public int delete(@Param("jobId") Long jobId);

    public List<JobInfo> getJobsByGroup(@Param("jobGroup") Long jobGroup);

    public int findAllCount();

}
