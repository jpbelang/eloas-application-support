package ca.eloas.restsupport.operations.json;

import ca.eloas.restsupport.messages.Link;
import ca.eloas.restsupport.messages.LinkedMessage;
import ca.eloas.restsupport.operations.MethodSensitiveToMessageOperation;
import com.google.inject.assistedinject.AssistedInject;
import org.bson.BSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.UriInfo;

/**
 * @author JP
 */
public class SelfLink extends MethodSensitiveToMessageOperation<BSONObject, JSONObject> {

    @Inject
    private static JSONOperationFactory factory;

    Provider<UriInfo> builderProvider;

    @AssistedInject
    public SelfLink(Provider<UriInfo> info) {

        this.builderProvider = info;
    }


    @Override
    protected void runForPutDeleteAndGet(BSONObject object,  JSONObject linkedMessage) throws Exception {

        if ( ! linkedMessage.has("links") ) {

            linkedMessage.put("links", new JSONArray());
        }
        JSONArray links = linkedMessage.getJSONArray("links");
        JSONObject link = new JSONObject();
        link.put("name", "self");
        link.put("URL", builderProvider.get().getRequestUriBuilder().replaceQuery(null).build().toString());
        links.put(link);
    }

    @Override
    protected void runFofPostAndList(BSONObject object, JSONObject linkedMessage) throws Exception {

        if ( object == null ) {

            runForPutDeleteAndGet(object, linkedMessage);
            return;
        }

        if ( ! linkedMessage.has("links") ) {

            linkedMessage.put("links", new JSONArray());
        }
        JSONArray links = linkedMessage.getJSONArray("links");
        JSONObject link = new JSONObject();
        link.put("name", "self");
        link.put("URL",  builderProvider.get().getRequestUriBuilder().replaceQuery(null).path((String) object.get("id")).build().toString());
        links.put(link);

    }

    public static SelfLink selfLink() {

        return factory.createSelfLink();
    }
}
