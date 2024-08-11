package com.github.polyrocketmatt.cyclone.impl.exception;

public class CycloneException extends RuntimeException {

    public CycloneException(String message, Object... args) {
        super(String.format(message, args));
    }

    public CycloneException(String cause, Throwable throwable) {
        super(cause, throwable);
    }

    public CycloneException(String cause) {
        super(cause);
    }

    public CycloneException(Throwable throwable) {
        super(throwable);
    }

    public CycloneException() {
        super();
    }

}
