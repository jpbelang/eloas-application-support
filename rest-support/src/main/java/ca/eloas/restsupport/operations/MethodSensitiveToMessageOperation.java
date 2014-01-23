package ca.eloas.restsupport.operations;

import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.utils.CurrentMethod;

import javax.inject.Inject;
import javax.inject.Provider;

public class MethodSensitiveToMessageOperation<O, M> implements ToMessageOperation<O, M> {

    public static enum Method {
        GET, POST, PUT, DELETE
    }

    private Provider<Method> request;


    @Inject
    public MethodSensitiveToMessageOperation(Provider<Method> request) {
        this.request = request;
    }

    public void run(O one, M two) throws Exception {

        Method m = request.get();

        Method method = request.get();
        if (method == Method.GET) {
            if (CurrentMethod.get().getAnnotation(LIST.class) == null )
                runForGet(one, two);
            else
                runForList(one, two);

        } else if (method == Method.POST) {
            runForPost(one, two);
        } else if (method == Method.PUT) {
            runForPut(one, two);
        } else if (method == Method.DELETE) {
            runForDelete(one, two);
        } else {

            throw new IllegalArgumentException("Invalid method " + method);
        }

    }


    protected void runForDelete(O one, M two) throws Exception {

        runForPutDeleteAndGet(one, two);
    }

    protected void runForPut(O one, M two) throws Exception {

        runForPutDeleteAndGet(one, two);
    }

    protected void runForPost(O one, M two) throws Exception {

        runFofPostAndList(one, two);
    }

    protected void runForGet(O one, M two) throws Exception {

        runForPutDeleteAndGet(one, two);
    }

    protected void runForList(O one, M two) throws Exception {

        runFofPostAndList(one, two);
    }

    protected void runFofPostAndList(O one, M two) throws Exception {

    }

    protected void runForPutDeleteAndGet(O one, M two) throws Exception {

    }

}
