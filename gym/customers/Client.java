package gym.customers;

import gym.management.Secretary;

import java.util.ArrayList;
import java.util.List;

public class Client implements personInterface, Observer {
    private List<String> messages;
    private Person person;

    public Client(Person per) {
        person = per;
        messages = new ArrayList<>();
    }

    @Override
    public String getName() {
        return person.getName();
    }

    @Override
    public int getBalance() {
        return person.getBalance();
    }

    @Override
    public Gender getGender() {
        return person.getGender();
    }

    @Override
    public String getBirthday(){
        return person.getBirthday();
    }

    @Override
    public int getID()
    {
        return person.getID();
    }

    @Override
    public int getAge()
    {
        return person.getAge();
    }

    @Override
    public void deposit(int sum){
        person.deposit(sum);
    }

    @Override
    public void withdraw(int sum){
        person.withdraw(sum);
    }

    public List<String> getNotifications() {
        Secretary.docHistory("");
        Secretary.docHistory(person.getName() + " Notifications: " + messages);
        return messages;
    }

    @Override
    public void update(String message) {
        messages.add(message);
    }
}
