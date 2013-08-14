package ca.eloas.restsupport.operations.json;

import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.ToModelOperation;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.Map;

/**
 * @author JP
 */
public class CopyToModel implements ToModelOperation<BSONObject, JSONObject> {

    @Inject
    private static JSONOperationFactory factory;


    @Override
    public void run(JSONObject jsonObject, BSONObject object) throws Exception {


        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String next = (String) it.next();
            Object field = jsonObject.get(next);
            if ( field instanceof JSONObject ) {

                object.put(next, new BasicBSONObject());
                run((JSONObject)field, (BSONObject)object.get(next));
                continue;
            }

            if ( jsonObject.get(next) instanceof JSONArray) {

                continue;
            }

            object.put(next, field);
        }

    }

    public static CopyToModel copyToModel() {

        return factory.createCopyToModel();
    }
}
