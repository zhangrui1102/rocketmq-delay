package org.apache.rocketmq.store.delay;

import org.apache.rocketmq.store.delay.store.PeriodicFlushService;
import org.apache.rocketmq.store.delay.store.log.DispatchLog;
import org.apache.rocketmq.store.delay.store.log.ScheduleLog;

public class LogFlusher implements Switchable {
    private final PeriodicFlushService scheduleLogFlushService;
    private final PeriodicFlushService dispatchLogFlushService;

    LogFlusher(ScheduleLog scheduleLog, DispatchLog dispatchLog) {
        this.dispatchLogFlushService = new PeriodicFlushService(dispatchLog.getProvider());
        this.scheduleLogFlushService = new PeriodicFlushService(scheduleLog.getProvider());
    }

    @Override
    public void start() {
        dispatchLogFlushService.start();
        scheduleLogFlushService.start();
    }

    @Override
    public void shutdown() {
        dispatchLogFlushService.close();
        scheduleLogFlushService.close();
    }

}
