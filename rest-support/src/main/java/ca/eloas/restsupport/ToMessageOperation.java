package ca.eloas.restsupport;

/**
 * @author JP
 */
public interface ToMessageOperation<MODEL, MESSAGE> {

    public void run(MODEL object, MESSAGE message) throws Exception;
}
