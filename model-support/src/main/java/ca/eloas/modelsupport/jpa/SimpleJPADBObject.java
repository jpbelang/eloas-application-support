package ca.eloas.modelsupport.jpa;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.modelsupport.KeyType;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;
import java.util.UUID;

/**
 * @author JP
 */
@MappedSuperclass
public class SimpleJPADBObject implements JPADBObject<UUIDKey> {


    // Don't think this is legal.
    @Id
    private String key;

    @Version
    private long version = -1;

    public UUIDKey getObjectId() {
        return new UUIDKey(UUID.fromString(key));
    }

    @Override
    public long getVersion() {
        return version ;
    }


    public boolean isPersisted() {

        return version != -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;

        DBObject that = (DBObject) o;

        if (key != null ? !this.getObjectId().equals(that.getObjectId()) : that.getObjectId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {

        return this.getClass() + "[" + key + ", " + version + "]";
    }

}
