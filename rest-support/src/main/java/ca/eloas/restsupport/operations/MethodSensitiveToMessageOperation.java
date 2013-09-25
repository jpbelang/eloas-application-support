package ca.eloas.restsupport.operations;

import ca.eloas.restsupport.ToMessageOperation;
import ca.eloas.restsupport.utils.CurrentMethod;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

public class MethodSensitiveToMessageOperation<O, M> implements ToMessageOperation<O, M> {

    @Inject
    private Provider<HttpServletRequest> request;


    public void run(O one, M two) throws Exception {

        String method = request.get().getMethod();
        if ("GET".equals(method)) {
            if (CurrentMethod.get().getAnnotation(LIST.class) == null )
                runForGet(one, two);
            else
                runForList(one, two);

        } else if ("POST".equals(method)) {
            runForPost(one, two);
        } else if ("PUT".equals(method)) {
            runForPut(one, two);
        } else if ("DELETE".equals(method)) {
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
