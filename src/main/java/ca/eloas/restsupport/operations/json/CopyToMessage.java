package ca.eloas.restsupport.operations.json;

import ca.eloas.restsupport.ToMessageOperation;
import com.google.inject.assistedinject.AssistedInject;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * @author JP
 */
public class CopyToMessage implements ToMessageOperation<BSONObject, JSONObject> {

    @Inject
    private static JSONOperationFactory factory;

    @AssistedInject
    public CopyToMessage() {

    }


    @Override
    public void run(BSONObject object, JSONObject jsonObject) throws Exception {


        Iterator it = object.keySet().iterator();
        while (it.hasNext()) {
            String next = (String) it.next();
            Object field = object.get(next);
            if ( jsonObject.get(next) instanceof List) {

                continue;
            }

            if ( field instanceof BSONObject ) {

                object.put(next, new BasicBSONObject());
                run((BSONObject)object.get(next), (JSONObject)field);
                continue;
            }


            jsonObject.put(next, field);
        }

    }


    public static CopyToMessage copyToMessage() {

        return factory.createCopyToMessage();
    }
}
