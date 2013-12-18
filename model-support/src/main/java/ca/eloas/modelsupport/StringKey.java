package ca.eloas.modelsupport;

/**
 * @author JP
 */
public class StringKey implements KeyType<String> {

    private String id;

    public StringKey(String id) {
        this.id = id;
    }

    @Override
    public String asString() {

        return id;
    }


    @Override
    public String getKey() {
        return id;
    }
}
