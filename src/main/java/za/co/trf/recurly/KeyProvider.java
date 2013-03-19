package za.co.trf.recurly;

/**
 * A KeyProvider is a container for a Recurly key (private or API).
 *
 * @see SimpleKeyProvider
 */
public interface KeyProvider {

    /**
     * @return the Recurly private key offered by this provider
     */
    public String getKey();

}
