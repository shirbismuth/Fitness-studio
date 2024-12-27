package gym.management.Sessions;

import gym.customers.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Instructor implements personInterface {
    private static Set<Session> notPaidSessions = new HashSet<>();
    private int hourlyWage;
    private ArrayList<SessionType> qualifiedLessons;
    private Person person;

    public Instructor(Person person, int wage, ArrayList<SessionType> arr){
        this.person=person;
        this.hourlyWage = wage;
        this.qualifiedLessons = new ArrayList<>(arr);
    }

    public static Set<Session> getNotPaidSet(){
        return new HashSet<>(notPaidSessions);
    }

    public static void addToNotPaidSet(Session s){
        notPaidSessions.add(s);
    }

    public static void removeFromNotPaid(Session s){
        notPaidSessions.remove(s);
    }

    public int getWage(){
        return hourlyWage;
    }

    public ArrayList<SessionType> getQualifiedList() {
        return new ArrayList<>(qualifiedLessons);
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
}