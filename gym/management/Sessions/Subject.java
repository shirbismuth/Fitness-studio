package gym.management.Sessions;

import gym.customers.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notify(String message);
    void notify(Session s, String message);
    void notify(String date, String message);
}