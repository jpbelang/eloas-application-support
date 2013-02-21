package ca.eloas.security;

import java.security.Principal;

/**
 * @author JP
 */
public class SimpleAuthInformation implements AuthInformation {

    private String name;
    private boolean admin;


    public SimpleAuthInformation(String name, boolean admin) {
        this.name = name;
        this.admin = admin;
    }

    @Override
    public Principal getPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return name;
            }
        };
    }

    @Override
    public String[] getRoles() {

        if ( admin == true ) {

            return new String[] {StandardRoles.ADMIN, StandardRoles.USER};
        } else {

            return new String[] {StandardRoles.USER};
        }
    }

    @Override
    public boolean isAdmin() {
        return admin;
    }
}
