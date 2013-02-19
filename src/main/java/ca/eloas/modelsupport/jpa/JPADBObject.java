package ca.eloas.modelsupport.jpa;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.modelsupport.KeyType;

/**
 * @author JP
 */
public interface JPADBObject<K extends KeyType> extends DBObject<K> {

    long getVersion();
    boolean isPersisted();
}
