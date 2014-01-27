package ca.eloas.restsupport.operations.model;

import ca.eloas.restsupport.operations.model.SelfLink;
import com.google.inject.assistedinject.Assisted;

/**
 * @author JP
 */
public interface ModelBasedLinkOperationFactory {

    SelfLink selfLink();
    ChildLink childLink(@Assisted("one") String name, @Assisted("two") String link);
}
