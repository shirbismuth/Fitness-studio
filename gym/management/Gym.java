package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.customers.SortByID;

import java.util.ArrayList;
import java.util.Collections;

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

    public String getName() {
        return name;
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

    public void withdraw(int sum){
        this.balance=this.balance-sum;
    }

    @Override
    public String toString() {
        Secretary.docHistory("");
        Secretary.docHistory("---Gym information---");

        ArrayList<String> strList = new ArrayList<>();
        // Gym information
        strList.add("Gym Name: " + getName());
        strList.add("Gym Secretary: ID: " + secratary.getID() + " | Name: " + secratary.getName() + " | Gender: " +
                secratary.getGender() + " | Birthday: " + secratary.getBirthday() + " | Age: " + secratary.getAge() +
                " | Balance: " + secratary.getBalance() + " | Role: Secretary | Salary per Month: " + secratary.getSalary());
        strList.add("Gym Balance: " + Gym.getInstance().getBalance());

        // Empty line
        strList.add("");

        // Clients Data
        strList.add("Clients Data:");
        ArrayList<Client> clients = new ArrayList<>(secratary.getClients());

        // Comparator by id
        clients.sort(new SortByID());

        for(Client c : clients) {
            strList.add("ID: " + c.getID() + " | Name: " + c.getName() + " | Gender: " + c.getGender() +
                    " | Birthday: " + c.getBirthday() + " | Age: " + c.getAge() + " | Balance: " + c.getBalance());
        }

        String temp = "";
        for(String str : strList) {
            Secretary.docHistory(str);
            temp += str + "\n";
        }

        return temp.substring(0,temp.length()-2);
    }
}