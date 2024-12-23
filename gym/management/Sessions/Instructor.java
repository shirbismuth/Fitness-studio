package gym.management.Sessions;

import gym.customers.*;
import java.util.ArrayList;

public class Instructor extends Person {
    private int wage;
    private ArrayList<SessionType> qualifiedLessons;

    public Instructor(Person person, int wage, ArrayList<SessionType> arr){
        super(person);
        this.wage = wage;
        this.qualifiedLessons = new ArrayList<>(arr);
    }

    public int getWage(){
        return wage;
    }

    public ArrayList<SessionType> getQualifiedList() {
        return new ArrayList<>(qualifiedLessons);
    }
}