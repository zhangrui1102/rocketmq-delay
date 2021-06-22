package org.apache.rocketmq.store.delay.exception;

public class AppendException extends RuntimeException {

    public AppendException(String message) {
        super(message);
    }
}
