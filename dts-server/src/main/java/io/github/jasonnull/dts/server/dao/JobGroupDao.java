package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface JobGroupDao {

    public List<JobGroup> findAll();

    public List<JobGroup> findByAddressType(@Param("addressType") int addressType);

    public int save(JobGroup jobGroup);

    public int update(JobGroup jobGroup);

    public int remove(@Param("id") int id);

    public JobGroup load(@Param("id") int id);
}
