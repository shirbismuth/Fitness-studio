package gym.customers;

/**
 * The Observer interface should be implemented by any class that wants to be notified of changes or updates.
 */
public interface Observer {
    /**
     * This method is called to update the observer with a new message.
     *
     * @param message the message to update the observer with
     */
    void update(String message);
}