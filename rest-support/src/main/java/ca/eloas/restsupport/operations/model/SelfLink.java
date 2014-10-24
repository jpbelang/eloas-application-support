package ca.eloas.restsupport.operations.model;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.messages.Link;
import ca.eloas.restsupport.messages.LinkedMessage;
import ca.eloas.restsupport.operations.MethodSensitiveToMessageOperation;
import com.google.inject.assistedinject.AssistedInject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.ws.rs.core.UriBuilder;

/**
 * @author JP
 */
public class SelfLink<MODEL extends DBObject> extends  MethodSensitiveToMessageOperation<MODEL, LinkedMessage> {

    Provider<UriBuilder> uriBuilder;
    ObjectFactory objectFactory;

    @AssistedInject
    public SelfLink(ObjectFactory of, @Named("request") Provider<UriBuilder> info, Provider<Method> m) {
        super(m);
        this.objectFactory = of;
        this.uriBuilder = info;
    }


    @Override
    protected void runForPutDeleteAndGet(MODEL object,  LinkedMessage linkedMessage) throws Exception {
        Link link = objectFactory.create(Link.class);
        link.setName("self");
        link.setURL(uriBuilder.get().replaceQuery(null).build().toString());
        linkedMessage.getLinks().add(link);
    }

    @Override
    protected void runFofPostAndList(MODEL object, LinkedMessage linkedMessage) throws Exception {

        if ( object == null ) {

            runForPutDeleteAndGet(object, linkedMessage);
            return;
        }

        Link link = objectFactory.create(Link.class);
        link.setName("self");
        link.setURL(uriBuilder.get().replaceQuery(null).path(object.stableId().asString()).build().toString());
        linkedMessage.getLinks().add(link);
    }

    public static SelfLink boogieWoogie() {

        return null;
    }

}
