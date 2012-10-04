package org.ktm.exception;

public class KTMError extends Error {

    private static final long serialVersionUID = 1L;

    public KTMError(String message) {
        super(message);
    }
}
