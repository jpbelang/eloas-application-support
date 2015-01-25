package ca.eloas.restsupport.testing;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.modelsupport.ObjectTranslator;
import ca.eloas.modelsupport.Translator;
import ca.eloas.restsupport.messages.LinkedMessage;
import ca.eloas.restsupport.messages.MessagePart;
import com.sun.jersey.api.client.Client;
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
public class URLNavigator {

    private Translator<MessagePart, JSONObject> objectFactory;

    private final Client client;
    private URL url;
    private NewCookie cookie;

    public URLNavigator(NewCookie cookie, Client client, URL link) {

        this.cookie = cookie;
        this.url = link;
        this.client = client;
    }

    public <T> T extractMessage(Class<T> cls, ClientResponse cr) {

        return null;
        //return objectFactory.toDomainObject(cls, cr.getEntity(JSONObject.class));
    }

    public JSONObject extractJSON(Object message) {

        return null;
        //return objectFactory.(message);
    }


    public ResponseNavigator click() throws MalformedURLException, URISyntaxException {

        return click(statusCode(is(200)));
    }

    public ResponseNavigator click(Matcher<ClientResponse> matcher) throws MalformedURLException, URISyntaxException {
        ClientResponse cr = check(matcher,
                client.resource(url.toURI())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .cookie(cookie).get(ClientResponse.class));

        return new ResponseNavigator(cookie, cr);
    }

    public <T extends MessagePart> T create(T message) throws URISyntaxException {

        JSONObject jo = objectFactory.fromDomainToExternal(JSONObject.class, message);
        ClientResponse cr = check(statusCode(is(201)),
                client.resource(url.toURI())
                        .accept(MediaType.APPLICATION_JSON_TYPE)
                        .cookie(cookie)
                        .post(ClientResponse.class, jo));

        return (T) objectFactory.fromExternalToDomain(MessagePart.class, cr.getEntity(JSONObject.class));

    }
}
