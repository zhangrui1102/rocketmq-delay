package org.apache.rocketmq.store.delay.store.model;

import org.apache.rocketmq.store.delay.store.AppendMessageResult;
import org.apache.rocketmq.store.delay.store.PutStatus;

public interface RecordResult<T> {

    PutStatus getStatus();

    AppendMessageResult<T> getResult();
}
