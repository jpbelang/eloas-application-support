package ca.eloas.restsupport.operations.model;

import ca.eloas.restsupport.operations.model.AddLink;
import ca.eloas.restsupport.operations.model.AddProvidedLink;
import ca.eloas.restsupport.requests.RequestFactory;
import ca.eloas.restsupport.requests.RequestFactoryImpl;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author JP
 */
public class Module extends AbstractModule {

    @Override
    protected void configure() {

        bind(RequestFactory.class).to(RequestFactoryImpl.class);
    }
}
