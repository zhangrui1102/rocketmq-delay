package org.apache.rocketmq.store.delay.store;

public interface Serde<V> {
    byte[] toBytes(final V value);

    V fromBytes(final byte[] data);
}
