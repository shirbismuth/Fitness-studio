package gym.Exception;

import gym.management.Secretary;

public class ClientNotRegisteredException extends Exception {
    public ClientNotRegisteredException() {
        super("Error: Registration is required before attempting to unregister");
//        Secretary.docHistory(getMessage());
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}