package gym.customers;

/**
 * The personInterface defines the contract for a person in the gym system.
 * It includes methods for retrieving personal details and managing account balance.
 */
public interface personInterface {
    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    String getName();

    /**
     * Gets the balance of the person's account.
     *
     * @return the balance of the person's account
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