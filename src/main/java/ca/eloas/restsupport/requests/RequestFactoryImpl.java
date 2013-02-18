package ca.eloas.restsupport.requests;

import ca.eloas.restsupport.messages.MessageList;

/**
 * @author JP
 */
public class RequestFactoryImpl  implements RequestFactory {
    public <MODEL, MESSAGE> GetRequest<MODEL, MESSAGE> createGetRequest(MODEL model, Class<MESSAGE> message) {

        return new GetRequest<MODEL, MESSAGE>(model, message);
    }

    public <MODEL, INMESSAGE, OUTMESSAGE> AsymmetricalPostRequest<MODEL, INMESSAGE, OUTMESSAGE> createAsymmetricalPostRequest(MODEL model, INMESSAGE message, Class<OUTMESSAGE> messageClass) {
        return new AsymmetricalPostRequest<MODEL, INMESSAGE, OUTMESSAGE>(model, message, messageClass);
    }

    public <MODEL, MESSAGE> SymmetricalPostRequest<MODEL, MESSAGE> createPostRequest(MODEL model, MESSAGE message, Class<MESSAGE> messageClass) {

        return new SymmetricalPostRequest<MODEL, MESSAGE>(model, message, messageClass);
    }

    public <MODEL, LISTMESSAGE extends MessageList<MESSAGE>, MESSAGE> ListRequest<MODEL, LISTMESSAGE, MESSAGE> createListRequest(Iterable<MODEL> objectList, Class<LISTMESSAGE> messageListClass, Class<MESSAGE> messageClass) {
        return new ListRequest(objectList, messageListClass, messageClass);
    }
}
