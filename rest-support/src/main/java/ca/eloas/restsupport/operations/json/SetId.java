package ca.eloas.restsupport.operations.json;

import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.ToModelOperation;
import com.google.inject.assistedinject.AssistedInject;
import org.bson.BSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

/**
 * @author JP
 */
public class SetId implements ToModelOperation<BSONObject, JSONObject> {


    @AssistedInject
    public SetId() {

    }

    public void run(JSONObject linkedMessage, BSONObject object) throws Exception {

        object.put("id", UUID.randomUUID().toString());
    }

}
