package ca.eloas.restsupport;

import ca.eloas.restsupport.operations.AddLink;
import ca.eloas.restsupport.operations.AddProvidedLink;
import ca.eloas.restsupport.operations.RestSupportOperationFactory;
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

        install(new FactoryModuleBuilder()
                .build(RestSupportOperationFactory.class));
        requestStaticInjection(AddLink.class);
        requestStaticInjection(AddProvidedLink.class);

    }
}
