package gym.Exception;

/**
 * The InvalidAgeException class is a custom exception that is thrown
 * when a client attempts to register but does not meet the minimum age requirement.
 */
public class InvalidAgeException extends Exception {
    /**
     * Constructs a new InvalidAgeException with a default error message.
     */
    public InvalidAgeException() {
        super("Error: Client must be at least 18 years old to register");
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