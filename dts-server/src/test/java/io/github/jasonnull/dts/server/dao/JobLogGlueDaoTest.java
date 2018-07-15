package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobLogGlue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobLogGlueDaoTest {

    @Resource
    private JobLogGlueDao jobLogGlueDao;

    @Test
    public void test() {
        JobLogGlue logGlue = new JobLogGlue();
        logGlue.setJobId(1);
        logGlue.setGlueType("1");
        logGlue.setGlueSource("1");
        logGlue.setGlueRemark("1");
        int ret = jobLogGlueDao.save(logGlue);

        List<JobLogGlue> list = jobLogGlueDao.findByJobId(1);

        int ret2 = jobLogGlueDao.removeOld(1, 1);

        int ret3 = jobLogGlueDao.deleteByJobId(1);
    }

}
