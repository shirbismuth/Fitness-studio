package gym.Exception;

import gym.management.Secretary;

public class InvalidAgeException extends Exception {
    public InvalidAgeException() {
        super("Error: Client must be at least 18 years old to register");
        Secretary.docHistory(getMessage());
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}