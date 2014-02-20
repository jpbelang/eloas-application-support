package ca.eloas.jsresources;

import org.eclipse.jetty.util.IO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author JP
 */
public class JSServlet extends HttpServlet {


    public JSServlet() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            long year = 86400L * 365L * 1000;
            long expiry = System.currentTimeMillis() + year;

            // Nothing really done here for caching management.
            resp.setDateHeader("Expires", expiry);
            resp.setHeader("Cache-Control", "max-age="+ 2592000);

            String resource = "META-INF/resources/" + new URI(req.getRequestURI()).getPath().substring(req.getContextPath().length() + 1);
            if ( resource.endsWith(".js")) {

                resp.setContentType("application/x-javascript");
            }

            if ( resource.endsWith(".css")) {

                resp.setContentType("text/css");
            }

            InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(resource);
            if ( resourceStream == null ) {

                throw new ServletException("no such resource: " + resource);
            }

            IO.copy(resourceStream, resp.getOutputStream());
        } catch (URISyntaxException e) {
            throw new ServletException(e);
        }
    }
}
