package ca.eloas.restsupport.operations.model;

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
public class ModelOperationsModule extends AbstractModule {

    @Override
    protected void configure() {

        install(new FactoryModuleBuilder()
                .build(ModelOperationFactory.class));
        requestStaticInjection(SelfLink.class);
        requestStaticInjection(ChildLink.class);

    }
}
