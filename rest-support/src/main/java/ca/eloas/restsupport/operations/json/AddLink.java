package ca.eloas.restsupport.operations.json;

import ca.eloas.restsupport.ToMessageOperation;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.UriInfo;

/**
 * @author JP
 */
public class AddLink implements ToMessageOperation<Object, JSONObject> {

    Provider<UriInfo> builderProvider;

    private final String target;
    private final String name;


    @AssistedInject
    public AddLink(Provider<UriInfo> info, @Assisted("one") String name, @Assisted("two") String target) {

        this.builderProvider = info;
        this.name = name;
        this.target = target;
    }

    public void run(Object object, JSONObject linkedMessage) throws Exception {

        if (!linkedMessage.has("links")) {

            linkedMessage.put("links", new JSONArray());
        }
        JSONArray links = linkedMessage.getJSONArray("links");
        JSONObject link = new JSONObject();
        link.put("name", name);
        link.put("URL", builderProvider.get().getBaseUriBuilder().path(target).build().toString());
        links.put(link);
    }
}
