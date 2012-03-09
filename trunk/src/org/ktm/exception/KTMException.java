package org.ktm.exception;

public class KTMException extends Exception {

	private static final long serialVersionUID = 6947527086283998843L;

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
