package gym.management;

import gym.customers.Observer;

public interface Subject {
    public Observer registerClient(Observer o);
    public void unregisterClient(Observer o);
    public void notify();
}