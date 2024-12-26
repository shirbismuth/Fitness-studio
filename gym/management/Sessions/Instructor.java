package gym.management.Sessions;

import gym.customers.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Instructor extends Person {
    private int hourlyWage;
    private Set<SessionType> qualifiedLessons;
    private Set<Session> notPaidSessions;

    public Instructor(Person person, int wage, ArrayList<SessionType> arr){
        super(person);
        this.hourlyWage = wage;
        this.qualifiedLessons = new HashSet<>(arr);
        this.notPaidSessions = new HashSet<>();
    }

    public int getWage(){
        return hourlyWage;
    }

    public void deposit(int sum){
        setBalance(getBalance() + sum);
    }

    public Set<SessionType> getQualifiedList() {
        return new HashSet<>(qualifiedLessons);
    }

    public void addToNotPaidSet(Session s){
        notPaidSessions.add(s);
    }

    public Set<Session> getNotPaidSet(){
        return new HashSet<>(notPaidSessions);
    }

    public void removeFromNotPaid(Session s){
        notPaidSessions.remove(s);
    }
}