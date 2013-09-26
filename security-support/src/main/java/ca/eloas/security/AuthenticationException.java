package ca.eloas.security;

/**
 * @author JP
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
    }

    public AuthenticationException(String s) {
        super(s);
    }

    public AuthenticationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }
}
