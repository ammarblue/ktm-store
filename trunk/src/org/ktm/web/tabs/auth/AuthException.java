package org.ktm.web.tabs.auth;

public class AuthException extends Exception {

    private static final long serialVersionUID = 1L;

    public AuthException() {
        super();
    }
    
    public AuthException(String message) {
        super(message);
    }

    public AuthException(Exception cause) {
        super(cause.getMessage());
   }
}
