package org.apache.rocketmq.store.delay.store.visitor;

import java.util.Optional;

public interface LogVisitor<T> {

    Optional<T> nextRecord();

    long visitedBufferSize();

    void close();

}
