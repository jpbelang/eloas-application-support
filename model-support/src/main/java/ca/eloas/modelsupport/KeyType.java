package ca.eloas.modelsupport;

/**
 * @author JP
 */
public interface KeyType<CONTAINED> {

    String asString();
    CONTAINED getKey();
}
