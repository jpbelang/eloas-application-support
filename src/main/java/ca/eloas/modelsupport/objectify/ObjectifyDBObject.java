package ca.eloas.modelsupport.objectify;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.modelsupport.KeyType;
import com.googlecode.objectify.Key;

/**
 * @author JP
 */
public interface ObjectifyDBObject<TYPE, K extends KeyType> extends DBObject<K> {

    Key<TYPE> getKey();
}
