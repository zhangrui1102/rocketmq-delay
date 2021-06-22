package org.apache.rocketmq.store.delay.configuration;

public interface DynamicConfigFactory {
    DynamicConfig create(String name);
}
