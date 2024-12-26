package gym.customers;

import java.util.Comparator;

public class SortByID implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
        return p1.getID() - p2.getID();
    }
}