package org.apache.rocketmq.store.delay.store.buffer;

import java.nio.ByteBuffer;

public interface Buffer {
    ByteBuffer getBuffer();

    int getSize();
}
