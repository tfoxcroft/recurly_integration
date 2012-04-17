package za.co.trf.recurly;

public class SimpleKeyProvider implements KeyProvider {

    private String privateKey;

    public SimpleKeyProvider(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

}
