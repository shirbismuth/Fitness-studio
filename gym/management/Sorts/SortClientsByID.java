package gym.management.Sorts;

import gym.customers.Client;

import java.util.Comparator;

/**
 * The SortClientsByID class implements the Comparator interface for comparing Client objects by their ID.
 */
public class SortClientsByID implements Comparator<Client> {
    /**
     * Compares two Client objects by their ID.
     *
     * @param c1 the first Client object to be compared
     * @param c2 the second Client object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(Client c1, Client c2){
        return c1.getID() - c2.getID();
    }
}