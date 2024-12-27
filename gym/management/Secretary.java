package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Secretary implements personInterface, Subject {
    private int salary;
    private Person person;
    public static ArrayList<String> actionsHistory = new ArrayList<>();
    private static Set<Observer> observers = new HashSet<>();
    private static Set<Instructor> instructors = new HashSet<>();
    private static ArrayList<Session> sessions= new ArrayList<>();

    public Secretary(Person per, int salary) {
        person = per;
        this.salary = salary;
    }

    public Client registerClient(Person per) throws InvalidAgeException, DuplicateClientException {
        if (!this.equals(Gym.getInstance().getSecretary())) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            return null;
        }
        if (per == null) throw new NullPointerException("Invalid parameters.");
        if (per.getAge() < 18) throw new InvalidAgeException();

        for (Client current : getClients())
            if (current.getID() == per.getID())
                throw new DuplicateClientException();

        Client c = new Client(per);
        registerObserver(c);
        String action = "Registered new client: " + c.getName();
        actionsHistory.add(action);
        return c;
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        if (!this.equals(Gym.getInstance().getSecretary())) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            return;
        }
        if (c != null) {
            if (!observers.contains(c)) throw new ClientNotRegisteredException();
            unregisterObserver(c);
            String action = "Unregistered client: " + c.getName();
            actionsHistory.add(action);
        }
    }

    public Instructor hireInstructor(Person per, int hourlyWage, ArrayList<SessionType> qualifiedLessons) {
        if (!this.equals(Gym.getInstance().getSecretary())) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            return null;
        }
        if (per == null || qualifiedLessons.isEmpty()) throw new NullPointerException("Invalid parameters.");
        Instructor ins = new Instructor(per, hourlyWage, qualifiedLessons);
        instructors.add(ins);
        String action = "Hired new instructor: " + ins.getName() + " with salary per hour: " + ins.getWage();
        actionsHistory.add(action);
        return ins;
    }

    public Session addSession(SessionType sessionType, String time, ForumType forumType, Instructor ins) throws InstructorNotQualifiedException {
        if (!this.equals(Gym.getInstance().getSecretary())) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
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
        sessions.add(s);
        Instructor.addToNotPaidSet(s);
        String tempTime = timeToFormat(time);
        String action = "Created new session: " + sessionType + " on " + tempTime + " with instructor: " + ins.getName();
        actionsHistory.add(action);
        return s;
    }

    public void registerClientToLesson(Client c, Session s) throws ClientNotRegisteredException, DuplicateClientException {
        if (!this.equals(Gym.getInstance().getSecretary())) {
            String e = "Error: Former secretaries are not permitted to perform actions";
            System.out.println(e);
            return;
        }

        if (c == null || s == null) throw new NullPointerException("Invalid parameters.");

        if (!observers.contains(c)) {
            String e = "Error: The client is not registered with the gym and cannot enroll in lessons";
            System.out.println(e);
            return;
        }

        if (s.getRegistered().contains(c)) {
            String e = "Error: The client is already registered for this lesson";
            System.out.println(e);
            return;
        }

        boolean isValid = true;

        if (isPassed(s.getTime())) {
            String e = "Failed registration: Session is not in the future";
            actionsHistory.add(e);
            isValid = false;
        }

        String eForumType = isForumTypeMatch(c, s);
        if (!eForumType.isBlank()) {
            actionsHistory.add(eForumType);
            isValid = false;
        }

        if (c.getBalance() < s.getPrice()) {
            String action = "Failed registration: Client doesn't have enough balance";
            actionsHistory.add(action);
            isValid = false;
        }

        if (s.getCapacity() <= s.getRegistered().size()) {
            String action = "Failed registration: No available spots for session";
            actionsHistory.add(action);
            isValid = false;
        }

        if (isValid) {
            s.registerToLesson(c);
            int price = s.getPrice();
            c.withdraw(price);
            Gym.getInstance().deposit(price);
            String tempTime = timeToFormat(s.getTime());
            String action = "Registered client: " + c.getName() + " to session: " + s.getSessionType().toString() + " on " + tempTime + " for price: " + s.getPrice();
            actionsHistory.add(action);
        }
    }

    private String isForumTypeMatch(Client c, Session s) {
        switch (s.getForumType()) {
            case All:
                break;
            case Male:
                if (!c.getGender().equals(Gender.Male)) {
                    String e = "Failed registration: Client's gender doesn't match the session's gender requirements";
                    return e;
                }
            case Female:
                if (!c.getGender().equals(Gender.Female)) {
                    String e = "Failed registration: Client's gender doesn't match the session's gender requirements";
                    return e;
                }
                break;
            case Seniors:
                if (c.getAge() < 65) {
                    String e = "Failed registration: Client doesn't meet the age requirements for this session (Seniors)";
                    return e;
                }
                break;
        }
        return "";
    }

    private boolean isPassed(String lessonTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime givenDateTime = LocalDateTime.parse(lessonTime, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isAfter(givenDateTime);
    }

    public static void printActions() {
        for (String action : actionsHistory) {
            System.out.println(action);
        }
    }

    public String timeToFormat(String time) {
        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter resultFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, originalFormat);
        return dateTime.format(resultFormat);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notify(String message) {
        for (Observer current : observers) {
            current.update(message);
        }
        actionsHistory.add("A message was sent to all gym clients: " + message);
    }

    @Override
    public void notify(Session s, String message) {
        Set<Client> toNotify = s.getRegistered();
        for (Observer current : toNotify) {
            current.update(message);
        }
        actionsHistory.add("A message was sent to everyone registered for session " + s.getSessionType() + " on " + timeToFormat(s.getTime()) + " : " + message);
    }

    @Override
    public void notify(String date, String message) {
        for (Session session : sessions) {
            String[] strArr = session.getTime().split(" ");
            if (strArr[0].equals(date)) {
                Set<Client> toNotify = session.getRegistered();
                for (Observer current : toNotify) current.update(message);
            }
        }
        String[] dateArr = date.split("-");
        String dateByFormat = dateArr[2] + "-" + dateArr[1] + "-" + dateArr[0];
        actionsHistory.add("A message was sent to everyone registered for a session on " + dateByFormat + " : " + message);
    }

    public void paySalaries() {
        this.deposit(salary); // Pay to secretary
        Gym.getInstance().withdraw(salary);
        for (Session session : Instructor.getNotPaidSet()) {
            Instructor ins = session.getInstructor();
            int wage = ins.getWage();
            Instructor.removeFromNotPaid(session);
            ins.deposit(wage);
            Gym.getInstance().withdraw(wage);
        }
        actionsHistory.add("Salaries have been paid to all employees");
    }

    public int getSalary() {
        return this.salary;
    }

    public Set<Client> getClients() {
        Set<Client> result = new HashSet<>();
        for (Observer current : observers) {
            result.add((Client) current);
        }
        return result;
    }

    public Set<Instructor> getInstructors() {
        return new HashSet<>(instructors);
    }

    public ArrayList<Session> getSessions() {
        return new ArrayList<>(sessions);
    }

    @Override
    public String getName() {
        return person.getName();
    }

    @Override
    public int getBalance() {
        return person.getBalance();
    }

    @Override
    public Gender getGender() {
        return person.getGender();
    }

    @Override
    public String getBirthday(){
        return person.getBirthday();
    }

    @Override
    public int getID()
    {
        return person.getID();
    }

    @Override
    public int getAge()
    {
        return person.getAge();
    }

    @Override
    public void deposit(int sum){
        person.deposit(sum);
    }

    @Override
    public void withdraw(int sum){
        person.withdraw(sum);
    }
}