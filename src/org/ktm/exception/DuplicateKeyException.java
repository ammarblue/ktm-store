package org.ktm.exception;

public class DuplicateKeyException extends CreateException {

    private static final long serialVersionUID = 1L;

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

}
