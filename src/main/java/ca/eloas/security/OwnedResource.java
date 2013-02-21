package ca.eloas.security;

/**
 * @author JP
 */
public interface OwnedResource {

    boolean isOwner(AuthInformation info);
}
