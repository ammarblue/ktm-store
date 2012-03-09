package org.ktm.exception;

public class KTMError extends Error {

	private static final long serialVersionUID = -9134587685071658540L;

	public KTMError(String message) {
		super(message);
	}
}
