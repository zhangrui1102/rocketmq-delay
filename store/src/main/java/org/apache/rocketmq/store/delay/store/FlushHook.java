package org.apache.rocketmq.store.delay.store;

public interface FlushHook {
    void beforeFlush();
}
