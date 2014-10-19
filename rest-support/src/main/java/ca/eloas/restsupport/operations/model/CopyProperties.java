package ca.eloas.restsupport.operations.model;

import ca.eloas.restsupport.ToModelOperation;

/**
 * @author JP
 */
public class CopyProperties<MODEL, MESSAGE> implements ToModelOperation<MODEL, MESSAGE> {

    public void run(MESSAGE integer, MODEL object) {

    }

    public static <MODEL, MESSAGE> CopyProperties<MODEL, MESSAGE> copy(MODEL model, MESSAGE message) {

        return new CopyProperties<MODEL, MESSAGE>();
    }
}
