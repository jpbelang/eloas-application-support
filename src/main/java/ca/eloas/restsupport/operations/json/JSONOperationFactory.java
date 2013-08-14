package ca.eloas.restsupport.operations.json;


import com.google.inject.assistedinject.Assisted;

/**
 * @author JP
 */
public interface JSONOperationFactory {

    SelfLink createSelfLink();
    CopyToMessage createCopyToMessage();
    CopyToModel createCopyToModel();
    AddLink createAddLink(@Assisted("one") String name, @Assisted("two") String target);
}
