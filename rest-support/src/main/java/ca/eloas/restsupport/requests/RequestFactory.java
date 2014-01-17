package ca.eloas.restsupport.requests;

import ca.eloas.restsupport.messages.MessageList;

/**
 * @author JP
 */
public interface RequestFactory {

    <MODEL,MESSAGE> GetRequest<MODEL,MESSAGE> createGetRequest(MODEL model, Class<MESSAGE> message);
    <O extends Object,MESSAGE> GetRequest<Object,MESSAGE> createGetRequest(Class<MESSAGE> message);

    <MODEL,MESSAGE> SymmetricalPostRequest<MODEL,MESSAGE> createPostRequest(MODEL model, MESSAGE message, Class<MESSAGE> messageClass);
    <MODEL,INPUT, OUTPUT> AsymmetricalPostRequest<MODEL,INPUT, OUTPUT> createAsymmetricalPostRequest(MODEL model, INPUT message, Class<OUTPUT> messageClass);
    <MODEL, LISTMESSAGE extends MessageList<MESSAGE>, MESSAGE> ListRequest<MODEL, LISTMESSAGE, MESSAGE> createListRequest(Iterable<MODEL> objectList, Class<LISTMESSAGE> messageListClass, Class<MESSAGE> messageClass);

}
