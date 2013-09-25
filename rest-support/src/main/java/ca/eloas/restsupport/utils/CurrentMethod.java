package ca.eloas.restsupport.utils;

import java.lang.reflect.Method;

/**
 * @author JP
 */
public class CurrentMethod {

    private static ThreadLocal<Method> currentMethod = new ThreadLocal<Method>();

    public static Method get() {

        return currentMethod.get();
    }

    public static void set(Method m) {

        currentMethod.set(m);
    }

    public static void clear() {

        currentMethod.set(null);
    }
}
