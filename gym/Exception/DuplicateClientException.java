package gym.Exception;

/**
 * The DuplicateClientException class is a custom exception that is thrown
 * when an attempt is made to register a client who is already registered.
 */
public class DuplicateClientException extends Exception {
    /**
     * Constructs a new DuplicateClientException with a default error message.
     */
    public DuplicateClientException() {
        super("Error: The client is already registered");
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this exception
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}