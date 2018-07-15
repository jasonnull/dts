package io.github.jasonnull.dts.client.biz;

import io.github.jasonnull.dts.client.biz.model.LogResult;
import io.github.jasonnull.dts.client.biz.model.ReturnT;
import io.github.jasonnull.dts.client.biz.model.TriggerParam;


public interface ExecutorBiz {

    /**
     * beat
     *
     * @return
     */
    public ReturnT<String> beat();

    /**
     * idle beat
     *
     * @param jobId
     * @return
     */
    public ReturnT<String> idleBeat(int jobId);

    /**
     * kill
     *
     * @param jobId
     * @return
     */
    public ReturnT<String> kill(int jobId);

    /**
     * log
     *
     * @param logDateTim
     * @param logId
     * @param fromLineNum
     * @return
     */
    public ReturnT<LogResult> log(long logDateTim, int logId, int fromLineNum);

    /**
     * run
     *
     * @param triggerParam
     * @return
     */
    public ReturnT<String> run(TriggerParam triggerParam);

}
