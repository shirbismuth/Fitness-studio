package gym.Exception;

/**
 * The InstructorNotQualifiedException class is a custom exception that is thrown
 * when an instructor is not qualified to conduct a specific session type.
 */
public class InstructorNotQualifiedException extends Exception {
    /**
     * Constructs a new InstructorNotQualifiedException with a default error message.
     */
    public InstructorNotQualifiedException() {
        super("Error: Instructor is not qualified to conduct this session type.");
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