package ca.eloas.modelsupport;

import org.bson.types.ObjectId;

/**
 * @author JP
 */
public class ObjectIdKey implements KeyType<ObjectId> {

    private ObjectId key;

    public ObjectIdKey(ObjectId key) {
        this.key = key;
    }

    @Override
    public String asString() {
        return key.toString();
    }

    @Override
    public ObjectId getKey() {
        return key;
    }
}
