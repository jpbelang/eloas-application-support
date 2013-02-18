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
public class AsymmetricalPostRequest<MODEL, INPUTMESSAGE, OUTPUTMESSAGE> {


    private final Class<OUTPUTMESSAGE> outputMessageClass;
    private final MODEL object;
    private Iterable<? extends ToModelOperation<? super MODEL, ? super INPUTMESSAGE>> toModelOperations = new ArrayList<ToModelOperation<? super MODEL, ? super INPUTMESSAGE>>();
    private Iterable<? extends ToMessageOperation<? super MODEL, ? super OUTPUTMESSAGE>> toMessageOprations = new ArrayList<ToMessageOperation<? super MODEL, ? super OUTPUTMESSAGE>>();

    private ObjectFactory messageFactory;
    private final INPUTMESSAGE inputMessage;

    public AsymmetricalPostRequest(MODEL object, INPUTMESSAGE inputMessage, Class<OUTPUTMESSAGE> outputMessageClass) {

        this.object = object;
        this.inputMessage = inputMessage;
        this.outputMessageClass = outputMessageClass;
    }

    /* Not sure */
    public AsymmetricalPostRequest<MODEL, INPUTMESSAGE, OUTPUTMESSAGE> runAllOnEntity(Iterable<ToModelOperation< ? super MODEL, ? super INPUTMESSAGE>> operations) {

        this.toModelOperations =  operations;
        return this;
    }

    public AsymmetricalPostRequest<MODEL, INPUTMESSAGE, OUTPUTMESSAGE> runAllOnEntity(ToModelOperation< ? super MODEL, ? super INPUTMESSAGE>... operations) {

        runAllOnEntity(Arrays.asList(operations));
        return this;
    }

    /* Not sure */
    public AsymmetricalPostRequest<MODEL, INPUTMESSAGE, OUTPUTMESSAGE> runAllOnOutputMessage(Iterable<ToMessageOperation< ? super MODEL, ? super OUTPUTMESSAGE>> operations) {

        this.toMessageOprations =  operations;
        return this;
    }

    public AsymmetricalPostRequest<MODEL, INPUTMESSAGE, OUTPUTMESSAGE> runAllOnOutputMessage(ToMessageOperation< ? super MODEL, ? super OUTPUTMESSAGE>... operations) {

        runAllOnOutputMessage(Arrays.asList(operations));
        return this;
    }


    public AsymmetricalPostRequest<MODEL, INPUTMESSAGE, OUTPUTMESSAGE> createMessageWith(ObjectFactory factory) {

        this.messageFactory = factory;
        return this;
    }

    public OUTPUTMESSAGE now() {


        try {
            OUTPUTMESSAGE message = null;
            for (ToModelOperation<? super MODEL, ? super INPUTMESSAGE> operation : toModelOperations) {

                operation.run(inputMessage, object);
            }

            message = messageFactory.create(outputMessageClass);

            for (ToMessageOperation<? super MODEL, ? super OUTPUTMESSAGE> operation : toMessageOprations) {

                operation.run(object, message);
            }
            return message;
        } catch (Exception e) {

            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }

    }


}
