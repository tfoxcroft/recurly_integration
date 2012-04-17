package za.co.trf.recurly.js;

public class RecurlyJSException extends RuntimeException {

    public RecurlyJSException(Exception cause) {
        this.initCause(cause);
    }
    
}
