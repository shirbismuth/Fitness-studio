package gym.management.Sessions;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

public class Pilates implements Session{
    private String time;
    private ForumType forumType;
    private Instructor instructor;
    private Set<Client> registered;
    final private int price = 60;
    final private int capacity = 30;

    public Pilates(String time,ForumType forumType, Instructor instructor){
        this.time=time;
        this.forumType=forumType;
        this.instructor=instructor;
        this.registered = new HashSet<>();
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

    @Override
    public Set<Client> getRegistered() {
        return new HashSet<>(registered);
    }

    @Override
    public void registerToLesson(Client c){
        registered.add(c);
    }
}
