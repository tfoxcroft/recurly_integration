package za.co.trf.recurly;

/**
 * Simple implementation of the KeyProvider interface. Stores and provides the key provided as a constructor argument.
 */
public class SimpleKeyProvider implements KeyProvider {

    /**
     * The key
     */
    private String key;

    public SimpleKeyProvider(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
