package ca.eloas.restsupport.operations;

import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.messages.Link;
import ca.eloas.restsupport.messages.LinkedMessage;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author JP
 */
public class AddLink implements ToMessageOperation<Object, LinkedMessage> {

    @Inject
    private static RestSupportOperationFactory factory;

    UriBuilder uriBuilder;
    ObjectFactory objectFactory;

    private final String target;
    private final String name;


    @AssistedInject
    public AddLink(ObjectFactory of, @Named("base")UriBuilder info, @Assisted("one") String name, @Assisted("two") String target) {

        this.objectFactory = of;
        this.uriBuilder = info;
        this.name = name;
        this.target = target;
    }

    public void run(Object object, LinkedMessage linkedMessage) {

        Link link = objectFactory.create(Link.class);
        link.setName(name);
        link.setURL(uriBuilder.path(target).build().toString());
        linkedMessage.getLinks().add(link);
    }

    public static AddLink addLink(String name, String target) {

        return factory.createAddLink(name, target);
    }
}
