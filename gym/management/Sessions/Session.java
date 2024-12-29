package gym.management.Sessions;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

public abstract class Session {
    private String time;
    private ForumType forumType;
    private Instructor instructor;
    private Set<Client> registered;

    protected Session(String time, ForumType forumType, Instructor instructor) {
        this.time = time;
        this.forumType = forumType;
        this.instructor = instructor;
        this.registered = new HashSet<>();
    }

    public String getTime() {
        return time;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Set<Client> getRegistered() {
        return new HashSet<>(registered);
    }

    void registerToLesson(Client c) {
        registered.add(c);
    }

    public abstract SessionType getSessionType();

    public abstract int getPrice();

    public abstract int getCapacity();
}