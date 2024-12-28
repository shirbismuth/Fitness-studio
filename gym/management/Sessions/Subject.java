package gym.management.Sessions;

import gym.customers.Observer;

/**
 * The Subject interface represents a subject in the observer pattern.
 * It provides methods to register, unregister, and notify observers.
 */
public interface Subject {
    /**
     * Registers an observer to the subject.
     *
     * @param observer the observer to be registered
     */
    void registerObserver(Observer observer);

    /**
     * Unregisters an observer from the subject.
     *
     * @param observer the observer to be unregistered
     */
    void unregisterObserver(Observer observer);

    /**
     * Notifies all registered observers with a message.
     *
     * @param message the message to be sent to the observers
     */
    void notify(String message);

    /**
     * Notifies all registered observers with a session and a message.
     *
     * @param s the session to be sent to the observers
     * @param message the message to be sent to the observers
     */
    void notify(Session s, String message);

    /**
     * Notifies all registered observers with a date and a message.
     *
     * @param date the date to be sent to the observers
     * @param message the message to be sent to the observers
     */
    void notify(String date, String message);
}