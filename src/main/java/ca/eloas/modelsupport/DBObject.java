package ca.eloas.modelsupport;

/**
 * @author JP
 */
public interface DBObject<K extends KeyType> {

    K getObjectId();
}
