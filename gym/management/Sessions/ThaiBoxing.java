package gym.management.Sessions;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

/**
 * The ThaiBoxing class represents a Thai Boxing session in the gym management system.
 * It implements the Session interface and provides details about the session such as time,
 * forum type, instructor, price, capacity, and registered clients.
 */
public class ThaiBoxing extends Session {
    private static final int PRICE = 100;
    private static final int CAPACITY = 20;

    private String time;
    private ForumType forumType;
    private Instructor instructor;
    private Set<Client> registered;

    /**
     * Constructs a new ThaiBoxing session with the specified time, forum type, and instructor.
     *
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param instructor the instructor conducting the session
     */
    public ThaiBoxing(String time, ForumType forumType, Instructor instructor) {
        this.time = time;
        this.forumType = forumType;
        this.instructor = instructor;
        this.registered = new HashSet<>();
    }

    /**
     * Returns the session type.
     *
     * @return the session type
     */
    @Override
    public SessionType getSessionType() {
        return SessionType.ThaiBoxing;
    }

    /**
     * Returns the time of the session.
     *
     * @return the time of the session
     */
    @Override
    public String getTime() {
        return time;
    }

    /**
     * Returns the forum type of the session.
     *
     * @return the forum type of the session
     */
    @Override
    public ForumType getForumType() {
        return forumType;
    }

    /**
     * Returns the instructor conducting the session.
     *
     * @return the instructor conducting the session
     */
    @Override
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Returns the price of the session.
     *
     * @return the price of the session
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Returns the capacity of the session.
     *
     * @return the capacity of the session
     */
    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Returns the set of clients registered for the session.
     *
     * @return the set of clients registered for the session
     */
    @Override
    public Set<Client> getRegistered() {
        return new HashSet<>(registered);
    }

    /**
     * Registers a client to the session.
     *
     * @param c the client to be registered
     */
    @Override
    void registerToLesson(Client c) {
        registered.add(c);
    }
}