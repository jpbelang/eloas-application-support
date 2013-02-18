package ca.eloas.restsupport.requests;

import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.ToModelOperation;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author JP
 */
public class SymmetricalPostRequest<MODEL, MESSAGE> {


    private final Class<MESSAGE> outputMessageClass;
    private final MODEL object;
    private Iterable<? extends ToModelOperation<? super MODEL, ? super MESSAGE>> toModelOperations = new ArrayList<ToModelOperation<? super MODEL, ? super MESSAGE>>();
    private Iterable<? extends ToMessageOperation<? super MODEL, ? super MESSAGE>> toMessageOprations = new ArrayList<ToMessageOperation<? super MODEL, ? super MESSAGE>>();

    private ObjectFactory messageFactory;
    private final MESSAGE inputMessage;

    public SymmetricalPostRequest(MODEL object, MESSAGE inputMessage, Class<MESSAGE> outputMessageClass) {

        this.object = object;
        this.inputMessage = inputMessage;
        this.outputMessageClass = outputMessageClass;
    }

    /* Not sure */
    public SymmetricalPostRequest<MODEL, MESSAGE> runAllOnEntity(Iterable<ToModelOperation< ? super MODEL, ? super MESSAGE>> operations) {

        this.toModelOperations =  operations;
        return this;
    }

    public SymmetricalPostRequest<MODEL, MESSAGE> runAllOnEntity(ToModelOperation< ? super MODEL, ? super MESSAGE>... operations) {

        runAllOnEntity(Arrays.asList(operations));
        return this;
    }

    /* Not sure */
    public SymmetricalPostRequest<MODEL, MESSAGE> runAllOnOutputMessage(Iterable<ToMessageOperation< ? super MODEL, ? super MESSAGE>> operations) {

        this.toMessageOprations =  operations;
        return this;
    }

    public SymmetricalPostRequest<MODEL, MESSAGE> runAllOnOutputMessage(ToMessageOperation< ? super MODEL, ? super MESSAGE>... operations) {

        runAllOnOutputMessage(Arrays.asList(operations));
        return this;
    }


    public SymmetricalPostRequest<MODEL, MESSAGE> createMessageWith(ObjectFactory factory) {

        this.messageFactory = factory;
        return this;
    }

    public MESSAGE now() {

        try {
            for (ToModelOperation<? super MODEL, ? super MESSAGE> operation : toModelOperations) {

                operation.run(inputMessage, object);
            }

            MESSAGE message = messageFactory.create(outputMessageClass);

            for (ToMessageOperation<? super MODEL, ? super MESSAGE> operation : toMessageOprations) {

                operation.run(object, message);
            }

            return message;
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


}
