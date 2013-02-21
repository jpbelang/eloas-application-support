package ca.eloas.security;

import com.google.appengine.api.users.UserService;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author JP
 */
public class AuthenticationAuthorizationServiceImpl implements AuthenticationAuthorizationService {

    @Inject
    Provider<HttpSession> session;

    @Inject
    UserService service;

    public boolean isAuthenticated() {

        AuthInformation ai = getAuthInformation();

        return ai != null || service.isUserLoggedIn();
    }

    public boolean isAdmin() {

        AuthInformation ai = getAuthInformation();

        return isAuthenticated() && ( ai != null || (service.isUserLoggedIn() && service.isUserAdmin()));
    }

    private AuthInformation getAuthInformation() {
        return (AuthInformation) session.get().getAttribute("auth");
    }

    public URL getLogoutURL() {

        try {
            if (isAuthenticated() && session.get().getAttribute("admin") != null) {

                return new URL("http://google.com");
            }

            if (isAuthenticated() && service.isUserLoggedIn()) {

                return new URL(service.createLogoutURL("http://google.com"));
            }

            return new URL("http://www.altavista.com");
        } catch (MalformedURLException e) {

            throw new RuntimeException(e);
        }
    }

    public void authenticate(Authenticatable auth) throws AuthenticationException {

        if ( auth == null ) {

            throw new AuthenticationException();
        }

        AuthInformation ai = auth.authenticate();

        session.get().setAttribute("auth", ai);
    }

    public SystemUser getUser() {

        if (!isAuthenticated()) {

            return null;
        }

        if (getAuthInformation() != null) {

            return new SystemUser() {
                public String getLoginName() {
                    return getAuthInformation().getPrincipal().getName();
                }

                public String getRealName() {
                    return "";
                }
            };
        } else {

            return new SystemUser() {
                public String getLoginName() {
                    return service.getCurrentUser().getEmail();
                }

                public String getRealName() {
                    return service.getCurrentUser().getNickname();
                }
            };
        }
    }
}
