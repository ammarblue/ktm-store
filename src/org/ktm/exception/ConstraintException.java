package org.ktm.exception;

public class ConstraintException extends KTMException {

    private static final long serialVersionUID = 1L;

    public ConstraintException(String message) {
        super(message);
    }

    public ConstraintException(Throwable cause) {
        super(cause);
    }

    public ConstraintException(String message, Throwable cause) {
        super(message, cause);
    }

}
