package ca.eloas.modelsupport;

/**
 * @author JP
 */
public class LongKey implements KeyType<Long> {

    private long id;

    public LongKey(long id) {
        this.id = id;
    }

    @Override
    public String asString() {

        return Long.toString(id);
    }


    @Override
    public Long getKey() {
        return id;
    }
}
