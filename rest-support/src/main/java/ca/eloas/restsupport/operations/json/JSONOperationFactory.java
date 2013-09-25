package ca.eloas.restsupport.operations.json;


import com.google.inject.assistedinject.Assisted;
import com.mongodb.DBCollection;

/**
 * @author JP
 */
public interface JSONOperationFactory {

    SelfLink createSelfLink();
    CopyToMessage createCopyToMessage();
    CopyToModel createCopyToModel();
    AddLink createAddLink(@Assisted("one") String name, @Assisted("two") String target);
    SetId createSetId();
    MongoSave createMongoSave(@Assisted DBCollection collection);
}
