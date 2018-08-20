package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobGroupDaoTest {

    @Resource
    private JobGroupDao jobGroupDao;

    @Test
    public void test() {
        List<JobGroup> list = jobGroupDao.findAll();

        List<JobGroup> list2 = jobGroupDao.findByAddressType(0);

        JobGroup group = new JobGroup();
        group.setAppName("setAppName");
        group.setTitle("setTitle");
        group.setOrder(1);
        group.setAddressType(0);
        group.setAddressList("setAddressList");

        int ret = jobGroupDao.save(group);

        JobGroup group2 = jobGroupDao.load(group.getJobGroup());
        group2.setAppName("setAppName2");
        group2.setTitle("setTitle2");
        group2.setOrder(2);
        group2.setAddressType(2);
        group2.setAddressList("setAddressList2");

        int ret2 = jobGroupDao.update(group2);

        int ret3 = jobGroupDao.remove(group.getJobGroup());
    }

}
