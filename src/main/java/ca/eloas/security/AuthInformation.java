package ca.eloas.security;

import java.io.Serializable;
import java.security.Principal;

/**
 * @author JP
 */
public interface AuthInformation extends Serializable {
    Principal getPrincipal();
    String[] getRoles();
    boolean isAdmin();
}
