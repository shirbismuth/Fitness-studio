import gym.Exception.InvalidAgeException;

import java.util.HashSet;
import java.util.Set;

public class Secretary extends Person {
    private int salary;
    private Set<Client> clients;

    public Secretary(Person per, int salary) {
        super(per);
        this.salary = salary;
        this.clients = new HashSet<>();
    }

    public Client registerClient(Person per) throws InvalidAgeException {
        if(per.getAge() < 18) throw new InvalidAgeException();
        Client c = new Client(per);
        clients.add(c);
        return c;
    }
}
