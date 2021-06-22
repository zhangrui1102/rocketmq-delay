package org.apache.rocketmq.store.delay.store;

import org.apache.rocketmq.store.delay.store.log.DelaySegment;

import java.io.IOException;

public class DefaultDelaySegmentValidator implements DelaySegmentValidator {

    @Override
    public long validate(DelaySegment segment) throws IOException {
        return segment.validate();
    }
}
