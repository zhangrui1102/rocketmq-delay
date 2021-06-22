package org.apache.rocketmq.store.delay.store;

import org.apache.rocketmq.store.delay.store.log.DelaySegment;

import java.io.IOException;

public interface DelaySegmentValidator {

    long validate(DelaySegment segment) throws IOException;
}
