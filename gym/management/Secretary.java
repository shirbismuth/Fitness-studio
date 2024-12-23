package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class Secretary extends Person {
    private int salary;
    private Set<Client> clients;
    private Set<Instructor> instructors;
    static final private File outFile = new File("output1.txt"); // Remember to remove the '1' from the name of the file

    public Secretary(Person per, int salary) {
        super(per);
        this.salary = salary;
        this.clients = new HashSet<>();
    }

    public Client registerClient(Person per) throws InvalidAgeException, DuplicateClientException {
        if(!Gym.getInstance().getSecretary().equals(this)) {
            System.out.println("Error: Former secretaries are not permitted to perform actions"); // Required in assignment
            throw new IllegalStateException("Former secretaries are not permitted to perform actions");
        }
        if(per == null) throw new NullPointerException("Invalid parameters.");
        if(per.getAge() < 18) throw new InvalidAgeException();
        Client c = new Client(per);
        if(clients.contains(c)) throw new DuplicateClientException();
        clients.add(c);
        System.out.println("Registered new client: " + c.getName());
        return c;
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        if(!Gym.getInstance().getSecretary().equals(this)) {
            System.out.println("Error: Former secretaries are not permitted to perform actions"); // Required in assignment
            throw new IllegalStateException("Former secretaries are not permitted to perform actions");
        }
        if(c != null) {
            if(!clients.contains(c)) throw new ClientNotRegisteredException();
            System.out.println("Unregistered client: " + c.getName());
            clients.remove(c);
        }
    }

    public Instructor hireInstructor(Person per, int hourlyWage, ArrayList<SessionType> qualifiedLessons) {
        if(!Gym.getInstance().getSecretary().equals(this)) {
            System.out.println("Error: Former secretaries are not permitted to perform actions"); // Required in assignment
            throw new IllegalStateException("Former secretaries are not permitted to perform actions");
        }
        if(per == null || qualifiedLessons.isEmpty()) throw new NullPointerException("Invalid parameters.");
        Instructor ins = new Instructor(per, hourlyWage, qualifiedLessons);
        System.out.println("Hired new instructor: " + ins.getName() + " with salary per hour: " + ins.getWage());
        instructors.add(ins);
        return ins;
    }

    public Session addSession(SessionType sessionType, String time, ForumType forumType, Instructor ins) throws InstructorNotQualifiedException {
        if(!Gym.getInstance().getSecretary().equals(this)) {
            System.out.println("Error: Former secretaries are not permitted to perform actions"); // Required in assignment
            throw new IllegalStateException("Former secretaries are not permitted to perform actions");
        }
        if(sessionType == null || forumType == null || ins == null) throw new NullPointerException("Invalid parameters.");

        // InstructorNotQualifiedException check
        boolean isQualified = false;
        for(SessionType type : ins.getQualifiedList()) {
            if(sessionType.equals(type)) {
                isQualified = true;
                break;
            }
        }
        if(!isQualified) throw new InstructorNotQualifiedException();

        Session s = SessionFactory.createSession(sessionType, time, forumType, ins);
        System.out.println("Created new session: " + sessionType + " on " + time + "with instructor: " + ins.getName());
        return s;
    }

    public void registerClientToLesson(Client c, Session s) throws ClientNotRegisteredException, DuplicateClientException, IllegalStateException {
        if(!Gym.getInstance().getSecretary().equals(this)) {
            System.out.println("Error: Former secretaries are not permitted to perform actions");
            return;
        }
        if(c == null || s == null) throw new NullPointerException("Invalid parameters.");
        //Prints:
        //Registered client: Nofar to session: Pilates on 2025-01-23T10:00 for price: 60
        //Failed registration: gym.management.Sessions.Session is not in the future
        if (c.getBalance() < s.getPrice()){
            System.out.println("Failed registration: gym.customers.Client doesn't have enough balance");
            return;
        }
        if (s.getCapacity()==s.getRegistered().size()){
            System.out.println("Failed registration: No available spots for session");
            return;
        }
        if (s.getRegistered().contains(c)){
            System.out.println("Error: The client is already registered for this lesson");
            return;
        }
        if (!clients.contains(c)){
            System.out.println("Error: The client is not registered with the gym and cannot enroll in lessons");
            return;
        }
        if (checkForumType(c,s))



    }

    public boolean checkForumType(Client c, Session s) {
        String f = s.getForumType().name();
        String gender = c.getGender().name();
        switch (s.getForumType()) {
            case ForumType.All:
                return true;
            case ForumType.Male:
                return new Pilates(time, forumType, instructor);
            case ForumType.Female:
                return new MachinePilates(time, forumType, instructor);
            case ForumType.Seniors:
                return new Ninja(time, forumType, instructor);
            default:
                throw new IllegalArgumentException("Invalid session type");
        }
    }

    private void docHistory(String line) {
        try {
            if (outFile.createNewFile()) System.out.println("File created: " + outFile.getName());
            else System.out.println("File already exists.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
    }
}