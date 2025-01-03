package gym.customers;

import gym.helpers.*;

/**
 * The Person class implements the personInterface and represents a person in the gym system.
 * It encapsulates personal details such as name, balance, gender, birthday, and ID.
 */
public class Person implements personInterface {
    private static int countID = 1111;

    private String name;
    private int balance;
    private Gender gender;
    private String birthday;
    private int id;

    /**
     * Constructs a Person object with the specified details.
     *
     * @param name the name of the person
     * @param balance the balance of the person
     * @param gender the gender of the person
     * @param birthday the birthday of the person in the format "dd-MM-yyyy"
     */
    public Person(String name, int balance, Gender gender, String birthday) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;
        this.id = countID++;
    }

    /**
     * Gets the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return Dates.ageCalculator(birthday);
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the balance of the person.
     *
     * @return the balance of the person
     */
    public int getBalance(){
        return this.balance;
    }

    /**
     * Gets the gender of the person.
     *
     * @return the gender of the person
     */
    public Gender getGender(){
        return this.gender;
    }

    /**
     * Gets the birthday of the person.
     *
     * @return the birthday of the person
     */
    public String getBirthday(){
        return this.birthday;
    }

    /**
     * Gets the ID of the person.
     *
     * @return the ID of the person
     */
    public int getID() {
        return this.id;
    }

    /**
     * Deposits a specified sum into the person's account.
     *
     * @param sum the amount to deposit
     */
    public void deposit(int sum){
        this.balance += sum;
    }

    /**
     * Withdraws a specified sum from the person's account.
     *
     * @param sum the amount to withdraw
     */
    public void withdraw(int sum){
        this.balance -= sum;
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Gender: " + gender + " | Birthday: " + birthday +
                " | Age: " + getAge() + " | Balance: " + balance;
    }
}