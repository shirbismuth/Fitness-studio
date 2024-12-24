package gym.management;

import gym.customers.Person;

public class Gym {
    private static Gym instance;
    private String name;
    private int balance;
    private Secretary secratary;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public int getBalance() {
        return balance;
    }

    public Secretary getSecretary() {
        return secratary;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setSecretary(Person p1, int salary) {
        secratary = new Secretary(p1, salary);
        String action = "A new secretary has started working at the gym: " + secratary.getName();
        Secretary.actionsHistory.add(action);
    }

    public void deposit(int sum){
        this.balance=this.balance+sum;
    }
}