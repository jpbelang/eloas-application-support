package ca.eloas.restsupport.operations.json;

import ca.eloas.restsupport.messages.Link;
import ca.eloas.restsupport.messages.LinkedMessage;
import ca.eloas.restsupport.operations.MethodSensitiveToMessageOperation;
import com.google.inject.assistedinject.AssistedInject;
import com.mongodb.DBObject;
import org.bson.BSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.UriInfo;

/**
 * @author JP
 */
public class SelfLink extends MethodSensitiveToMessageOperation<DBObject, JSONObject> {

    private Provider<UriInfo> builderProvider;

    @AssistedInject
    public SelfLink(Provider<UriInfo> info, Provider<Method> m) {

        super(m);
        this.builderProvider = info;
    }


    @Override
    protected void runForPutDeleteAndGet(DBObject object, JSONObject linkedMessage) throws Exception {

        if (!linkedMessage.has("links")) {

            linkedMessage.put("links", new JSONArray());
        }
        JSONArray links = linkedMessage.getJSONArray("links");
        JSONObject link = new JSONObject();
        link.put("name", "self");
        link.put("URL", builderProvider.get().getRequestUriBuilder().replaceQuery(null).build().toString());
        links.put(link);
    }

    @Override
    protected void runFofPostAndList(DBObject object, JSONObject linkedMessage) throws Exception {

        if (object == null) {

            runForPutDeleteAndGet(object, linkedMessage);
            return;
        }

        if (!linkedMessage.has("links")) {

            linkedMessage.put("links", new JSONArray());
        }
        JSONArray links = linkedMessage.getJSONArray("links");
        JSONObject link = new JSONObject();
        link.put("name", "self");
        link.put("URL", builderProvider.get().getRequestUriBuilder().replaceQuery(null).path((String) object.get("id")).build().toString());
        links.put(link);

    }
}
