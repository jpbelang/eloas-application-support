package ca.eloas.restsupport.operations.model;

import ca.eloas.restsupport.operations.model.SelfLink;
import com.google.inject.assistedinject.Assisted;

/**
 * @author JP
 */
public interface ModelOprationFactory {

    SelfLink createSelfLink();
    ChildLink createChildLink(@Assisted("one") String name, @Assisted("two")String link);
    CopyToMessage createCopyToMessage();
}
