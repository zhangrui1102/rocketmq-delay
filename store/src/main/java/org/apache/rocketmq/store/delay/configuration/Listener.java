package org.apache.rocketmq.store.delay.configuration;

public interface Listener {
    void onLoad(DynamicConfig config);
}
