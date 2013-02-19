package ca.eloas.modelsupport.jpa;

import ca.eloas.modelsupport.KeyType;

import java.util.UUID;

/**
 * @author JP
 */
public class UUIDKey implements KeyType<UUID> {

    private UUID key;

    public UUIDKey(UUID key) {
        this.key = key;
    }

    @Override
    public String asString() {
        return key.toString();
    }

    @Override
    public UUID getKey() {
        return key;
    }
}
