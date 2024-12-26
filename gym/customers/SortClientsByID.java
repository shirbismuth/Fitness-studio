package gym.customers;

import java.util.Comparator;

public class SortClientsByID implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2){
        return c1.getID() - c2.getID();
    }
}