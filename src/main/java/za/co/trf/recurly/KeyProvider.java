package za.co.trf.recurly;

/**
 * A KeyProvider is a container for a Recurly private key.
 * @see SimpleKeyProvider
 */
public interface KeyProvider {

    /**
     * @return the Recurly private key offered by this provider
     */
    public String getPrivateKey();

}
