package za.co.trf.recurly.js;

/**
 * Exception thrown on RecurlyJS failure. It wraps the actual cause of the failure and could be subclassed to indicate
 * the type of failure. As this class is a runtime exception, there is no need for user code to catch it or subclasses
 * if any error is to be considered fatal.
 */
public class RecurlyJSException extends RuntimeException {

    public RecurlyJSException(Exception cause) {
        this.initCause(cause);
    }
    
}
