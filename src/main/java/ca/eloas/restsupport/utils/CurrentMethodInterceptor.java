package ca.eloas.restsupport.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author JP
 */
public class CurrentMethodInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {

        try {

            CurrentMethod.set(findMethod(invocation.getMethod()));
            return invocation.proceed();
        } finally {

            CurrentMethod.clear();
        }
    }

    private Method findMethod(Method method) {

        return method;
    }
}
