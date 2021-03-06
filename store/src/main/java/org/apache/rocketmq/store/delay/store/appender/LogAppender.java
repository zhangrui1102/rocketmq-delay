package org.apache.rocketmq.store.delay.store.appender;


import org.apache.rocketmq.store.delay.store.model.AppendRecordResult;

public interface LogAppender<R, T> {

    AppendRecordResult<R> appendLog(final T log);

    void lockAppender();

    void unlockAppender();

}
