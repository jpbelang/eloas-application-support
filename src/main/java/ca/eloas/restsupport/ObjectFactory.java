package ca.eloas.restsupport;

/**
 * @author JP
 */
public interface ObjectFactory {

    <T> T create(Class<T> cls);
}
