package ca.eloas.modelsupport.json;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.modelsupport.KeyType;
import ca.eloas.modelsupport.ObjectIdKey;

/**
 * @author JP
 */
public interface MongoDBObject<K extends KeyType> extends DBObject<K> {

}
