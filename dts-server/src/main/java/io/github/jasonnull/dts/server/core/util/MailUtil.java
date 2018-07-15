package io.github.jasonnull.dts.server.core.util;

import io.github.jasonnull.dts.server.core.conf.JobServerConfig;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * 邮件发送.Util
 */
public class MailUtil {
    private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * @param toAddress   收件人邮箱
     * @param mailSubject 邮件主题
     * @param mailBody    邮件正文
     * @return
     */
    public static boolean sendMail(String toAddress, String mailSubject, String mailBody) {

        try {
            // Create the email message
            HtmlEmail email = new HtmlEmail();

            //email.setDebug(true);        // 将会打印一些log
            //email.setTLS(true);        // 是否TLS校验，，某些邮箱需要TLS安全校验，同理有SSL校验
            //email.setSSL(true);

            email.setHostName(JobServerConfig.getAdminConfig().getMailHost());

            if (JobServerConfig.getAdminConfig().isMailSSL()) {
                email.setSslSmtpPort(JobServerConfig.getAdminConfig().getMailPort());
                email.setSSLOnConnect(true);
            } else {
                email.setSmtpPort(Integer.valueOf(JobServerConfig.getAdminConfig().getMailPort()));
            }

            email.setAuthenticator(new DefaultAuthenticator(JobServerConfig.getAdminConfig().getMailUsername(), JobServerConfig.getAdminConfig().getMailPassword()));
            email.setCharset(Charset.defaultCharset().name());

            email.setFrom(JobServerConfig.getAdminConfig().getMailUsername(), JobServerConfig.getAdminConfig().getMailSendNick());
            email.addTo(toAddress);
            email.setSubject(mailSubject);
            email.setMsg(mailBody);

            //email.attach(attachment);    // add the attachment

            email.send();                // send the email
            return true;
        } catch (EmailException e) {
            logger.error(e.getMessage(), e);

        }
        return false;
    }

}
