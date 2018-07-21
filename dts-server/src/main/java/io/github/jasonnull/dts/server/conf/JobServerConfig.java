package io.github.jasonnull.dts.server.conf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 */
@Configuration
public class JobServerConfig implements InitializingBean {
    private static JobServerConfig adminConfig = null;
    @Value("${dts.job.mail.host}")
    private String mailHost;
    @Value("${dts.job.mail.port}")
    private String mailPort;
    @Value("${dts.job.mail.ssl}")
    private boolean mailSSL;
    @Value("${dts.job.mail.username}")
    private String mailUsername;
    @Value("${dts.job.mail.password}")
    private String mailPassword;
    @Value("${dts.job.mail.sendNick}")
    private String mailSendNick;
    @Value("${dts.job.login.username}")
    private String loginUsername;
    @Value("${dts.job.login.password}")
    private String loginPassword;
    @Value("${dts.job.i18n}")
    private String i18n;

    public static JobServerConfig getAdminConfig() {
        return adminConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;
    }

    public String getMailHost() {
        return mailHost;
    }

    public String getMailPort() {
        return mailPort;
    }

    public boolean isMailSSL() {
        return mailSSL;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public String getMailSendNick() {
        return mailSendNick;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getI18n() {
        return i18n;
    }

}
