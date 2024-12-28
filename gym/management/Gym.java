package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sorts.SortClientsByID;
import gym.management.Sessions.Instructor;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;
import gym.management.Sorts.SortInstructorsByID;

import java.util.ArrayList;

/**
 * The Gym class represents a gym in the gym management system.
 * It is implemented as a singleton to ensure only one instance exists.
 */
public class Gym {
    private static Gym instance;
    private String name;
    private int balance;
    private Secretary secretary;

    /**
     * Private constructor to prevent instantiation.
     */
    private Gym() {
    }

    /**
     * Returns the singleton instance of the Gym.
     *
     * @return the singleton instance of the Gym
     */
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    /**
     * Returns the name of the gym.
     *
     * @return the name of the gym
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the balance of the gym.
     *
     * @return the balance of the gym
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns the secretary of the gym.
     *
     * @return the secretary of the gym
     */
    public Secretary getSecretary() {
        return secretary;
    }

    /**
     * Sets the name of the gym.
     *
     * @param newName the new name of the gym
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the secretary of the gym with the specified person and salary.
     *
     * @param p1 the person to be set as the secretary
     * @param salary the salary of the secretary
     */
    public void setSecretary(Person p1, int salary) {
        secretary = new Secretary(p1, salary);
        String action = "A new secretary has started working at the gym: " + secretary.getName();
        Secretary.actionsHistory.add(action);
    }

    /**
     * Deposits a specified sum into the gym's balance.
     *
     * @param sum the sum to be deposited
     */
    public void deposit(int sum){
        this.balance += sum;
    }

    /**
     * Withdraws a specified sum from the gym's balance.
     *
     * @param sum the sum to be withdrawn
     */
    public void withdraw(int sum){
        this.balance -= sum;
    }

    /**
     * Returns a string representation of the gym, including information about the gym, clients, employees, and sessions.
     *
     * @return a string representation of the gym
     */
    @Override
    public String toString() {
        ArrayList<String> strList = new ArrayList<>();
        // Gym information
        strList.add("Gym Name: " + getName());
        strList.add("Gym Secretary: ID: " + secretary.getID() + " | Name: " + secretary.getName() + " | Gender: " +
                secretary.getGender() + " | Birthday: " + secretary.getBirthday() + " | Age: " + secretary.getAge() +
                " | Balance: " + secretary.getBalance() + " | Role: Secretary | Salary per Month: " + secretary.getSalary());
        strList.add("Gym Balance: " + Gym.getInstance().getBalance());

        // Empty line
        strList.add("");

        // Clients Data
        strList.add("Clients Data:");
        ArrayList<Client> clients = new ArrayList<>(secretary.getClients());
        clients.sort(new SortClientsByID());

        for(Client c : clients) {
            strList.add("ID: " + c.getID() + " | Name: " + c.getName() + " | Gender: " + c.getGender() +
                    " | Birthday: " + c.getBirthday() + " | Age: " + c.getAge() + " | Balance: " + c.getBalance());
        }

        // Empty line
        strList.add("");

        // Employees Data
        strList.add("Employees Data:");
        ArrayList<Instructor> instructors = new ArrayList<>(secretary.getInstructors());
        instructors.sort(new SortInstructorsByID());

        // Instructors
        for(Instructor ins : instructors) {
            String certifiedClasses = "";
            for(SessionType cls : ins.getQualifiedList()) {
                certifiedClasses += cls + ", ";
            }
            certifiedClasses = certifiedClasses.substring(0, certifiedClasses.length()-2); // Cuts the last comma and space
            strList.add("ID: " + ins.getID() + " | Name: " + ins.getName() + " | Gender: " + ins.getGender() +
                    " | Birthday: " + ins.getBirthday() + " | Age: " + ins.getAge() + " | Balance: " + ins.getBalance() +
                    " | Role: Instructor | Salary per Hour: " + ins.getWage() + " | Certified Classes: " + certifiedClasses);
        }
        // Secretary
        strList.add("ID: " + secretary.getID() + " | Name: " + secretary.getName() + " | Gender: " + secretary.getGender() +
                " | Birthday: " + secretary.getBirthday() + " | Age: " + secretary.getAge() + " | Balance: " + secretary.getBalance() +
                " | Role: Secretary | Salary per Month: " + secretary.getSalary());

        // Empty line
        strList.add("");

        strList.add("Sessions Data:");
        ArrayList<Session> sessions = new ArrayList<>(secretary.getSessions());

        for(Session s : sessions) {
            strList.add("Session Type: " + s.getSessionType() + " | Date: " + s.getTime() + " | Forum: " + s.getForumType() +
                    " | Instructor: " + s.getInstructor().getName() + " | Participants: " + s.getRegistered().size() + "/" + s.getCapacity());
        }

        String temp = "";
        for(String str : strList) {
            temp += str + "\n";
        }
        return temp.substring(0, temp.length()-1); // Cuts the last empty line and returns the entire string
    }
}