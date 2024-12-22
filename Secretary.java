import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class Secretary extends Person {
    private int salary;
    private Set<Client> clients;
    private Set<Instructor> instructors;

    public Secretary(Person per, int salary) {
        super(per);
        this.salary = salary;
        this.clients = new HashSet<>();
    }

    public Client registerClient(Person per) throws InvalidAgeException, DuplicateClientException {
        if(per.getAge() < 18) throw new InvalidAgeException();
        Client c = new Client(per);
        if(clients.contains(c)) throw new DuplicateClientException();
        clients.add(c);
        System.out.println("Registered new client: " + c.getName());
        return c;
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        if(!clients.contains(c)) throw new ClientNotRegisteredException();
        System.out.println("Unregistered client: " + c.getName());
        clients.remove(c);
    }

    public Instructor hireInstructor(Person per, int hourlyWage, ArrayList<SessionType> qualifiedLessons) {
        Instructor ins = new Instructor(per, hourlyWage, qualifiedLessons);
        instructors.add(ins);
        return ins;
    }
}
