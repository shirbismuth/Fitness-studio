package gym.management.Sessions;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

public class MachinePilates implements Session {
    private static final int PRICE = 80;
    private static final int CAPACITY = 10;

    private String time;
    private ForumType forumType;
    private Instructor instructor;
    private Set<Client> registered;

    public MachinePilates(String time,ForumType forumType, Instructor instructor){
        this.time=time;
        this.forumType=forumType;
        this.instructor=instructor;
        this.registered = new HashSet<>();
    }
    @Override
    public SessionType getSessionType() {
        return SessionType.MachinePilates ;
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
        return PRICE;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public Set<Client> getRegistered() {
        return new HashSet<>(registered);
    }

    @Override
    public void registerToLesson(Client c){
        registered.add(c);
    }
}
