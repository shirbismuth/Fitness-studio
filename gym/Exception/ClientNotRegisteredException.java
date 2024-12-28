package gym.Exception;

/**
 * The ClientNotRegisteredException class is a custom exception that is thrown
 * when an attempt is made to unregister a client who is not registered.
 */
public class ClientNotRegisteredException extends Exception {
    /**
     * Constructs a new ClientNotRegisteredException with a default error message.
     */
    public ClientNotRegisteredException() {
        super("Error: Registration is required before attempting to unregister");
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