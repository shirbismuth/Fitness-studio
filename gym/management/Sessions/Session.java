package gym.management.Sessions;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

public abstract class Session {
    protected String time;
    protected ForumType forumType;
    protected Instructor instructor;
    protected Set<Client> registered;

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



    abstract SessionType getSessionType();

    abstract int getPrice();

    abstract int getCapacity();

    abstract void registerToLesson(Client c);
}