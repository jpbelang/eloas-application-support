package ca.eloas.security;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * @author JP
 */
public class SecurityInterceptor  implements MethodInterceptor {


    @Inject
    private Provider<AuthenticationAuthorizationService> service;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        // Check permissions on the class
        boolean shouldContinue = checkClassPermissions(invocation);

        // check permissions on the methods
        if (invocation.getMethod().getAnnotation(PermitAll.class) != null) {

            return invocation.proceed();
        }

        if (invocation.getMethod().getAnnotation(DenyAll.class) != null) {

            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }

        RolesAllowed allowed = invocation.getMethod().getAnnotation(RolesAllowed.class);
        if (allowed == null) {

            if (shouldContinue) {
                return invocation.proceed();
            } else {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        }

        List<String> allowedRoles = Arrays.asList(allowed.value());
        if ( allowedRoles.contains(StandardRoles.OWNER) ) {

            if ( invocation.getThis() instanceof OwnedResource ) {

                OwnedResource r = (OwnedResource) invocation.getThis();
                r.isOwner(service.get().getAuthInformation());
            }
        }

        if (service.get().isUserInRoles(allowed.value())) {

            return invocation.proceed();
        }

        throw new WebApplicationException(Response.Status.FORBIDDEN);
    }

    private boolean checkClassPermissions(MethodInvocation invocation) throws Throwable {
        if (getClassAnnotations(invocation.getThis().getClass(), PermitAll.class) != null) {

            return true;
        }

        RolesAllowed rolesAllowed = getClassAnnotations(invocation.getThis().getClass(), RolesAllowed.class);

        if (rolesAllowed != null && service.get().isUserInRoles(rolesAllowed.value())) {

            return true;
        }

        return false;
    }

    private <T> T getClassAnnotations(Class clazz, Class<T> annotationClass) {

        if (clazz == Object.class) {

            return null;
        }

        T annotation = (T) clazz.getAnnotation(annotationClass);
        if (annotation != null) {

            return annotation;
        } else {

            return getClassAnnotations(clazz.getSuperclass(), annotationClass);
        }
    }

}
