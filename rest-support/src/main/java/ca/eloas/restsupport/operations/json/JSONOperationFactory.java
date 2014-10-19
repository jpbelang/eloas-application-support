package ca.eloas.restsupport.operations.json;


import com.google.inject.assistedinject.Assisted;
import com.mongodb.DBCollection;

/**
 * @author JP
 */
public interface JSONOperationFactory {

    SelfLink selfLink();
    CopyToMessage copyToMessage();
    CopyToModel copyToModel();
    AddLink addLink(@Assisted("one") String name, @Assisted("two") String target);
    SetId setId();
    MongoSave mongoSave(@Assisted DBCollection collection);
}
