package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobLog;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobLogDaoTest {

    @Resource
    private JobLogDao jobLogDao;

    @Test
    public void test() {
        List<JobLog> list = jobLogDao.pageList(0, 10, 1, 1, null, null, 1);
        int list_count = jobLogDao.pageListCount(0, 10, 1, 1, null, null, 1);

        JobLog log = new JobLog();
        log.setJobGroup(1);
        log.setJobId(1);

        int ret1 = jobLogDao.save(log);
        JobLog dto = jobLogDao.load(log.getId());

        log.setGlueType("1");
        log.setTriggerTime(new Date());
        log.setTriggerCode(1);
        log.setTriggerMsg("1");
        log.setExecutorAddress("1");
        log.setExecutorHandler("1");
        log.setExecutorParam("1");
        ret1 = jobLogDao.updateTriggerInfo(log);
        dto = jobLogDao.load(log.getId());


        log.setHandleTime(new Date());
        log.setHandleCode(2);
        log.setHandleMsg("2");
        ret1 = jobLogDao.updateHandleInfo(log);
        dto = jobLogDao.load(log.getId());


        List<Map<String, Object>> list2 = jobLogDao.triggerCountByDay(DateUtils.addDays(new Date(), 30), new Date());

        int ret4 = jobLogDao.clearLog(1, 1, new Date(), 100);

        int ret2 = jobLogDao.delete(log.getJobId());

        int ret3 = jobLogDao.triggerCountByHandleCode(-1);
    }

}
