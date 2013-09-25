package ca.eloas.restsupport.requests;

import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.messages.MessageList;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JP
 */
public class ListRequest<MODEL, LISTMESSAGE extends MessageList<MESSAGE>, MESSAGE> {


    private final Class<MESSAGE> messageClass;
    private final Class<LISTMESSAGE> messageListClass;
    private final Iterable<MODEL> objectList;
    private Iterable<? extends ToMessageOperation<? super MODEL, ? super MESSAGE>> itemOperations = new ArrayList<ToMessageOperation<? super MODEL, ? super MESSAGE>>();
    private Iterable<? extends ToMessageOperation<? super MODEL, ? super LISTMESSAGE>> listOperations = new ArrayList<ToMessageOperation<? super MODEL, ? super LISTMESSAGE>>();

    private ObjectFactory messageFactory;
    private ObjectFactory listMessageFactory;

    public ListRequest(Iterable<MODEL> objectList, Class<LISTMESSAGE> messageListClass, Class<MESSAGE> messageClass) {

        this.objectList = objectList;
        this.messageListClass = messageListClass;
        this.messageClass = messageClass;
    }

    /* Not sure */
    public ListRequest<MODEL, LISTMESSAGE, MESSAGE> runOnItems(Iterable<ToMessageOperation< ? super MODEL, ? super MESSAGE>> operations) {

        this.itemOperations =  operations;
        return this;
    }

    public ListRequest<MODEL, LISTMESSAGE, MESSAGE> runOnItems(ToMessageOperation< ? super MODEL, ? super MESSAGE>... operations) {

        runOnItems(Arrays.asList(operations));
        return this;
    }

    /* Not sure */
    public ListRequest<MODEL, LISTMESSAGE, MESSAGE> runOnList(Iterable<ToMessageOperation< ? super MODEL, ? super LISTMESSAGE>> operations) {

        this.listOperations =  operations;
        return this;
    }

    public ListRequest<MODEL, LISTMESSAGE, MESSAGE> runOnList(ToMessageOperation< ? super MODEL, ? super LISTMESSAGE>... operations) {

        runOnList(Arrays.asList(operations));
        return this;
    }

    public ListRequest<MODEL, LISTMESSAGE, MESSAGE> createMessageWith(ObjectFactory factory) {

        this.messageFactory = factory;
        return this;
    }

    public ListRequest<MODEL, LISTMESSAGE, MESSAGE> createListWith(ObjectFactory factory) {

        this.listMessageFactory = factory;
        return this;
    }

    public LISTMESSAGE now() {

        try {
            LISTMESSAGE list = listMessageFactory.create(messageListClass);
            List<MESSAGE> messageList = new ArrayList<MESSAGE>();

            for (MODEL model : objectList) {

                MESSAGE message = messageFactory.create(messageClass);
                messageList.add(message);
                for (ToMessageOperation<? super MODEL, ? super MESSAGE> operation : itemOperations) {

                    operation.run(model, message);
                }
            }
            list.setMessages(messageList);

            for (ToMessageOperation<? super MODEL, ? super LISTMESSAGE> listOperation : listOperations) {

                listOperation.run(null, list);
            }

            return list;
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


}
