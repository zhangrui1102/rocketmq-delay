package org.apache.rocketmq.store.delay.store.model;

public class ScheduleSetSequence {
    private final long scheduleTime;

    public ScheduleSetSequence(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public long getScheduleTime() {
        return scheduleTime;
    }

}
