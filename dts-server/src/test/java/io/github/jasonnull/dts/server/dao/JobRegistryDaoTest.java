package io.github.jasonnull.dts.server.dao;

import io.github.jasonnull.dts.server.model.JobRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobRegistryDaoTest {

    @Resource
    private JobRegistryDao jobRegistryDao;

    @Test
    public void test() {
        int ret = jobRegistryDao.registryUpdate("g1", "k1", "v1");
        if (ret < 1) {
            ret = jobRegistryDao.registrySave("g1", "k1", "v1");
        }

        List<JobRegistry> list = jobRegistryDao.findAll(1);

        int ret2 = jobRegistryDao.removeDead(1);
    }

}
