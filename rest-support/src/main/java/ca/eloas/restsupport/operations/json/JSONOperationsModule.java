package ca.eloas.restsupport.operations.json;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author JP
 */
public class JSONOperationsModule extends AbstractModule {

    @Override
    protected void configure() {

        install(new FactoryModuleBuilder()
                .build(JSONOperationFactory.class));

    }
}
