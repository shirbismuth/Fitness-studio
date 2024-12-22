package gym.management.Sessions;

import gym.customers.Client;

import java.util.Set;

public class MachinePilates implements Session {
    private String time;
    private ForumType forumType;
    private Instructor instructor;
    private Set<Client> registered;
    final private int price = 80;
    final private int capacity = 10;

    public MachinePilates(String time,ForumType forumType, Instructor instructor){
        this.time=time;
        this.forumType=forumType;
        this.instructor=instructor;
    }
    @Override
    public SessionType getSessionType() {
        return SessionType.Pilates ;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public ForumType getForumType() {
        return forumType;
    }

    @Override
    public Instructor getInstructor() {
        return instructor;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
