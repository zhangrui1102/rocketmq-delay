package org.apache.rocketmq.store.delay.store.log;

import org.apache.rocketmq.store.delay.store.model.LogRecord;
import org.apache.rocketmq.store.delay.store.AppendMessageResult;
import org.apache.rocketmq.store.delay.store.appender.LogAppender;

import java.io.IOException;

public interface DelaySegment<T> {

    AppendMessageResult<T> append(LogRecord log, LogAppender<T, LogRecord> appender);

    void setWrotePosition(long position);

    long getWrotePosition();

    void setFlushedPosition(long position);

    long getFlushedPosition();

    long getSegmentBaseOffset();

    long validate() throws IOException;

    boolean destroy();

    long flush();
}
