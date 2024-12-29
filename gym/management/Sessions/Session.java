package gym.management.Sessions;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

/**
 * The Session class represents an abstract session in the gym management system.
 * It contains details about the session such as time, forum type, instructor, and registered clients.
 */
public abstract class Session {
    private String time;
    private ForumType forumType;
    private Instructor instructor;
    private Set<Client> registered;

    /**
     * Constructs a new Session with the specified time, forum type, and instructor.
     *
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param instructor the instructor of the session
     */
    protected Session(String time, ForumType forumType, Instructor instructor) {
        this.time = time;
        this.forumType = forumType;
        this.instructor = instructor;
        this.registered = new HashSet<>();
    }

    /**
     * Returns the time of the session.
     *
     * @return the time of the session
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the forum type of the session.
     *
     * @return the forum type of the session
     */
    public ForumType getForumType() {
        return forumType;
    }

    /**
     * Returns the instructor of the session.
     *
     * @return the instructor of the session
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Returns the set of clients registered for the session.
     *
     * @return the set of clients registered for the session
     */
    public Set<Client> getRegistered() {
        return new HashSet<>(registered);
    }

    /**
     * Registers a client to the session.
     *
     * @param c the client to be registered
     */
    void registerToLesson(Client c) {
        registered.add(c);
    }

    /**
     * Returns the session type of this session.
     *
     * @return the session type of this session
     */
    public abstract SessionType getSessionType();

    /**
     * Returns the price of this session.
     *
     * @return the price of this session
     */
    public abstract int getPrice();

    /**
     * Returns the capacity of this session.
     *
     * @return the capacity of this session
     */
    public abstract int getCapacity();

    /**
     * Returns a string representation of this session.
     *
     * @return a string representation of this session
     */
    @Override
    public String toString() {
        return " | Date: " + getTime() + " | Forum: " + getForumType() +
                " | Instructor: " + getInstructor().getName();
    }
}