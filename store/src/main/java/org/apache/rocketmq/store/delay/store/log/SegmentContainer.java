package org.apache.rocketmq.store.delay.store.log;

public interface SegmentContainer<R, T> {

    R append(T record);

    boolean clean(Long key);

    void flush();
}
