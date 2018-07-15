package io.github.jasonnull.dts.server.core.enums;

import io.github.jasonnull.dts.server.core.util.I18nUtil;


public enum ExecutorFailStrategyEnum {

    NULL(I18nUtil.getString("jobconf_fail_null")),

    FAIL_TRIGGER_RETRY(I18nUtil.getString("jobconf_fail_trigger_retry")),

    FAIL_HANDLE_RETRY(I18nUtil.getString("jobconf_fail_handle_retry"));

    private final String title;

    private ExecutorFailStrategyEnum(String title) {
        this.title = title;
    }

    public static ExecutorFailStrategyEnum match(String name, ExecutorFailStrategyEnum defaultItem) {
        if (name != null) {
            for (ExecutorFailStrategyEnum item : ExecutorFailStrategyEnum.values()) {
                if (item.name().equals(name)) {
                    return item;
                }
            }
        }
        return defaultItem;
    }

    public String getTitle() {
        return title;
    }

}
