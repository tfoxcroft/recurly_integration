package za.co.trf.recurly;

/**
 * Base class for exceptions thrown by this library.
 */
public class RecurlyException extends RuntimeException {

    /**
     * Constructor
     *
     * @param msg Error message
     */
    public RecurlyException(String msg) {
        super(msg);
    }

    /**
     * Constructor
     *
     * @param msg Error message
     * @param cause Exception
     */
    public RecurlyException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
