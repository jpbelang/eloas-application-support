package ca.eloas.modelsupport.json;

import com.mongodb.DBObject;

/**
 * @author JP
 */
public interface DBObjectTranslator {

    DBObject toDBObject(Object o);
    <T> T toObject(Class<T> type, DBObject dbObject);
    <T extends MongoDBObject> T createObject(Class<T> type);
}
