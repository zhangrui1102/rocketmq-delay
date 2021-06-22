package org.apache.rocketmq.store.delay.store.log;

import org.apache.rocketmq.store.delay.store.model.AppendLogResult;

public interface Log<R, T> {
    AppendLogResult<R> append(T record);

    boolean clean(Long key);

    void flush();
}
