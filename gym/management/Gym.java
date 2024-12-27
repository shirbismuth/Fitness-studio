package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.customers.SortClientsByID;
import gym.customers.SortInstructorsByID;
import gym.management.Sessions.Instructor;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Gym {
    private static Gym instance;
    private String name;
    private int balance;
    private Secretary secretary;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setSecretary(Person p1, int salary) {
        secretary = new Secretary(p1, salary);
        String action = "A new secretary has started working at the gym: " + secretary.getName();
        Secretary.actionsHistory.add(action);
    }

    public void deposit(int sum){
        this.balance += sum;
    }

    public void withdraw(int sum){
        this.balance -= sum;
    }

    @Override
    public String toString() {
//        Secretary.docHistory("");
//        Secretary.docHistory("---Gym information---");
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
//            Secretary.docHistory(str);
            temp += str + "\n";
        }

        return temp.substring(0, temp.length()-2); // Cuts the last "\n"
    }
}