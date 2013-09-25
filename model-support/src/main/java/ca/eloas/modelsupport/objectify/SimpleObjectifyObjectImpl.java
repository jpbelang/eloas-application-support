package ca.eloas.modelsupport.objectify;

import ca.eloas.modelsupport.LongKey;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Id;


public class SimpleObjectifyObjectImpl<TYPE> implements ObjectifyDBObject<TYPE, LongKey> {

    @Id
    private Long id;

    public Key<TYPE> getKey() {

        if ( getParentKey() == null ) {
            return (Key<TYPE>) Key.create(this.getClass(), getObjectId().getKey());
        } else {

            return (Key<TYPE>) Key.create(getParentKey(), this.getClass(), getObjectId().getKey());
        }
    }

    public LongKey getObjectId() {
        return new LongKey(id);
    }

    protected Key<?> getParentKey() {

        return null;
    }
}

