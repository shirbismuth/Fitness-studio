package gym.management.Sorts;

import gym.management.Sessions.Instructor;

import java.util.Comparator;

/**
 * The SortInstructorsByID class implements the Comparator interface to provide
 * a comparison function for sorting Instructor objects by their ID.
 */
public class SortInstructorsByID implements Comparator<Instructor> {
    /**
     * Compares two Instructor objects by their ID.
     *
     * @param i1 the first Instructor to be compared
     * @param i2 the second Instructor to be compared
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second
     */
    @Override
    public int compare(Instructor i1, Instructor i2){
        return i1.getID() - i2.getID();
    }
}