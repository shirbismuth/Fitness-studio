package gym.management.Sessions;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.*;
import gym.helpers.*;
import gym.management.Subject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The Secretary class represents a secretary in the gym management system.
 * It implements the personInterface and Subject interfaces and provides methods to manage clients, instructors and sessions.
 */
public class Secretary implements personInterface, Subject {
    private int salary;
    private Person person;
    public static ArrayList<String> actionsHistory = new ArrayList<>();
    private static Set<Observer> observers = new HashSet<>();
    private static Set<Instructor> instructors = new HashSet<>();
    private static ArrayList<Session> sessions = new ArrayList<>();
    private static Set<Session> notPaidSessions = new HashSet<>();

    /**
     * Constructs a new Secretary with the specified person and salary.
     *
     * @param per the person representing the secretary
     * @param salary the salary of the secretary
     */
    public Secretary(Person per, int salary) {
        person = per;
        this.salary = salary;
    }

    /**
     * Registers a new client.
     *
     * @param per the person to be registered as a client
     * @return the registered client
     * @throws InvalidAgeException if the person's age is less than 18
     * @throws DuplicateClientException if the client is already registered
     */
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

    /**
     * Unregisters a client.
     *
     * @param c the client to be unregistered
     * @throws ClientNotRegisteredException if the client is not registered
     */
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

    /**
     * Hires a new instructor.
     *
     * @param per the person to be hired as an instructor
     * @param hourlyWage the hourly wage of the instructor
     * @param qualifiedLessons the list of sessions the instructor is qualified to teach
     * @return the hired instructor
     */
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

    /**
     * Adds a new session.
     *
     * @param sessionType the type of the session
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param ins the instructor conducting the session
     * @return the added session
     * @throws InstructorNotQualifiedException if the instructor is not qualified to teach the session
     */
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
        notPaidSessions.add(s);
        String tempTime = Dates.timeToFormat(time);
        String action = "Created new session: " + sessionType + " on " + tempTime + " with instructor: " + ins.getName();
        actionsHistory.add(action);
        return s;
    }

    /**
     * Registers a client to a lesson.
     *
     * @param c the client to be registered
     * @param s the session to register the client to
     * @throws ClientNotRegisteredException if the client is not registered
     * @throws DuplicateClientException if the client is already registered for the session
     */
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

        if (Dates.isPassed(s.getTime())) {
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
            String tempTime = Dates.timeToFormat(s.getTime());
            String action = "Registered client: " + c.getName() + " to session: " + s.getSessionType() + " on " + tempTime + " for price: " + s.getPrice();
            actionsHistory.add(action);
        }
    }

    /**
     * Checks if the client's forum type matches the session's forum type.
     *
     * @param c the client
     * @param s the session
     * @return an error message if the forum type does not match, otherwise an empty string
     */
    private String isForumTypeMatch(Client c, Session s) {
        switch (s.getForumType()) {
            case All:
                break;
            case Male:
                if (!c.getGender().equals(Gender.Male)) {
                    return "Failed registration: Client's gender doesn't match the session's gender requirements";
                }
            case Female:
                if (!c.getGender().equals(Gender.Female)) {
                    return "Failed registration: Client's gender doesn't match the session's gender requirements";
                }
                break;
            case Seniors:
                if (c.getAge() < 65) {
                    return "Failed registration: Client doesn't meet the age requirements for this session (Seniors)";
                }
                break;
        }
        return "";
    }

    /**
     * Prints the actions history.
     */
    public static void printActions() {
        for (String action : actionsHistory) {
            System.out.println(action);
        }
    }

    /**
     * Registers an observer.
     *
     * @param o the observer to be registered
     */
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Unregisters an observer.
     *
     * @param o the observer to be unregistered
     */
    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifies all registered observers with a message.
     *
     * @param message the message to be sent to the observers
     */
    @Override
    public void notify(String message) {
        for (Observer current : observers) {
            current.update(message);
        }
        actionsHistory.add("A message was sent to all gym clients: " + message);
    }

    /**
     * Notifies all registered observers with a session and a message.
     *
     * @param s the session to be sent to the observers
     * @param message the message to be sent to the observers
     */
    @Override
    public void notify(Session s, String message) {
        Set<Client> toNotify = s.getRegistered();
        for (Observer current : toNotify) {
            current.update(message);
        }
        actionsHistory.add("A message was sent to everyone registered for session " + s.getSessionType() + " on " + Dates.timeToFormat(s.getTime()) + " : " + message);
    }

    /**
     * Notifies all registered observers with a date and a message.
     *
     * @param date the date to be sent to the observers
     * @param message the message to be sent to the observers
     */
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

    /**
     * Pays salaries to all employees.
     */
    public void paySalaries() {
        this.deposit(salary); // Pay to secretary
        Gym.getInstance().withdraw(salary);
        Set<Session> notPaidCopy = new HashSet<>(notPaidSessions);
        for (Session session : notPaidCopy) {
            Instructor ins = session.getInstructor();
            int wage = ins.getWage();
            notPaidSessions.remove(session);
            ins.deposit(wage);
            Gym.getInstance().withdraw(wage);
        }
        actionsHistory.add("Salaries have been paid to all employees");
    }

    public String toString(){
        return person.toString() +  " | Role: Secretary | Salary per Month: " + salary;
    }

    /**
     * Returns the salary of the secretary.
     *
     * @return the salary of the secretary
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     * Returns the set of clients.
     *
     * @return the set of clients
     */
    public Set<Client> getClients() {
        Set<Client> result = new HashSet<>();
        for (Observer current : observers) {
            result.add((Client) current);
        }
        return result;
    }

    /**
     * Returns the set of instructors.
     *
     * @return the set of instructors
     */
    public Set<Instructor> getInstructors() {
        return new HashSet<>(instructors);
    }

    /**
     * Returns the list of sessions.
     * @return the list of sessions
     */
    public ArrayList<Session> getSessions() {
        return new ArrayList<>(sessions);
    }

    /**
     * Returns the name of the secretary.
     *
     * @return the name of the secretary
     */
    @Override
    public String getName() {
        return person.getName();
    }

    /**
     * Returns the balance of the secretary.
     *
     * @return the balance of the secretary
     */
    @Override
    public int getBalance() {
        return person.getBalance();
    }

    /**
     * Returns the gender of the secretary.
     *
     * @return the gender of the secretary
     */
    @Override
    public Gender getGender() {
        return person.getGender();
    }

    /**
     * Returns the birthday of the secretary.
     *
     * @return the birthday of the secretary
     */
    @Override
    public String getBirthday() {
        return person.getBirthday();
    }

    /**
     * Returns the ID of the secretary.
     *
     * @return the ID of the secretary
     */
    @Override
    public int getID() {
        return person.getID();
    }

    /**
     * Returns the age of the secretary.
     *
     * @return the age of the secretary
     */
    @Override
    public int getAge() {
        return person.getAge();
    }

    /**
     * Deposits a specified sum into the secretary's balance.
     *
     * @param sum the sum to be deposited
     */
    @Override
    public void deposit(int sum){
        person.deposit(sum);
    }

    /**
     * Withdraws a specified sum from the secretary's balance.
     *
     * @param sum the sum to be withdrawn
     */
    @Override
    public void withdraw(int sum){
        person.withdraw(sum);
    }
}