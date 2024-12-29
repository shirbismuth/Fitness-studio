package gym.management.Sessions;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

public class Pilates extends Session {
    private static final int PRICE = 60;
    private static final int CAPACITY = 30;
    private Set<Client> registered;

    public Pilates(String time,ForumType forumType, Instructor instructor){
        super(time , forumType , instructor);
        this.registered = new HashSet<>();
    }
    @Override
    public SessionType getSessionType() {
        return SessionType.Pilates ;
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
    public Set<Client> getRegistered() {return new HashSet<>(registered);}

    @Override
    void registerToLesson(Client c){
        registered.add(c);
    }
}