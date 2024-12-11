package gym.Exception;

public class DuplicateClientException extends Exception{
    public DuplicateClientException() {
        super("Error: The client is already registered");
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
