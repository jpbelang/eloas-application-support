package ca.eloas.restsupport.operations.model;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.messages.Link;
import ca.eloas.restsupport.messages.LinkedMessage;
import ca.eloas.restsupport.operations.MethodSensitiveToMessageOperation;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.UriInfo;

/**
 * @author JP
 */
public class ChildLink<MODEL extends DBObject> extends  MethodSensitiveToMessageOperation<MODEL, LinkedMessage> {

    @Inject
    private static ModelOprationFactory factory;

    Provider<UriInfo> builderProvider;
    ObjectFactory objectFactory;
    private final String link;
    private final String name;

    @AssistedInject
    public ChildLink(ObjectFactory of, Provider<UriInfo> info, @Assisted("one") String name, @Assisted("two")String link) {

        this.objectFactory = of;
        this.builderProvider = info;
        this.name = name;
        this.link = link;
    }


    @Override
    protected void runForPutDeleteAndGet(MODEL object,  LinkedMessage linkedMessage) throws Exception {
        Link link = objectFactory.create(Link.class);
        link.setName(name);
        link.setURL(builderProvider.get().getRequestUriBuilder().replaceQuery(null).path(this.link).build().toString());
        linkedMessage.getLinks().add(link);
    }

    @Override
    protected void runFofPostAndList(MODEL object, LinkedMessage linkedMessage) throws Exception {

        if ( object == null ) {

            runForPutDeleteAndGet(object, linkedMessage);
            return;
        }

        Link link = objectFactory.create(Link.class);
        link.setName(name);
        link.setURL(builderProvider.get().getRequestUriBuilder().replaceQuery(null).path(object.getObjectId().asString() + this.link).build().toString());
        linkedMessage.getLinks().add(link);
    }

    public static ChildLink childLink(String name, String link) {

        return factory.createChildLink(name, link);
    }
}
