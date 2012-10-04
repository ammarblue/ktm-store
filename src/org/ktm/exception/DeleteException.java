package org.ktm.exception;

public class DeleteException extends StorageException {

    private static final long serialVersionUID = 1L;

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

}
