package ca.eloas.security;

/**
 * @author JP
 */
public interface Authenticatable {

    AuthInformation authenticate() throws AuthenticationException;
}
