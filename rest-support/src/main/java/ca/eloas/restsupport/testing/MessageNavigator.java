package ca.eloas.restsupport.testing;

import ca.eloas.restsupport.messages.LinkedMessage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.hamcrest.Matcher;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static ca.eloas.restsupport.testing.ClientResponseMatcher.statusCode;
import static ca.eloas.restsupport.testing.RestTestUtils.check;
import static ca.eloas.restsupport.testing.RestTestUtils.findLink;
import static org.hamcrest.CoreMatchers.is;


/**
 * @author JP
 */
public class MessageNavigator {

    private Client client;
    private LinkedMessage message;
    private NewCookie cookie;

    public MessageNavigator(Client client, NewCookie cookie, LinkedMessage message) {
        this.message = message;
        this.cookie = cookie;
        this.client = client;
    }

    public ResponseNavigator click(String name) throws MalformedURLException, URISyntaxException {

        return click(statusCode(is(200)), name);
    }


    public ResponseNavigator click(Matcher<ClientResponse> matcher, String name) throws MalformedURLException, URISyntaxException {

        URL link = findLink(message, name);

        ClientResponse cr = check(matcher, client.resource(link.toURI())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .cookie(cookie).get(ClientResponse.class));
        return new ResponseNavigator(cookie, cr);
    }

    public URL link(String name) throws MalformedURLException {
        return findLink(message, name);
    }
}
