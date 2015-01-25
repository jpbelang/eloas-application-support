package ca.eloas.restsupport.testing;

import ca.eloas.modelsupport.ObjectTranslator;
import ca.eloas.restsupport.messages.LinkedMessage;
import com.sun.jersey.api.client.ClientResponse;
import org.codehaus.jettison.json.JSONObject;
import org.hamcrest.Matcher;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static ca.eloas.restsupport.testing.ClientResponseMatcher.isOK;
import static ca.eloas.restsupport.testing.ClientResponseMatcher.statusCode;
import static ca.eloas.restsupport.testing.RestTestUtils.check;
import static ca.eloas.restsupport.testing.RestTestUtils.findLink;
import static org.hamcrest.core.Is.is;


/**
 * @author JP
 */
public class ResponseNavigator {

    private ObjectTranslator objectFactory;

    private ClientResponse resp;
    private NewCookie cookie;

    public ResponseNavigator(NewCookie cookie, ClientResponse r) {
        this.resp = r;
        this.cookie = cookie;
    }

    public <T> T extractMessage(Class<T> cls, ClientResponse cr) {

        return (T) objectFactory.fromExternalToDomain(cls, cr.getEntity(JSONObject.class));
    }



    public URLNavigator follow(String linkName) throws MalformedURLException {

        LinkedMessage lm = extractMessage(LinkedMessage.class, resp);
        URL link = findLink(lm, linkName);

        return new URLNavigator(cookie, resp.getClient(), link);


    }

    public ResponseNavigator click(String name) throws MalformedURLException, URISyntaxException {

        return click(statusCode(is(200)), name);
    }

    public ResponseNavigator click(Matcher<ClientResponse> matcher, String name) throws MalformedURLException, URISyntaxException {
        LinkedMessage lm = extractMessage(LinkedMessage.class, resp);
        URL link = findLink(lm, name);

        if (link == null) {

            throw new IllegalArgumentException("link \"" + name + "\" not found");
        }

        ClientResponse cr = check(matcher, resp.getClient().resource(link.toURI())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .cookie(cookie).get(ClientResponse.class));

        return new ResponseNavigator(cookie, cr);
    }

    public ClientResponse response() {

        return resp;
    }

    public <C> C message(Class<C> cls) {

        if (isOK().matches(resp)) {
            return extractMessage(cls, resp);
        } else {
            throw new IllegalArgumentException("response was in error: " + resp.getStatus());
        }
    }


    public URL link(String name) throws MalformedURLException {

        if (isOK().matches(resp)) {
            LinkedMessage lm = extractMessage(LinkedMessage.class, resp);
            URL link = findLink(lm, name);

            return link;
        } else {

            throw new IllegalArgumentException("response was in error: " + resp.getStatus());
        }
    }
}
