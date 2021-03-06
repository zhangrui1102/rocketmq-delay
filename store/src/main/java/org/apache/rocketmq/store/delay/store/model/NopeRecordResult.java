package org.apache.rocketmq.store.delay.store.model;

import org.apache.rocketmq.store.delay.store.AppendMessageResult;
import org.apache.rocketmq.store.delay.store.AppendMessageStatus;
import org.apache.rocketmq.store.delay.store.PutStatus;

public class NopeRecordResult implements RecordResult {

    private PutStatus status;

    public NopeRecordResult(PutStatus status) {
        this.status = status;
    }

    @Override
    public PutStatus getStatus() {
        return status;
    }

    @Override
    public AppendMessageResult<Void> getResult() {
        return new AppendMessageResult<>(AppendMessageStatus.UNKNOWN_ERROR, -1, -1);
    }
}
