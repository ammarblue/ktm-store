package org.ktm.exception;

public class KTMException extends Exception {

    private static final long serialVersionUID = 1L;

    public KTMException(String message) {
        super(message);
    }

    public KTMException(Throwable cause) {
        super(cause);
    }

    public KTMException(String message, Throwable cause) {
        super(message, cause);
    }
}
