package gym.Exception;

import gym.management.Secretary;

public class DuplicateClientException extends Exception{
    public DuplicateClientException() {
        super("Error: The client is already registered");
        Secretary.docHistory(getMessage());
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
