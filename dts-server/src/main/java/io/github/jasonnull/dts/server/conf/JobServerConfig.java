package io.github.jasonnull.dts.server.conf;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 */
@Data
@Configuration
public class JobServerConfig implements InitializingBean {
    private static JobServerConfig adminConfig = null;

    private String loginUsername = "admin";

    private String loginPassword = "123456";

    private String i18n = "";

    public static JobServerConfig getAdminConfig() {
        return adminConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;
    }
}
