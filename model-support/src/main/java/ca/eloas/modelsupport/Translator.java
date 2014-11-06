package ca.eloas.modelsupport;

/**
 * @author JP
 */
public interface Translator<ONE, TWO> {

    <T extends ONE> T fromTwoToOne(Class<T> type, TWO two);
    <T extends TWO> T fromOneToTwo(Class<T> type, ONE one);

}
