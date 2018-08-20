package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobGroupDao {

    public List<JobGroup> findAll();

    public List<JobGroup> findByAddressType(@Param("addressType") int addressType);

    public int save(JobGroup jobGroup);

    public int update(JobGroup jobGroup);

    public int remove(@Param("jobGroup") Long jobGroup);

    public JobGroup load(@Param("jobGroup") Long jobGroup);
}
