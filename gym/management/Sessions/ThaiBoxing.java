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

    public ThaiBoxing(String time,ForumType forumType, Instructor instructor){
        super(time , forumType , instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.ThaiBoxing ;
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}