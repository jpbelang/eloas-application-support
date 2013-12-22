package ca.eloas.restsupport.requests;

import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.ToModelOperation;
import ca.eloas.restsupport.operations.json.AddLink;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Arrays;

/**
 * @author JP
 */
public class GetRequest<MODEL, MESSAGE> {


    private final Class<MESSAGE> messageClass;
    private final MODEL object;
    private Iterable<? extends ToMessageOperation<? super MODEL, ? super MESSAGE>> operations;
    private ObjectFactory messageFactory;
    private MESSAGE message;

    @AssistedInject
    public GetRequest(@Assisted MODEL object, @Assisted Class<MESSAGE> message) {

        this.object = object;
        this.messageClass = message;
    }

    /* Not sure */
    public GetRequest<MODEL, MESSAGE> runAll(Iterable<ToMessageOperation< ? super MODEL, ? super MESSAGE>> operations) {

        this.operations =  operations;
        return this;
    }

    public GetRequest<MODEL, MESSAGE> runAll(ToMessageOperation< ? super MODEL, ? super MESSAGE>... operations) {

        runAll(Arrays.asList(operations));
        return this;
    }

    public GetRequest<MODEL, MESSAGE> createMessageWith(ObjectFactory factory) {

        this.messageFactory = factory;
        return this;
    }

    public MESSAGE now() {

        if ( object == null ) {

            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        try {
            MESSAGE message = messageFactory.create(messageClass);
            for (ToMessageOperation<? super MODEL, ? super MESSAGE> operation : operations) {

                operation.run(object, message);
            }

            return message;
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


//    public GetRequest<MODEL, MESSAGE>  runAll(MODEL addLink, MODEL addLink1, MODEL addLink2) {
//    }
}
