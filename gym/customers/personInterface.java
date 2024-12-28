package gym.customers;

/**
 * The personInterface defines the methods that must be implemented by any class representing a person in the gym system.
 */
public interface personInterface {
    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    String getName();

    /**
     * Gets the balance of the person.
     *
     * @return the balance of the person
     */
    int getBalance();

    /**
     * Gets the gender of the person.
     *
     * @return the gender of the person
     */
    Gender getGender();

    /**
     * Gets the birthday of the person.
     *
     * @return the birthday of the person
     */
    String getBirthday();

    /**
     * Gets the ID of the person.
     *
     * @return the ID of the person
     */
    int getID();

    /**
     * Gets the age of the person.
     *
     * @return the age of the person
     */
    int getAge();

    /**
     * Deposits a specified sum into the person's account.
     *
     * @param sum the amount to deposit
     */
    void deposit(int sum);

    /**
     * Withdraws a specified sum from the person's account.
     *
     * @param sum the amount to withdraw
     */
    void withdraw(int sum);
}