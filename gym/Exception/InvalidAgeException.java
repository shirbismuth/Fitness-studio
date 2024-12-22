package gym.Exception;

public class InvalidAgeException extends Exception {
    public InvalidAgeException() {
        super("Error: gym.customers.Client must be at least 18 years old to register");
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}