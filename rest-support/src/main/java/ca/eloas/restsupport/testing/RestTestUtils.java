package ca.eloas.restsupport.testing;

import ca.eloas.restsupport.messages.Link;
import ca.eloas.restsupport.messages.LinkedMessage;
import com.sun.jersey.api.client.ClientResponse;
import org.codehaus.jettison.json.JSONObject;
import org.hamcrest.Matcher;

import javax.ws.rs.core.NewCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author JP
 */
public class RestTestUtils {

    /*
        static public <T> T extractMessage(Class<T> cls, ClientResponse cr) {

            return JSONObjectFactory.createObject(cls, cr.getEntity(JSONObject.class));
        }

        static public JSONObject extractJSON(Object message) {

            return JSONObjectFactory.extractObject(message);
        }

    */
    static public URL findLink(LinkedMessage cr, String name) throws MalformedURLException {

        for (Link link : cr.getLinks()) {
            if (link.getName().equals(name)) {

                return new URL(link.getURL());
            }
        }

        return null;
    }

    static public ClientResponse check(Matcher<ClientResponse> matcher, ClientResponse clientResponse) {

        assertThat(clientResponse, matcher);
        return clientResponse;
    }

/*
    static public NewCookie globalSessionCookie(List<NewCookie> cookies) {
        return globalSessionCookie(cookies, "");
    }

    static public NewCookie globalSessionCookie(List<NewCookie> cookies, String suffix){
        for(NewCookie cookie: cookies){
            if(cookie.getName().equals(COOKIE_NAME_OF_GLOBAL_SESSION + suffix)){
                return cookie;
            }
        }
        return new NewCookie("generated_cookie", "generated_cookie");
    }
*/

}
