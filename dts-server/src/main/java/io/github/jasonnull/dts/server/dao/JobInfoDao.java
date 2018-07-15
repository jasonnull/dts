package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * job info
 */
public interface JobInfoDao {

    public List<JobInfo> pageList(@Param("offset") int offset,
                                  @Param("pagesize") int pagesize,
                                  @Param("jobGroup") int jobGroup,
                                  @Param("jobDesc") String jobDesc,
                                  @Param("executorHandler") String executorHandler);

    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("jobGroup") int jobGroup,
                             @Param("jobDesc") String jobDesc,
                             @Param("executorHandler") String executorHandler);

    public int save(JobInfo info);

    public JobInfo loadById(@Param("id") int id);

    public int update(JobInfo item);

    public int delete(@Param("id") int id);

    public List<JobInfo> getJobsByGroup(@Param("jobGroup") int jobGroup);

    public int findAllCount();

}
