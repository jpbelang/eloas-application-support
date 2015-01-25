package ca.eloas.modelsupport;

/**
 * @author JP
 */
public interface Translator<DOMAIN, EXTERNAL> {

    <T extends DOMAIN> T fromExternalToDomain(Class<T> type, EXTERNAL two);
    <T extends EXTERNAL> T fromDomainToExternal(Class<T> type, DOMAIN one);

}
