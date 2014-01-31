package ca.eloas.restsupport.operations;

import com.google.inject.assistedinject.Assisted;

/**
 * @author JP
 */
public interface RestSupportOperationFactory {
    AddLink addLink(@Assisted("one") String name, @Assisted("two") String target);
    AddProvidedLink addProvidedLink(@Assisted("one") String name, @Assisted("two") String target);
}
