package gym.management.Sessions;

import gym.customers.Gender;
import gym.customers.Person;
import gym.customers.personInterface;

import java.util.ArrayList;

/**
 * The Instructor class represents an instructor in the gym management system.
 * It implements the personInterface and contains information about the instructor's
 * personal details, hourly wage, and qualified session types.
 */
public class Instructor implements personInterface {
    private int hourlyWage;
    private ArrayList<SessionType> qualifiedLessons;
    private Person person;

    /**
     * Constructs a new Instructor with the specified person details, hourly wage, and qualified session types.
     *
     * @param person the personal details of the instructor
     * @param wage the hourly wage of the instructor
     * @param arr the list of session types the instructor is qualified to conduct
     */
    public Instructor(Person person, int wage, ArrayList<SessionType> arr){
        this.person = person;
        this.hourlyWage = wage;
        this.qualifiedLessons = new ArrayList<>(arr);
    }

    /**
     * Returns the hourly wage of the instructor.
     *
     * @return the hourly wage of the instructor
     */
    public int getWage(){
        return hourlyWage;
    }

    /**
     * Returns a list of session types the instructor is qualified to conduct.
     *
     * @return a list of session types the instructor is qualified to conduct
     */
    public ArrayList<SessionType> getQualifiedList() {
        return new ArrayList<>(qualifiedLessons);
    }

    /**
     * Returns the name of the instructor.
     *
     * @return the name of the instructor
     */
    @Override
    public String getName() {
        return person.getName();
    }

    /**
     * Returns the balance of the instructor.
     *
     * @return the balance of the instructor
     */
    @Override
    public int getBalance() {
        return person.getBalance();
    }

    /**
     * Returns the gender of the instructor.
     *
     * @return the gender of the instructor
     */
    @Override
    public Gender getGender() {
        return person.getGender();
    }

    /**
     * Returns the birthday of the instructor.
     *
     * @return the birthday of the instructor
     */
    @Override
    public String getBirthday(){
        return person.getBirthday();
    }

    /**
     * Returns the ID of the instructor.
     *
     * @return the ID of the instructor
     */
    @Override
    public int getID()
    {
        return person.getID();
    }

    /**
     * Returns the age of the instructor.
     *
     * @return the age of the instructor
     */
    @Override
    public int getAge()
    {
        return person.getAge();
    }

    /**
     * Deposits a specified sum into the instructor's account.
     *
     * @param sum the sum to be deposited
     */
    @Override
    public void deposit(int sum){
        person.deposit(sum);
    }

    /**
     * Withdraws a specified sum from the instructor's account.
     *
     * @param sum the sum to be withdrawn
     */
    @Override
    public void withdraw(int sum){
        person.withdraw(sum);
    }

    /**
     * Returns a string representation of the instructor.
     *
     * @return a string representation of the instructor
     */
    @Override
    public String toString() {
        String certifiedClasses = "";
        for(SessionType cls : qualifiedLessons) {
            certifiedClasses += cls + ", ";
        }
        certifiedClasses = certifiedClasses.substring(0, certifiedClasses.length()-2); // Cuts the last comma and space
        return person.toString() + " | Role: Instructor | Salary per Hour: " + hourlyWage +
                " | Certified Classes: " + certifiedClasses;
    }
}