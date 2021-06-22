package org.apache.rocketmq.store.delay.store.log;

import org.apache.rocketmq.store.delay.store.DefaultDelaySegmentValidator;
import org.apache.rocketmq.store.delay.DelaySyncRequest;
import org.apache.rocketmq.store.delay.store.buffer.SegmentBuffer;
import org.apache.rocketmq.store.delay.cleaner.LogCleaner;
import org.apache.rocketmq.store.delay.config.StoreConfiguration;
import org.apache.rocketmq.store.delay.store.PeriodicFlushService;
import org.apache.rocketmq.store.delay.store.appender.DispatchLogAppender;

import java.io.File;
import java.nio.ByteBuffer;

public class DispatchLog extends AbstractDelayLog<Boolean> {

    private static final int DEFAULT_FLUSH_INTERVAL = 500;

    public DispatchLog(StoreConfiguration storeConfiguration) {
        super(new DispatchLogSegmentContainer(storeConfiguration,
                new File(storeConfiguration.getDispatchLogStorePath())
                , new DefaultDelaySegmentValidator(), new DispatchLogAppender()));
    }

    public PeriodicFlushService.FlushProvider getProvider() {
        return new PeriodicFlushService.FlushProvider() {
            @Override
            public int getInterval() {
                return DEFAULT_FLUSH_INTERVAL;
            }

            @Override
            public void flush() {
                DispatchLog.this.flush();
            }
        };
    }

    public DispatchLogSegment latestSegment() {
        return ((DispatchLogSegmentContainer) container).latestSegment();
    }

    public void clean(LogCleaner.CleanHook hook) {
        ((DispatchLogSegmentContainer) container).clean(hook);
    }

    public SegmentBuffer getDispatchLogData(long segmentBaseOffset, long dispatchLogOffset) {
        return ((DispatchLogSegmentContainer) container).getDispatchData(segmentBaseOffset, dispatchLogOffset);
    }

    public long getMaxOffset(long dispatchSegmentBaseOffset) {
        return ((DispatchLogSegmentContainer) container).getMaxOffset(dispatchSegmentBaseOffset);
    }

    public DelaySyncRequest.DispatchLogSyncRequest getSyncMaxRequest() {
        return ((DispatchLogSegmentContainer) container).getSyncMaxRequest();
    }

    public boolean appendData(long startOffset, long baseOffset, ByteBuffer body) {
        return ((DispatchLogSegmentContainer) container).appendData(startOffset, baseOffset, body);
    }

    public DispatchLogSegment lowerSegment(long latestOffset) {
        return ((DispatchLogSegmentContainer) container).lowerSegment(latestOffset);
    }

    public long higherBaseOffset(long low) {
        return ((DispatchLogSegmentContainer) container).higherBaseOffset(low);
    }
}
