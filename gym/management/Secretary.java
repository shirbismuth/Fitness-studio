package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Secretary extends Person implements Subject {
    static final String FILENAME = "output1.txt";

    private int salary;
    private Set<Client> clients;
    private Set<Instructor> instructors;
    private static File outFile = new File(FILENAME); // Remember to remove the '1' from the name of the file

    public Secretary(Person per, int salary) {
        super(per);
        this.salary = salary;
        this.clients = new HashSet<>();
        this.instructors = new HashSet<>();
    }

    @Override
    public Client registerClient(Person per) throws InvalidAgeException, DuplicateClientException {
        if (!Gym.getInstance().getSecretary().equals(this)) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            docHistory(e);
            return null;
        }
        if (per == null) throw new NullPointerException("Invalid parameters.");
        if (per.getAge() < 18) throw new InvalidAgeException();
        Client c = new Client(per);
        if (clients.contains(c)) throw new DuplicateClientException();
        clients.add(c);
        String action = "Registered new client: " + c.getName();
        System.out.println(action);
        docHistory(action);
        return c;
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        if (!Gym.getInstance().getSecretary().equals(this)) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            docHistory(e);
            return;
        }
        if (c != null) {
            if (!clients.contains(c)) throw new ClientNotRegisteredException();
            clients.remove(c);
            String action = "Unregistered client: " + c.getName();
            System.out.println(action);
            docHistory(action);
        }
    }

    public Instructor hireInstructor(Person per, int hourlyWage, ArrayList<SessionType> qualifiedLessons) {
        if (!Gym.getInstance().getSecretary().equals(this)) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            docHistory(e);
            return null;
        }
        if (per == null || qualifiedLessons.isEmpty()) throw new NullPointerException("Invalid parameters.");
        Instructor ins = new Instructor(per, hourlyWage, qualifiedLessons);
        instructors.add(ins);
        String action = "Hired new instructor: " + ins.getName() + " with salary per hour: " + ins.getWage();
        System.out.println(action);
        docHistory(action);
        return ins;
    }

    public Session addSession(SessionType sessionType, String time, ForumType forumType, Instructor ins) throws InstructorNotQualifiedException {
        if (!Gym.getInstance().getSecretary().equals(this)) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            docHistory(e);
            return null;
        }
        if (sessionType == null || forumType == null || ins == null)
            throw new NullPointerException("Invalid parameters.");

        // InstructorNotQualifiedException check
        boolean isQualified = false;
        for (SessionType type : ins.getQualifiedList()) {
            if (sessionType.equals(type)) {
                isQualified = true;
                break;
            }
        }
        if (!isQualified) throw new InstructorNotQualifiedException();

        Session s = SessionFactory.createSession(sessionType, time, forumType, ins);
        String action = "Created new session: " + sessionType + " on " + time + " with instructor: " + ins.getName();
        System.out.println(action);
        docHistory(action);
        return s;
    }

    public void registerClientToLesson(Client c, Session s) throws ClientNotRegisteredException, DuplicateClientException {
        if (!Gym.getInstance().getSecretary().equals(this)) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            docHistory(e);
            return;
        }

        if (c == null || s == null) throw new NullPointerException("Invalid parameters.");

        if (s.getRegistered().contains(c)) {
            throw new DuplicateClientException();
        }

        if (!clients.contains(c)) {
            throw new ClientNotRegisteredException();
        }

        boolean isValid = true;

        if (isPassed(s.getTime())) {
            String e = "Failed registration: gym.management.Sessions.Session is not in the future";
            System.out.println(e);
            docHistory(e);
            isValid = false;
        }

        String eForumType = isForumTypeMatch(c,s);
        if (!eForumType.isBlank()) {
            System.out.println(eForumType);
            docHistory(eForumType);
            isValid = false;
        }

        if (c.getBalance() < s.getPrice()) {
            String action = "Failed registration: gym.customers.Client doesn't have enough balance";
            System.out.println(action);
            docHistory(action);
            isValid = false;
        }

        if (s.getCapacity() <= s.getRegistered().size()) {
            String action = "Failed registration: No available spots for session";
            System.out.println(action);
            docHistory(action);
            isValid = false;
        }

        if(isValid) {
            s.registerToLesson(c);
            c.withdraw(s.getPrice());
            Gym.getInstance().deposit(s.getPrice());
            String action = "Registered client: " + c.getName() + " to session: " + s.getSessionType().toString() + " on " + s.getTime() + " for price: " + s.getPrice();
            System.out.println(action);
            docHistory(action);
        }
    }

    private String isForumTypeMatch(Client c, Session s) {
        switch (s.getForumType()) {
            case All:
                break;
            case Male:
                if (!c.getGender().equals(Gender.Male)) {
                    String e = "Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements";
                    return e;
                }
            case Female:
                if (!c.getGender().equals(Gender.Female)) {
                    String e = "Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements";
                    return e;
                }
                break;
            case Seniors:
                if (c.getAge() < 65) {
                    String e = "Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)";
                    return e;
                }
                break;
        }
        return "";
    }

    private boolean isPassed (String lessonTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime givenDateTime = LocalDateTime.parse(lessonTime, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isAfter(givenDateTime);
    }

    public static void docHistory(String line) {
        boolean created = createFile();
        try {
            FileWriter writerFile = new FileWriter(FILENAME, true);
            if(!created) writerFile.append("\n"); // New line
            writerFile.append(line);
            writerFile.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred related to the actions history (Writing).");
            e.printStackTrace();
        }
    }

    private static boolean createFile() {
        try {
            if (outFile.createNewFile()) {
                System.out.println("File created: " + outFile.getName());
                return true;
            }
            // else: File already exists - the method returns false
        }
        catch (IOException e) {
            System.out.println("An error occurred related to the actions history (Creating file).");
            e.printStackTrace();
        }
        return false;
    }


}