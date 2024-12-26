package gym.customers;

import gym.management.Sessions.Instructor;

import java.util.Comparator;

public class SortInstructorsByID implements Comparator<Instructor> {
    @Override
    public int compare(Instructor i1, Instructor i2){
        return i1.getID() - i2.getID();
    }
}