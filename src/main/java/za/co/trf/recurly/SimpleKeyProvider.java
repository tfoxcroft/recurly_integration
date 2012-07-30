package za.co.trf.recurly;

/**
 * Simple implementation of the KeyProvider interface. Stores and provides the key provided as a constructor argument.
 */
public class SimpleKeyProvider implements KeyProvider {

    private String privateKey;

    public SimpleKeyProvider(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

}
