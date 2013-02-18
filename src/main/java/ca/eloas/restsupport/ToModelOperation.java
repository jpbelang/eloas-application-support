package ca.eloas.restsupport;

/**
 * @author JP
 */
public interface ToModelOperation<MODEL, MESSAGE> {

    public void run(MESSAGE message, MODEL object) throws Exception;
}
