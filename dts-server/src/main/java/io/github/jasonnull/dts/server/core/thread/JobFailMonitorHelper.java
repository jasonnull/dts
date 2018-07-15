package io.github.jasonnull.dts.server.core.thread;

import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.server.core.model.JobGroup;
import io.github.jasonnull.dts.server.core.model.JobInfo;
import io.github.jasonnull.dts.server.core.model.JobLog;
import io.github.jasonnull.dts.server.core.schedule.JobDynamicScheduler;
import io.github.jasonnull.dts.server.core.util.I18nUtil;
import io.github.jasonnull.dts.server.core.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * job monitor instance
 */
public class JobFailMonitorHelper {
    // email alarm template
    private static final String mailBodyTemplate = "<h5>" + I18nUtil.getString("jobconf_monitor_detail") + "：</span>" +
            "<table border=\"1\" cellpadding=\"3\" style=\"border-collapse:collapse; width:80%;\" >\n" +
            "   <thead style=\"font-weight: bold;color: #ffffff;background-color: #ff8c00;\" >" +
            "      <tr>\n" +
            "         <td width=\"20%\" >" + I18nUtil.getString("jobinfo_field_jobgroup") + "</td>\n" +
            "         <td width=\"10%\" >" + I18nUtil.getString("jobinfo_field_id") + "</td>\n" +
            "         <td width=\"20%\" >" + I18nUtil.getString("jobinfo_field_jobdesc") + "</td>\n" +
            "         <td width=\"10%\" >" + I18nUtil.getString("jobconf_monitor_alarm_title") + "</td>\n" +
            "         <td width=\"40%\" >" + I18nUtil.getString("jobconf_monitor_alarm_content") + "</td>\n" +
            "      </tr>\n" +
            "   <thead/>\n" +
            "   <tbody>\n" +
            "      <tr>\n" +
            "         <td>{0}</td>\n" +
            "         <td>{1}</td>\n" +
            "         <td>{2}</td>\n" +
            "         <td>" + I18nUtil.getString("jobconf_monitor_alarm_type") + "</td>\n" +
            "         <td>{3}</td>\n" +
            "      </tr>\n" +
            "   <tbody>\n" +
            "</table>";
    private static Logger logger = LoggerFactory.getLogger(JobFailMonitorHelper.class);
    private static JobFailMonitorHelper instance = new JobFailMonitorHelper();

    // ---------------------- monitor ----------------------
    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(0xfff8);

    private Thread monitorThread;
    private volatile boolean toStop = false;

    public static JobFailMonitorHelper getInstance() {
        return instance;
    }

    // producer
    public static void monitor(int jobLogId) {
        getInstance().queue.offer(jobLogId);
    }

    public void start() {
    }


    // ---------------------- alarm ----------------------

    public void toStop() {
        toStop = true;
        // interrupt and wait
        monitorThread.interrupt();
        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * fail alarm
     *
     * @param jobLog
     */
    private void failAlarm(JobLog jobLog) {

        // send monitor email
        JobInfo info = JobDynamicScheduler.jobInfoDao.loadById(jobLog.getJobId());
        if (info != null && info.getAlarmEmail() != null && info.getAlarmEmail().trim().length() > 0) {

            String alarmContent = "Alarm Job LogId=" + jobLog.getId();
            if (jobLog.getTriggerCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>TriggerMsg=" + jobLog.getTriggerMsg();
            }
            if (jobLog.getHandleCode() > 0 && jobLog.getHandleCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>HandleCode=" + jobLog.getHandleMsg();
            }

            Set<String> emailSet = new HashSet<String>(Arrays.asList(info.getAlarmEmail().split(",")));
            for (String email : emailSet) {
                JobGroup group = JobDynamicScheduler.jobGroupDao.load(Integer.valueOf(info.getJobGroup()));

                String title = I18nUtil.getString("jobconf_monitor");
                String content = MessageFormat.format(mailBodyTemplate,
                        group != null ? group.getTitle() : "null",
                        info.getId(),
                        info.getJobDesc(),
                        alarmContent);

                MailUtil.sendMail(email, title, content);
            }
        }

        // TODO, custom alarm strategy, such as sms

    }

}
