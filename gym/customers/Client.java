package gym.customers;

import java.util.ArrayList;
import java.util.List;

/**
 * The Client class implements the personInterface and Observer interfaces.
 * It represents a client in the gym system, encapsulating a Person object and managing notifications.
 */
public class Client implements personInterface, Observer {
    private List<String> messages;
    private Person person;

    /**
     * Constructs a Client object with the specified Person.
     *
     * @param per the Person object associated with this Client
     */
    public Client(Person per) {
        person = per;
        messages = new ArrayList<>();
    }

    /**
     * Gets the name of the client.
     *
     * @return the name of the client
     */
    @Override
    public String getName() {
        return person.getName();
    }

    /**
     * Gets the balance of the client.
     *
     * @return the balance of the client
     */
    @Override
    public int getBalance() {
        return person.getBalance();
    }

    /**
     * Gets the gender of the client.
     *
     * @return the gender of the client
     */
    @Override
    public Gender getGender() {
        return person.getGender();
    }

    /**
     * Gets the birthday of the client.
     *
     * @return the birthday of the client
     */
    @Override
    public String getBirthday(){
        return person.getBirthday();
    }

    /**
     * Gets the ID of the client.
     *
     * @return the ID of the client
     */
    @Override
    public int getID() {
        return person.getID();
    }

    /**
     * Gets the age of the client.
     *
     * @return the age of the client
     */
    @Override
    public int getAge() {return person.getAge();
    }

    /**
     * Deposits a specified sum into the client's account.
     *
     * @param sum the amount to deposit
     */
    @Override
    public void deposit(int sum) {
        person.deposit(sum);
    }

    /**
     * Withdraws a specified sum from the client's account.
     *
     * @param sum the amount to withdraw
     */
    @Override
    public void withdraw(int sum) {
        person.withdraw(sum);
    }

    /**
     * Gets the list of notifications for the client.
     *
     * @return the list of notifications
     */
    public List<String> getNotifications() {
        return messages;
    }

    /**
     * Updates the client with a new message.
     *
     * @param message the message to add to the notifications
     */
    @Override
    public void update(String message) {
        messages.add(message);
    }
}