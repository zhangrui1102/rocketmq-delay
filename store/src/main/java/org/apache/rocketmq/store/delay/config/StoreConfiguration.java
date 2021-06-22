package org.apache.rocketmq.store.delay.config;


import org.apache.rocketmq.store.delay.configuration.DynamicConfig;

public interface StoreConfiguration {
    DynamicConfig getConfig();

    String getScheduleLogStorePath();

    String getDispatchLogStorePath();

    int getSingleMessageLimitSize();

    String getCheckpointStorePath();

    int getLoadSegmentDelayMinutes();

    long getDispatchLogKeepTime();

    long getCheckCleanTimeBeforeDispatch();

    long getLogCleanerIntervalSeconds();

    String getScheduleOffsetCheckpointPath();

    long getLoadInAdvanceTimesInMillis();

    long getLoadBlockingExitTimesInMillis();

    boolean isDeleteExpiredLogsEnable();

    int getSegmentScale();
}
