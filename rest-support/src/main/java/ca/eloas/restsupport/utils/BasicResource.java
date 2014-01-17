package ca.eloas.restsupport.utils;

import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.messages.MessageList;
import ca.eloas.restsupport.requests.AsymmetricalPostRequest;
import ca.eloas.restsupport.requests.GetRequest;
import ca.eloas.restsupport.requests.ListRequest;
import ca.eloas.restsupport.requests.RequestFactory;
import ca.eloas.restsupport.requests.SymmetricalPostRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author JP
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class BasicResource {

    @Inject
    private RequestFactory factory;

    @Inject
    private ObjectFactory objectFactory;

    public <MODEL,MESSAGE> GetRequest<MODEL,MESSAGE> get(MODEL model, Class<MESSAGE> message) {

        return factory.createGetRequest(model, message)
                .createMessageWith(objectFactory);
    }

    public <MODEL,MESSAGE> SymmetricalPostRequest<MODEL,MESSAGE> post(MODEL model, MESSAGE message, Class<MESSAGE> messageClass) {

        return factory.createPostRequest(model, message, messageClass)
                .createMessageWith(objectFactory);
    }

    public <MODEL,INPUT, OUTPUT> AsymmetricalPostRequest<MODEL,INPUT, OUTPUT> postAsymmetrical(MODEL model, INPUT message, Class<OUTPUT> messageClass) {

        return factory.createAsymmetricalPostRequest(model, message, messageClass)
                .createMessageWith(objectFactory);
    }

    public <MODEL, LISTMESSAGE extends MessageList<MESSAGE>, MESSAGE> ListRequest<MODEL,LISTMESSAGE, MESSAGE> list(Iterable<MODEL> objectList, Class<LISTMESSAGE> messageListClass, Class<MESSAGE> messageClass) {

        return factory.createListRequest(objectList, messageListClass, messageClass)
                .createMessageWith(objectFactory)
                .createListWith(objectFactory);
    }

    protected<M> M createMessage(Class<M> cls) {

        return objectFactory.create(cls);
    }

    protected ObjectFactory getObjectFactory() {
        return objectFactory;
    }
}
