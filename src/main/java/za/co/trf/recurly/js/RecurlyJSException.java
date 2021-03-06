package za.co.trf.recurly.js;

import za.co.trf.recurly.RecurlyException;

/**
 * Exception thrown on RecurlyJS failure. It wraps the actual cause of the failure and could be subclassed to indicate
 * the type of failure. As this class is a runtime exception, there is no need for user code to catch it or subclasses
 * if any error is to be considered fatal.
 */
public class RecurlyJSException extends RecurlyException {

    /**
     * Constructor
     *
     * @param msg Error message
     */
    public RecurlyJSException(String msg) {
        super(msg);
    }

    /**
     * Constructor
     *
     * @param msg Error message
     * @param cause Exception
     */
    public RecurlyJSException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
