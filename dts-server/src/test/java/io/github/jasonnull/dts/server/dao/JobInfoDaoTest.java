package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobInfoDaoTest {

    @Resource
    private JobInfoDao jobInfoDao;

    @Test
    public void pageList() {
        List<JobInfo> list = jobInfoDao.pageList(0, 20, 0L, null, null);
        int list_count = jobInfoDao.pageListCount(0, 20, 0L, null, null);

        System.out.println(list);
        System.out.println(list_count);

        List<JobInfo> list2 = jobInfoDao.getJobsByGroup(1L);
    }

    @Test
    public void save_load() {
        JobInfo info = new JobInfo();
        info.setJobGroup(1L);
        info.setJobCron("jobCron");
        info.setJobDesc("desc");
        info.setAuthor("setAuthor");
        info.setAlarmEmail("setAlarmEmail");
        info.setExecutorRouteStrategy("setExecutorRouteStrategy");
        info.setExecutorHandler("setExecutorHandler");
        info.setExecutorParam("setExecutorParam");
        info.setExecutorBlockStrategy("setExecutorBlockStrategy");
        info.setExecutorFailStrategy("setExecutorFailStrategy");
        info.setGlueType("setGlueType");
        info.setGlueSource("setGlueSource");
        info.setGlueRemark("setGlueRemark");
        info.setChildJobId("1");

        int count = jobInfoDao.save(info);

        JobInfo info2 = jobInfoDao.loadById(info.getJobId());
        info2.setJobCron("jobCron2");
        info2.setJobDesc("desc2");
        info2.setAuthor("setAuthor2");
        info2.setAlarmEmail("setAlarmEmail2");
        info2.setExecutorRouteStrategy("setExecutorRouteStrategy2");
        info2.setExecutorHandler("setExecutorHandler2");
        info2.setExecutorParam("setExecutorParam2");
        info2.setExecutorBlockStrategy("setExecutorBlockStrategy2");
        info2.setExecutorFailStrategy("setExecutorFailStrategy2");
        info2.setGlueType("setGlueType2");
        info2.setGlueSource("setGlueSource2");
        info2.setGlueRemark("setGlueRemark2");
        info2.setGlueUpdatetime(new Date());
        info2.setChildJobId("1");

        int item2 = jobInfoDao.update(info2);

        jobInfoDao.delete(info2.getJobId());

        List<JobInfo> list2 = jobInfoDao.getJobsByGroup(1L);

        int ret3 = jobInfoDao.findAllCount();

    }

}
