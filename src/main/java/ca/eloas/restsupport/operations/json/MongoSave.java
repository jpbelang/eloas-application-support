package ca.eloas.restsupport.operations.json;

import ca.eloas.restsupport.ToModelOperation;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.BSONObject;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;

/**
 * @author JP
 */
public class MongoSave implements ToModelOperation<DBObject, JSONObject> {

    @Inject
    private static JSONOperationFactory factory;

    private DBCollection collection;

    @AssistedInject
    public MongoSave(@Assisted DBCollection collection) {
        this.collection = collection;
    }

    @Override
    public void run(JSONObject jsonObject, DBObject object) throws Exception {

        collection.save(object);

    }

    public static MongoSave mongoSave(DBCollection c) {

        return factory.createMongoSave(c);
    }
}
