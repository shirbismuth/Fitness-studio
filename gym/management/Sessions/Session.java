package gym.management.Sessions;

import gym.customers.Client;

import java.util.Set;

/**
 * The Session interface represents a session in the gym management system.
 * It provides methods to get details about the session such as type, time, forum type,
 * instructor, price, capacity, and registered clients. It also allows registering a client to the session.
 */
public interface Session {
    /**
     * Returns the session type.
     *
     * @return the session type
     */
    SessionType getSessionType();

    /**
     * Returns the time of the session.
     *
     * @return the time of the session
     */
    String getTime();

    /**
     * Returns the forum type of the session.
     *
     * @return the forum type of the session
     */
    ForumType getForumType();

    /**
     * Returns the instructor conducting the session.
     *
     * @return the instructor conducting the session
     */
    Instructor getInstructor();

    /**
     * Returns the price of the session.
     *
     * @return the price of the session
     */
    int getPrice();

    /**
     * Returns the capacity of the session.
     *
     * @return the capacity of the session
     */
    int getCapacity();

    /**
     * Returns the set of clients registered for the session.
     *
     * @return the set of clients registered for the session
     */
    Set<Client> getRegistered();

    /**
     * Registers a client to the session.
     *
     * @param c the client to be registered
     */
    void registerToLesson(Client c);

    String toString();
}