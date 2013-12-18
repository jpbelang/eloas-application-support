package ca.eloas.modelsupport.json;

import com.mongodb.DBObject;

/**
 * @author JP
 */
public interface DBObjectTranslator {

    DBObject toDBObject(Object o);
    <T> Object toObject(Class<T> type, DBObject dbObject);
}
