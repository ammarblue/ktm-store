package org.ktm.exception;

public class CreateException extends StorageException {

    private static final long serialVersionUID = 1L;

    public CreateException(String message) {
        super(message);
    }

    public CreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateException(Throwable cause) {
        super(cause);
    }
}
