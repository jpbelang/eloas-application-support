package ca.eloas.security;

import java.net.URL;

/**
 * @author JP
 */
public interface AuthenticationAuthorizationService {

    boolean isAuthenticated();
    boolean isAdmin();
    SystemUser getUser();

    URL getLogoutURL();
    void authenticate(Authenticatable auth) throws AuthenticationException;
    boolean isUserInRoles(String... value);

    public AuthInformation getAuthInformation();

    }
