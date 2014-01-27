package ca.eloas.restsupport.operations.model;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author JP
 */
public class ModelOperationsModule extends AbstractModule {

    @Override
    protected void configure() {

        install(new FactoryModuleBuilder()
                .build(ModelBasedLinkOperationFactory.class));
    }
}
