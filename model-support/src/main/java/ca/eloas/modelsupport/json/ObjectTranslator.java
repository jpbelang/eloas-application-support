package ca.eloas.modelsupport.json;


import ca.eloas.modelsupport.DBObject;

/**
 * @author JP
 */
public interface ObjectTranslator<DBTYPE> {

    DBTYPE toDBObject(DBObject o);
    <T extends DBObject> T toDomainObject(Class<T> type, DBTYPE dbObject);
}
