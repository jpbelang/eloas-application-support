package ca.eloas.security;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * @author JP
 */
@Singleton
public class PageSecurityFilter implements Filter {


    @Inject
    @Named("pageSecurityFilterConfig")
    Properties configuration;

    @Inject
    AuthenticationAuthorizationService security;

    public void destroy() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();
        String[] patterns = configuration.getProperty("protected.admin.pages", "/admin.*").split("\\s+,\\s+");
        for (String pattern : patterns) {

            if ( path.matches(pattern) ) {

                if ( ! security.isAdmin() ) {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Role");
                    return;
                }
            }
        }

        patterns = configuration.getProperty("protected.user.pages", "/profile.*").split("\\s+,\\s+");
        for (String pattern : patterns) {

            if ( path.matches(pattern) ) {

                if ( ! security.isAuthenticated() ) {

                    redirectIntelligently(req, resp);
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    private void redirectIntelligently(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String loginPage = configuration.getProperty("authentication.page", "/site/login.html");

        String path = req.getRequestURL().toString();
        resp.sendRedirect(loginPage + "?returnTo=" + path);
    }
}
