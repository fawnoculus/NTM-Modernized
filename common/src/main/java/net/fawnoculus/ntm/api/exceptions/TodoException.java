package net.fawnoculus.ntm.api.exceptions;

/**
 * Exception to indicate that something hasn't been coded yet
 */
public class TodoException extends RuntimeException {
    public TodoException() {
        super();
    }

    public TodoException(String message) {
        super(message);
    }

    public TodoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoException(Throwable cause) {
        super(cause);
    }

    public TodoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
