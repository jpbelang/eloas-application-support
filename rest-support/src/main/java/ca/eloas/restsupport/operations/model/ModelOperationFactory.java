package ca.eloas.restsupport.operations.model;

import ca.eloas.restsupport.operations.model.SelfLink;
import com.google.inject.assistedinject.Assisted;

/**
 * @author JP
 */
public interface ModelOperationFactory {

    SelfLink selfLink();
    ChildLink childLink(@Assisted("one") String name, @Assisted("two") String link);
    CopyToMessage createCopyToMessage();
}
