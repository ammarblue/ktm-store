package org.ktm.exception;

public class UpdateException extends StorageException {

    private static final long serialVersionUID = 1L;

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
